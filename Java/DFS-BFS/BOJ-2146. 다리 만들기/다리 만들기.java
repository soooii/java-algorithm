import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;   // DFS에서 섬 번호 붙일 때 사용할 방문 배열
    static int[][] what;          // 각 칸이 몇 번 섬인지 저장
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int area = 1; // 섬 번호를 1번부터 붙이기 위해 1로 시작
    static int min = Integer.MAX_VALUE;

    static class Node {
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        what = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. DFS로 섬마다 번호 붙이기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    dfs(i, j, area);
                    area++;
                }
            }
        }

        // 2. 각 섬마다 BFS 수행
        for (int island = 1; island < area; island++) {
            bfs(island);
        }

        System.out.println(min);
    }

    // 같은 섬에 같은 번호 붙이기
    static void dfs(int x, int y, int areaNum) {
        visited[x][y] = true;
        what[x][y] = areaNum;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }

            if (!visited[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny, areaNum);
            }
        }
    }

    // 특정 섬에서 출발해서 다른 섬까지의 최단 거리 구하기
    static void bfs(int island) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visitedBfs = new boolean[N][N];

        // 1. 현재 섬에 속한 모든 칸을 큐에 넣음
        //  섬 전체에서 동시에 바다로 퍼져나가게 함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (what[i][j] == island) {
                    q.add(new Node(i, j, 0));
                    visitedBfs[i][j] = true;
                }
            }
        }

        // 2. BFS 탐색
        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (visitedBfs[nx][ny]) continue;

                // 다른 섬을 만난 경우
                // curr.dist가 지금까지 건넌 바다 칸 수이므로 그 값이 다리 길이
                // 바다일 경우는 0으로 초기화 되기때문에 피함
                if (what[nx][ny] != 0 && what[nx][ny] != island) {
                    min = Math.min(min, curr.dist);
                    return;
                }

                // 바다인 경우에만 계속 확장
                if (map[nx][ny] == 0) {
                    visitedBfs[nx][ny] = true;
                    q.add(new Node(nx, ny, curr.dist + 1));
                }
            }
        }
    }
}