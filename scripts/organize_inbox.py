"""Organize newly uploaded BaekjoonHub solutions from `_inbox` folders."""

from __future__ import annotations

import hashlib
import os
import re
import shutil
import subprocess
from collections import Counter
from pathlib import Path


INBOX_LANGUAGES = ("Java", "MySQL", "Oracle")
PLATFORM_PREFIX = {"백준": "BOJ", "프로그래머스": "PGS", "SWEA": "SWEA", "구름레벨": "GOORM"}

JAVA_PRIORITY = [
    "Shortest-Path",
    "DFS-BFS",
    "Dynamic-Programming",
    "Backtracking",
    "Binary-Search",
    "Two-Pointer",
    "Greedy",
    "Hash",
    "Stack-Queue",
    "Sorting",
    "String-Array",
    "Implementation",
    "Etc",
]

SQL_PRIORITY = [
    "WINDOW-FUNCTION",
    "CTE-RECURSIVE",
    "JOIN",
    "SUBQUERY",
    "GROUP-BY",
    "STRING-DATE",
    "CASE-NULL",
    "SELECT-WHERE",
    "Etc",
]

JAVA_KEYWORDS = {
    "Shortest-Path": ["최단 경로", "다익스트라", "플로이드", "벨만", "dijkstra", "floyd", "bellman", "priorityqueue dist"],
    "DFS-BFS": ["dfs", "bfs", "깊이 우선 탐색", "너비 우선 탐색", "그래프 탐색", "visited", "arraydeque"],
    "Dynamic-Programming": ["동적 계획법", "dp", "memo", "memoization"],
    "Backtracking": ["백트래킹", "backtracking", "순열", "조합"],
    "Binary-Search": ["이분 탐색", "binary search", "parametric search", "left right mid"],
    "Two-Pointer": ["투 포인터", "two pointer", "sliding window", "슬라이딩 윈도우"],
    "Greedy": ["그리디", "탐욕법", "greedy"],
    "Hash": ["해시", "hashmap", "hashset", "getordefault"],
    "Stack-Queue": ["스택", "큐", "deque", "stack", "queue", "후위 표기식", "괄호"],
    "Sorting": ["정렬", "arrays.sort", "collections.sort", "comparator"],
    "String-Array": ["문자열", "배열", "stringbuilder", "substring", "split", "charat"],
    "Implementation": ["구현", "시뮬레이션", "simulation", "완전 탐색", "brute force"],
}

SQL_KEYWORDS = {
    "WINDOW-FUNCTION": ["over(", "row_number(", "rank(", "dense_rank(", "lag(", "lead(", "first_value(", "last_value("],
    "CTE-RECURSIVE": ["with recursive", "connect by", " level ", "\nwith "],
    "JOIN": [" join ", "inner join", "left join", "right join", "cross join"],
    "SUBQUERY": ["(select", " exists(", " not exists", " in (select"],
    "GROUP-BY": ["group by", "having", "count(", "sum(", "avg(", "max(", "min("],
    "STRING-DATE": ["date_format", "datediff", "timestampdiff", "str_to_date", "to_char", "to_date", "substr", "substring", "concat", "regexp"],
    "CASE-NULL": ["case ", " when ", "ifnull", "nvl", "nvl2", "coalesce", "nullif", "is null"],
    "SELECT-WHERE": ["select ", " where ", " order by ", "distinct"],
}

FOLDER_RE = re.compile(r"^(?P<platform>[^-]+)-(?P<id>[^.]+?)\.\s*(?P<title>.+)$")
README_TITLE_RE = re.compile(r"^#\s*\[[^\]]+\]\s*(?P<title>.+?)\s*-\s*(?P<id>[^\s#]+)\s*$", re.MULTILINE)
README_SECTION_RE = re.compile(r"^###\s*(?:분류|구분)\s*(?P<body>.+?)(?=^###\s|\Z)", re.MULTILINE | re.DOTALL)
SPACE_RE = re.compile(r"[\s\u00A0\u1680\u180E\u2000-\u200B\u202F\u205F\u3000]+")


def main() -> int:
    """Scan inbox folders, move new problems, and print a summary."""
    root = Path(__file__).resolve().parents[1]
    total = moved = skipped = conflicts = errors = 0
    category_counts: dict[str, Counter[str]] = {language: Counter() for language in INBOX_LANGUAGES}

    for language in INBOX_LANGUAGES:
        inbox = root / language / "_inbox"
        if not inbox.exists():
            continue

        for problem_dir in find_problem_dirs(inbox):
            total += 1
            print(f"[DETECT] {to_rel(root, problem_dir)}")
            print(f"[LANGUAGE] {language}")
            try:
                result = handle_problem(root, language, problem_dir)
                if result["category"]:
                    category_counts[language][result["category"]] += 1
                for warning in result["warnings"]:
                    print(f"[WARN] {warning}")
                if result["status"] == "moved":
                    moved += 1
                elif result["status"] == "conflict":
                    conflicts += 1
                else:
                    skipped += 1
            except Exception as exc:  # noqa: BLE001
                errors += 1
                print(f"[ERROR] {to_rel(root, problem_dir)}: {exc}")

        cleanup_empty_inbox(root, language)

    print()
    print(f"전체 신규 문제 수: {total}")
    print(f"이동 완료 수: {moved}")
    print(f"건너뛴 문제 수: {skipped}")
    print(f"충돌 수: {conflicts}")
    print(f"오류 수: {errors}")
    for language in INBOX_LANGUAGES:
        print(f"{language} 유형별 수: {dict(category_counts[language])}")
    return 0


def find_problem_dirs(inbox: Path) -> list[Path]:
    """Return folders inside `_inbox` that contain source files."""
    found: list[Path] = []
    for current_root, dirnames, filenames in os.walk(inbox, topdown=True):
        current = Path(current_root)
        if any(Path(name).suffix.lower() in {".java", ".sql"} for name in filenames):
            found.append(current)
            dirnames[:] = []
    return sorted(found)


def handle_problem(root: Path, language: str, problem_dir: Path) -> dict[str, object]:
    """Classify one problem and move it into the target category folder."""
    java_files = list(problem_dir.glob("*.java"))
    sql_files = list(problem_dir.glob("*.sql"))
    warnings: list[str] = []

    if java_files and sql_files:
        print("[SKIP] Java와 SQL 파일이 함께 있습니다.")
        return {"status": "skipped", "category": None, "warnings": warnings}

    readme = read_text(problem_dir / "README.md")
    final_name = build_problem_name(problem_dir.name, readme, warnings)

    if language == "Java":
        code = "\n".join(read_text(path) for path in java_files)
        category, evidence = classify(JAVA_PRIORITY, JAVA_KEYWORDS, readme, code)
    else:
        code = "\n".join(read_text(path) for path in sql_files)
        category, evidence = classify(SQL_PRIORITY, SQL_KEYWORDS, readme, code)

    print(f"[CATEGORY] {category}")
    print(f"[EVIDENCE] {evidence}")

    target = root / language / category / final_name
    print(f"[MOVE] {to_rel(root, target)}")

    if target.exists():
        if fingerprint(problem_dir) == fingerprint(target):
            print("[SKIP] 동일한 대상 폴더가 이미 존재합니다.")
            return {"status": "skipped", "category": category, "warnings": warnings}
        print("[WARN] 대상 경로가 이미 존재하며 내용이 다릅니다.")
        return {"status": "conflict", "category": category, "warnings": warnings}

    target.parent.mkdir(parents=True, exist_ok=True)
    if not try_git_mv(root, problem_dir, target):
        shutil.move(str(problem_dir), str(target))
    cleanup_empty_parents(problem_dir.parent, stop_at=root / language)
    return {"status": "moved", "category": category, "warnings": warnings}


def classify(priority: list[str], keyword_map: dict[str, list[str]], readme: str, code: str) -> tuple[str, str]:
    """Pick the first matching category from README and source code."""
    section = readme_section(readme).lower()
    scope = normalize(f"{section}\n{readme}\n{code}").lower()
    hits: list[str] = []

    for category in priority:
        for keyword in keyword_map[category]:
            if keyword.lower() in scope:
                hits.append(f"{category}: {keyword}")
                return category, ", ".join(hits)

    return "Etc", "분류 키워드를 찾지 못했습니다."


def build_problem_name(folder_name: str, readme: str, warnings: list[str]) -> str:
    """Build the final destination folder name."""
    normalized_name = normalize(folder_name)
    match = FOLDER_RE.match(normalized_name)
    if match:
        platform = match.group("platform")
        problem_id = match.group("id")
        title = match.group("title")
        prefix = PLATFORM_PREFIX.get(platform, platform)
        if platform not in PLATFORM_PREFIX:
            warnings.append(f"알 수 없는 플랫폼이라 기존 이름을 유지합니다: {platform}")
        return f"{prefix}-{problem_id}. {title}"

    readme_match = README_TITLE_RE.search(readme)
    if readme_match:
        return f"{readme_match.group('id')}. {normalize(readme_match.group('title'))}"

    warnings.append("문제 폴더명을 해석하지 못해 기존 이름을 유지합니다.")
    return normalized_name


def readme_section(readme: str) -> str:
    """Extract the classification section from README when present."""
    match = README_SECTION_RE.search(readme)
    return match.group("body") if match else ""


def try_git_mv(root: Path, source: Path, target: Path) -> bool:
    """Try `git mv` first and return whether it succeeded."""
    command = [
        "git",
        "-c",
        f"safe.directory={root}",
        "mv",
        "--",
        to_rel(root, source),
        to_rel(root, target),
    ]
    result = subprocess.run(command, cwd=root, capture_output=True, text=True, check=False)
    return result.returncode == 0


def cleanup_empty_parents(current: Path, stop_at: Path) -> None:
    """Delete empty directories up to `stop_at`."""
    while current != stop_at:
        if any(current.iterdir()):
            break
        current.rmdir()
        current = current.parent


def cleanup_empty_inbox(root: Path, language: str) -> None:
    """Remove the inbox directory when it becomes empty."""
    inbox = root / language / "_inbox"
    if inbox.exists() and not any(inbox.iterdir()):
        inbox.rmdir()


def fingerprint(path: Path) -> list[tuple[str, str]]:
    """Return a deterministic hash list for all files in a directory."""
    items: list[tuple[str, str]] = []
    for file_path in sorted(path.rglob("*")):
        if file_path.is_file():
            digest = hashlib.sha256(file_path.read_bytes()).hexdigest()
            items.append((file_path.relative_to(path).as_posix(), digest))
    return items


def read_text(path: Path) -> str:
    """Read UTF-8 text safely."""
    if not path.exists():
        return ""
    return path.read_text(encoding="utf-8", errors="replace")


def normalize(text: str) -> str:
    """Collapse unusual whitespace into single spaces."""
    return SPACE_RE.sub(" ", text).strip()


def to_rel(root: Path, path: Path) -> str:
    """Return a repository-relative path."""
    return path.relative_to(root).as_posix()


if __name__ == "__main__":
    raise SystemExit(main())
