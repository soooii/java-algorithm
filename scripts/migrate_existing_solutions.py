"""Move existing solved problems into language/category folders.

Default mode is dry-run. Add ``--apply`` to actually move directories.
This version is intentionally simple and tuned for the current repository:

- `백준/...`
- `프로그래머스/...`
- optional `_inbox` folders under `Java/`, `MySQL/`, `Oracle/`
"""

from __future__ import annotations

import argparse
import os
import re
import shutil
import subprocess
from dataclasses import dataclass
from pathlib import Path


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
    "Shortest-Path": ["최단 경로", "다익스트라", "플로이드", "벨만", "dijkstra", "floyd", "bellman"],
    "DFS-BFS": ["dfs", "bfs", "그래프 탐색", "너비 우선 탐색", "깊이 우선 탐색"],
    "Dynamic-Programming": ["동적 계획법", "dp", "memo"],
    "Backtracking": ["백트래킹", "backtracking", "순열", "조합"],
    "Binary-Search": ["이분 탐색", "binary search", "parametric search"],
    "Two-Pointer": ["투 포인터", "two pointer", "sliding window", "슬라이딩 윈도우"],
    "Greedy": ["탐욕법", "greedy"],
    "Hash": ["해시", "hashmap", "hashset"],
    "Stack-Queue": ["스택", "큐", "stack", "queue", "deque"],
    "Sorting": ["정렬", "sorting", "arrays.sort", "collections.sort"],
    "String-Array": ["문자열", "배열", "stringbuilder", "substring", "split"],
    "Implementation": ["구현", "시뮬레이션", "simulation", "완전 탐색", "brute force"],
}

SQL_KEYWORDS = {
    "WINDOW-FUNCTION": ["over(", "row_number(", "rank(", "dense_rank("],
    "CTE-RECURSIVE": ["with recursive", "with "],
    "JOIN": [" join "],
    "SUBQUERY": ["(select", " exists(", " in (select"],
    "GROUP-BY": ["group by", "having"],
    "STRING-DATE": ["date_format", "str_to_date", "datediff", "timestampdiff", "to_char", "to_date", "substr"],
    "CASE-NULL": ["case ", "ifnull", "coalesce", "nvl", "decode"],
    "SELECT-WHERE": ["select ", "where "],
}

MYSQL_HINTS = ["ifnull", "date_format", "limit", "datediff", "timestampdiff", "str_to_date", "group_concat"]
ORACLE_HINTS = ["nvl", "nvl2", "decode", "sysdate", "connect by", "rownum", "listagg", "add_months", "months_between"]

PLATFORM_PREFIX = {"백준": "BOJ", "프로그래머스": "PGS", "SWEA": "SWEA", "구름레벨": "GOORM"}
SCAN_ROOTS = ["백준", "프로그래머스", "Java/_inbox", "MySQL/_inbox", "Oracle/_inbox"]
SKIP_DIRS = {".git", ".github", "scripts", "migration-report", "__pycache__"}
FOLDER_RE = re.compile(r"^(?:(?P<platform>[A-Za-z가-힣]+)-)?(?P<id>[^.]+?)\.\s*(?P<title>.+)$")
README_TITLE_RE = re.compile(r"^#\s*\[[^\]]+\]\s*(?P<title>.+?)\s*-\s*(?P<id>[^\s#]+)\s*$", re.MULTILINE)
README_LINK_RE = re.compile(r"\((https?://[^)]+)\)")
README_SECTION_RE = re.compile(r"^###\s*(?:분류|구분)\s*(?P<body>.+?)(?=^###\s|\Z)", re.MULTILINE | re.DOTALL)
SPACE_RE = re.compile(r"[\s\u00A0\u1680\u180E\u2000-\u200B\u202F\u205F\u3000]+")


@dataclass
class MovePlan:
    """One planned move."""

    source: Path
    target: Path
    language: str
    category: str
    platform: str | None
    note: str = ""


def main() -> int:
    """Run the migration."""
    args = parse_args()
    root = Path(__file__).resolve().parents[1]
    plans, skipped = build_plans(root)
    print_table(root, plans, skipped)

    if args.apply:
        apply_moves(root, plans)
        print(f"\nMoved: {len(plans)}")
    else:
        print(f"\nDry-run only. Planned moves: {len(plans)}")

    if skipped:
        print(f"Skipped: {len(skipped)}")
    return 0


def parse_args() -> argparse.Namespace:
    """Parse CLI args."""
    parser = argparse.ArgumentParser()
    parser.add_argument("--apply", action="store_true", help="Actually move directories.")
    return parser.parse_args()


def build_plans(root: Path) -> tuple[list[MovePlan], list[str]]:
    """Scan the repo and build move plans."""
    plans: list[MovePlan] = []
    skipped: list[str] = []
    seen_targets: set[Path] = set()

    for problem_dir in find_problem_dirs(root):
        plan = classify_problem(root, problem_dir)
        if plan is None:
            skipped.append(to_rel(root, problem_dir))
            continue
        if plan.target.exists() or plan.target in seen_targets:
            skipped.append(f"{to_rel(root, problem_dir)} -> {to_rel(root, plan.target)} (target exists)")
            continue
        plans.append(plan)
        seen_targets.add(plan.target)

    plans.sort(key=lambda item: to_rel(root, item.target))
    return plans, skipped


def find_problem_dirs(root: Path) -> list[Path]:
    """Find directories that look like problem folders."""
    found: list[Path] = []

    for scan_root in SCAN_ROOTS:
        start = root / scan_root
        if not start.exists():
            continue

        for current_root, dirnames, filenames in os.walk(start, topdown=True):
            current = Path(current_root)
            dirnames[:] = [name for name in dirnames if not should_skip(root, current / name)]
            if has_problem_sources(filenames):
                found.append(current)
                dirnames[:] = []

    return found


def should_skip(root: Path, path: Path) -> bool:
    """Return True when the directory should be skipped."""
    if path.name in SKIP_DIRS:
        return True

    parts = path.relative_to(root).parts
    if len(parts) >= 2 and parts[0] in {"Java", "MySQL", "Oracle"} and parts[1] != "_inbox":
        return True
    return False


def has_problem_sources(filenames: list[str]) -> bool:
    """Return True when the directory contains Java or SQL files."""
    suffixes = {Path(name).suffix.lower() for name in filenames}
    return ".java" in suffixes or ".sql" in suffixes


def classify_problem(root: Path, problem_dir: Path) -> MovePlan | None:
    """Create a move plan for one problem folder."""
    java_files = list(problem_dir.glob("*.java"))
    sql_files = list(problem_dir.glob("*.sql"))
    if java_files and sql_files:
        return None

    readme = read_text(problem_dir / "README.md")
    folder_name = normalize(problem_dir.name)
    platform = detect_platform(problem_dir, root, folder_name, readme)
    problem_id, title = detect_id_title(folder_name, readme)

    if java_files:
        language = "Java"
        code = "\n".join(read_text(path) for path in java_files)
        category = pick_category(JAVA_PRIORITY, detect_java_categories(problem_dir, root, readme, code))
        return MovePlan(
            source=problem_dir,
            target=root / "Java" / category / build_problem_name(platform, problem_id, title, folder_name),
            language=language,
            category=category,
            platform=platform,
        )

    if sql_files:
        code = "\n".join(read_text(path) for path in sql_files)
        dbms = detect_dbms(problem_dir, root, readme, code)
        if dbms is None:
            return None
        category = pick_category(SQL_PRIORITY, detect_sql_categories(readme, code))
        return MovePlan(
            source=problem_dir,
            target=root / dbms / category / build_problem_name(platform, problem_id, title, folder_name),
            language=dbms,
            category=category,
            platform=platform,
        )

    return None


def detect_platform(problem_dir: Path, root: Path, folder_name: str, readme: str) -> str | None:
    """Detect platform from path, folder, or README."""
    parts = set(problem_dir.relative_to(root).parts)
    if "백준" in parts:
        return "백준"
    if "프로그래머스" in parts:
        return "프로그래머스"

    folder_match = FOLDER_RE.match(folder_name)
    if folder_match and folder_match.group("platform") in PLATFORM_PREFIX:
        return folder_match.group("platform")

    readme_lower = readme.lower()
    if "acmicpc.net" in readme_lower:
        return "백준"
    if "programmers.co.kr" in readme_lower:
        return "프로그래머스"
    if "swexpertacademy" in readme_lower:
        return "SWEA"
    if "level.goorm.io" in readme_lower:
        return "구름레벨"
    return None


def detect_id_title(folder_name: str, readme: str) -> tuple[str | None, str | None]:
    """Detect problem id and title."""
    folder_match = FOLDER_RE.match(folder_name)
    if folder_match:
        return folder_match.group("id"), folder_match.group("title")

    for link in README_LINK_RE.findall(readme):
        url_match = re.search(r"/(?:problem|lessons|exam)/(\d+)", link)
        if url_match:
            readme_id = url_match.group(1)
            break
    else:
        readme_id = None

    title_match = README_TITLE_RE.search(normalize(readme))
    readme_title = title_match.group("title") if title_match else None
    readme_title_id = title_match.group("id") if title_match else None
    return readme_id or readme_title_id, readme_title


def detect_java_categories(problem_dir: Path, root: Path, readme: str, code: str) -> set[str]:
    """Detect Java categories from README, path, and code."""
    scope = "\n".join(
        [
            normalize(readme_section(readme)),
            to_rel(root, problem_dir).lower(),
            normalize(code).lower(),
        ]
    )
    return detect_categories(scope, JAVA_KEYWORDS)


def detect_sql_categories(readme: str, code: str) -> set[str]:
    """Detect SQL categories from README and query text."""
    scope = "\n".join([normalize(readme_section(readme)).lower(), normalize(code).lower()])
    return detect_categories(scope, SQL_KEYWORDS)


def detect_categories(scope: str, keyword_map: dict[str, list[str]]) -> set[str]:
    """Collect matching categories from a text scope."""
    hits: set[str] = set()
    for category, keywords in keyword_map.items():
        for keyword in keywords:
            if keyword.lower() in scope:
                hits.add(category)
                break
    return hits


def pick_category(priority: list[str], hits: set[str]) -> str:
    """Pick the first matching category by priority."""
    for category in priority:
        if category in hits:
            return category
    return "Etc"


def detect_dbms(problem_dir: Path, root: Path, readme: str, code: str) -> str | None:
    """Detect SQL DBMS with simple path/readme/code rules."""
    parts = {part.lower() for part in problem_dir.relative_to(root).parts}
    readme_lower = readme.lower()
    code_lower = code.lower()

    if "mysql" in parts:
        return "MySQL"
    if "oracle" in parts:
        return "Oracle"
    if "mysql" in readme_lower:
        return "MySQL"
    if "oracle" in readme_lower:
        return "Oracle"

    mysql_hits = sum(1 for hint in MYSQL_HINTS if hint in code_lower)
    oracle_hits = sum(1 for hint in ORACLE_HINTS if hint in code_lower)
    if mysql_hits >= 2 and mysql_hits > oracle_hits:
        return "MySQL"
    if oracle_hits >= 2 and oracle_hits > mysql_hits:
        return "Oracle"
    if "프로그래머스" in problem_dir.relative_to(root).parts and oracle_hits == 0 and "oracle" not in readme_lower:
        return "MySQL"
    return None


def build_problem_name(platform: str | None, problem_id: str | None, title: str | None, fallback: str) -> str:
    """Build the final problem folder name."""
    if platform and problem_id and title:
        return f"{PLATFORM_PREFIX[platform]}-{problem_id}. {title}"
    return fallback


def readme_section(readme: str) -> str:
    """Extract README category section when present."""
    match = README_SECTION_RE.search(readme)
    return match.group("body") if match else ""


def apply_moves(root: Path, plans: list[MovePlan]) -> None:
    """Move directories."""
    for plan in plans:
        plan.target.parent.mkdir(parents=True, exist_ok=True)
        if not try_git_mv(root, plan.source, plan.target):
            shutil.move(str(plan.source), str(plan.target))
        cleanup_empty_parents(root, plan.source.parent)


def try_git_mv(root: Path, source: Path, target: Path) -> bool:
    """Try git mv first."""
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


def cleanup_empty_parents(root: Path, current: Path) -> None:
    """Remove empty parent directories."""
    while current != root:
        if any(current.iterdir()):
            break
        current.rmdir()
        current = current.parent


def print_table(root: Path, plans: list[MovePlan], skipped: list[str]) -> None:
    """Print a compact plan table."""
    if not plans:
        print("No moves planned.")
    else:
        rows = [
            [to_rel(root, plan.source), plan.language, plan.category, to_rel(root, plan.target)]
            for plan in plans
        ]
        headers = ["Source", "Lang", "Category", "Target"]
        widths = [len(head) for head in headers]
        for row in rows:
            for i, cell in enumerate(row):
                widths[i] = max(widths[i], len(cell))

        print(" | ".join(headers[i].ljust(widths[i]) for i in range(len(headers))))
        print("-+-".join("-" * width for width in widths))
        for row in rows:
            print(" | ".join(row[i].ljust(widths[i]) for i in range(len(row))))

    if skipped:
        print("\nSkipped")
        for item in skipped[:20]:
            print(f"- {item}")
        if len(skipped) > 20:
            print(f"- ... {len(skipped) - 20} more")


def read_text(path: Path) -> str:
    """Read text safely."""
    if not path.exists():
        return ""
    return path.read_text(encoding="utf-8", errors="replace")


def normalize(text: str) -> str:
    """Collapse unusual whitespace."""
    return SPACE_RE.sub(" ", text).strip()


def to_rel(root: Path, path: Path) -> str:
    """Return a repo-relative POSIX path."""
    return path.relative_to(root).as_posix()


if __name__ == "__main__":
    raise SystemExit(main())
