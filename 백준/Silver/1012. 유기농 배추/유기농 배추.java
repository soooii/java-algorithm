import java.io.*;
import java.util.*;

public class Main{

    static int m, n, k;
    static int[][] arr;
    static boolean[][] visited;
    static int count;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };

    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < m && cy < n) {
                if (!visited[cx][cy] && arr[cx][cy] == 1) {
                    dfs(cx, cy);
                }
            }

        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); //테스트 케이스 개수


        for(int j = 0; j < t; j++){
            count = 0;
            String s = br.readLine();
            String[] ss = s.split(" ");
            m = Integer.parseInt(ss[0]); // 배추밭 가로 길이
            n = Integer.parseInt(ss[1]); // 배추밭 세로 길이
            k = Integer.parseInt(ss[2]); // 배추 위치 개수

            arr = new int[m][n]; // 배추
            visited = new boolean[m][n];

            for(int i=0; i<k; i++){
                String s1 = br.readLine();
                String[] s2 =s1.split(" ");
                arr[Integer.parseInt(s2[0])][Integer.parseInt(s2[1])] =1; //배추 존재
            }

            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (arr[x][y] == 1 && !visited[x][y]) {
                        dfs(x, y);
                        count++;
                    }
                }
            }

            System.out.println(count);


        }
        }


}