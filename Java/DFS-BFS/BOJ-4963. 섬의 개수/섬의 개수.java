import java.io.*;

public class Main {
    static int w; 
    static int h; 
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = br.readLine();
            String[] split = s.split(" ");
            w = Integer.parseInt(split[0]); // 가로
            h = Integer.parseInt(split[1]); // 세로
            if (w == 0 && h == 0) break;

            arr = new int[h][w];
            visited = new boolean[h][w];
            int count = 0;

            for (int i = 0; i < h; i++) {
                String[] mapSplit = br.readLine().split(" ");
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(mapSplit[j]);
                }
            }

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (arr[y][x] == 1 && !visited[y][x]) {
                        dfs(y, x);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    public static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < h && nx >= 0 && nx < w) {
                if (!visited[ny][nx] && arr[ny][nx] == 1) {
                    dfs(ny, nx);
                }
            }
        }
    }
}