import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] map;
    static boolean[][][] visited; // *중요*
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int min = Integer.MAX_VALUE;

    static class Node {
        int x;
        int y;
        int dist;
        int count;

        Node(int x, int y, int dist, int count) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int what = s.charAt(j)-'0';
                map[i][j] = what;
            }
        }

        bfs(0,0);

        System.out.println(min);
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(x,y,1,0));
        visited[x][y][0] = true;

        // BFS 탐색
        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.x == N - 1 && curr.y == M - 1) {
                min = curr.dist;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }


                if(map[nx][ny]==0){
                    if(!visited[nx][ny][curr.count]){
                        visited[nx][ny][curr.count]=true;
                        q.add(new Node(nx,ny,curr.dist+1, curr.count));
                    }

                }
                else{ //막혀있을때
                    if (curr.count ==0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        q.add(new Node(nx,ny,curr.dist+1,1));
                    }
                }
            }
        }
        min=-1;
    }
}