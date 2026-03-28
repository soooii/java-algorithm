import java.io.*;
import java.util.*;

public class Main {
    // 보드 크기 고정
    static final int N = 12;
    static final int M = 6;

    static char[][] map = new char[N][M];
    static boolean[][] visited = new boolean[N][M];

    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 연쇄 횟수
    static int answer = 0;

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // 더 이상 터질 게 없을 때까지 반복
        while (true) {
            // 이번 턴 방문 배열 초기화
            visited = new boolean[N][M];

            // 이번 턴에 하나라도 터졌는지 확인
            boolean exploded = false;

            // 이번 턴에 터질 모든 좌표를 모아둘 리스트
            List<Node> boomList = new ArrayList<>();

            // 전체 보드를 돌면서 같은 색 그룹 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 빈칸이거나 이미 방문했다면 넘어감
                    if (map[i][j] == '.' || visited[i][j]) continue;

                    // (i, j)에서 시작하는 같은 색 그룹 찾기
                    List<Node> group = bfs(i, j);

                    // 같은 색 뿌요가 4개 이상 연결되어 있으면 터짐
                    if (group.size() >= 4) {
                        exploded = true;
                        boomList.addAll(group);
                    }
                }
            }

            // 이번 턴에 아무것도 안 터졌으면 종료
            if (!exploded) break;

            // 터질 좌표들을 모두 '.'로 바꾸기
            for (Node node : boomList) {
                map[node.x][node.y] = '.';
            }

            // 뿌요 아래로 내리기
            gravity();

            // 한 번의 연쇄가 끝났으므로 answer 증가
            answer++;
        }

        System.out.println(answer);
    }

    static List<Node> bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        List<Node> group = new ArrayList<>();

        // 시작점 색깔 기억
        char color = map[x][y];

        // 시작점 큐에 넣고 방문 처리
        q.offer(new Node(x, y));
        visited[x][y] = true;
        group.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                // 범위 밖이면 제외
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 이미 방문했으면 제외
                if (visited[nx][ny]) continue;

                // 같은 색이 아니면 제외
                if (map[nx][ny] != color) continue;

                // 같은 색이면 방문 처리 후 큐에 넣기
                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
                group.add(new Node(nx, ny));
            }
        }

        // 이 BFS로 찾은 같은 색 그룹 반환
        return group;
    }

    static void gravity() {
        // 열 단위로 처리
        for (int col = 0; col < M; col++) {
            // 이 열에서 다음 뿌요를 내려놓을 위치
            int writeRow = N - 1;

            // 이 열에서 살아있는 뿌요만 찾음
            // 아래에서부터 차례대로 다시 채움

            // 아래에서 위로 올라오면서 뿌요를 확인
            for (int row = N - 1; row >= 0; row--) {
                // 빈칸이 아니면 살아남은 뿌요
                if (map[row][col] != '.') {
                    char temp = map[row][col];

                    // 원래 위치 비우기
                    map[row][col] = '.';

                    // 현재 내려놓아야 할 위치에 뿌요 놓기
                    map[writeRow][col] = temp;

                    // 다음 뿌요는 그 위 칸에 놓아야 하므로 한 칸 올림
                    writeRow--;
                }
            }
        }
    }
}