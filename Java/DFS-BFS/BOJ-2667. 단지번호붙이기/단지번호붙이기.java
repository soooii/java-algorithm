import java.util.*;
import java.io.*;

public class Main{

    static int[][] map;
    static boolean[][] visited;
    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};
    static int N;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            String s =br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = s.charAt(j)-'0';
            }
        }
        visited = new boolean[N][N];
        count=1;

        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==1 && !visited[i][j]){
                    dfs(i,j);
                    result.add(count);
                    count=1; 
                }
            }
        }

        int l = result.size();
        Collections.sort(result);
        System.out.println(l);
        for(int i=0;i<l;i++){
            System.out.println(result.get(i));
        }

    }
    public static void dfs(int x, int y){
        visited[x][y] = true;

        for(int i=0; i<4; i++){
            int nextX= x +dx[i];
            int nextY= y +dy[i];

            if(nextX<0 || nextY<0 || nextX>=N || nextY>=N) continue;

            if(visited[nextX][nextY] || map[nextX][nextY] ==0) continue;

            count ++;
            dfs(nextX, nextY);
        }



    }


}