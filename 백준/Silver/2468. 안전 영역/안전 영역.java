import java.io.*;
import java.util.*;

public class Main{

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int max;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int count;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];


        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(max < map[i][j]) max = map[i][j];
            }
        }


        count=0;
        answer=0;

        if (max == 0) {
            System.out.println(1);
            return;
        }

        for(int k=0;k<=max;k++){
            visited = new boolean[N][N];
            count = 0; // k마다 초기화

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++) {
                    if (map[i][j] > k && !visited[i][j]) {
                        dfs(i, j, k);
                        count++;
                    }
                }
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);





    }

    static void dfs(int x, int y, int k){
        visited[x][y] = true;

        for(int i=0;i<4;i++){
            int nextX = x+dx[i];
            int nextY = y+dy[i];

            if(nextX<0 || nextX>=N || nextY<0 || nextY>=N) continue;
            if(map[nextX][nextY]<=k || visited[nextX][nextY]) continue;

            dfs(nextX,nextY, k);

        }
    }
}
