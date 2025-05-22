import java.io.*;


public class Main{
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = { -1, 0, 0, 1};
    static int[] dy = { 0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];
        // n*n
        // 물에 잠기지 않는 k를 for문으로 -> 최대 구하기
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            String[] split = s.split(" ");

            for(int j=0;j<n;j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

        int maxCount=-1;
        int count = 0;
        for(int i=0;i<=100;i++) {
            //i는 limit
            count=0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    visited[j][k] = false;
                }
            }

            for(int j=0;j<n;j++) {
                for(int k=0;k<n;k++) {
                    if(arr[j][k]>i && !visited[j][k]) {
                        dfs(j,k,i); // 해당 dfs 탐색이 끝나야 이어진 영역의 탐색은 다 끝난 것 
                        count++;
                    }
                }
            }
            if(maxCount<count) {
                maxCount = count;
            }
        }

        System.out.println(maxCount);

        }


    public static void dfs(int y, int x, int limit){
        visited[y][x] = true;

        for(int i=0; i<4; i++){
            int mx = x + dx[i];
            int my = y + dy[i];

            if(mx >= 0 && mx < n && my >= 0 && my < n){ // 범위
                if(!visited[my][mx] && arr[my][mx]>limit){
                    dfs(my,mx, limit);
                }
            }
        }
    }

}