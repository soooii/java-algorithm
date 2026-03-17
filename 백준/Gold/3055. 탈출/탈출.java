import java.io.*;
import java.util.*;

public class Main{
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int R,C;
    static int startx,starty;

    // 물 시작 위치 미리 모두 넣기 위한 큐
    static Queue<Node> waterq=new LinkedList<>();
    static int[][] water;

    static class Node{
        int x;
        int y;
        int day;
        Node(int x, int y, int day){
            this.x=x;
            this.y=y;
            this.day=day;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        R = Integer.parseInt(str.nextToken()); // 행
        C = Integer.parseInt(str.nextToken()); // 열

        map = new char[R][C];
        visited = new boolean[R][C];
        water = new int[R][C];

        // 아직 물이 오지않은 칸(-1) 과 원래 물인 칸(0) 구분 필요
        // 2차원 배열 전부 -1 초기화
        for(int i=0;i<R;i++){
            Arrays.fill(water[i], -1);
        }

        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                if(s.charAt(j)=='S'){
                    startx=i;
                    starty=j;
                }
                else if(s.charAt(j)=='*'){
                    waterq.add(new Node(i,j,0));
                    water[i][j]=0;
                }

                map[i][j] = s.charAt(j);
            }
        }

        spreadwater();

        int answer = bfs(startx,starty);

        if(answer==-1){
            System.out.println("KAKTUS");
        }
        else{
            System.out.println(answer);
        }


    }

    static void spreadwater() {
        while (!waterq.isEmpty()) {
            Node curr = waterq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                // 주의: R-1, C-1까지임
                if (nx >= R || ny >= C || nx < 0 || ny < 0) {
                    continue;
                }

                // 물은 빈칸으로만 퍼지고, 아직 물이 안 온 칸이어야 함
                if (map[nx][ny] == '.' && water[nx][ny] == -1) {
                    water[nx][ny] = curr.day + 1; // 이때 물이 차오릅니다
                    waterq.add(new Node(nx, ny, curr.day + 1));
                }
            }
        }
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(x,y,0));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node curr = q.poll();

            for(int i=0;i<4;i++){
                int nx = curr.x+dx[i];
                int ny = curr.y+dy[i];
                int nday = curr.day+1;

                // 주의: R-1, C-1까지임
                if(nx>=R || ny>=C || nx<0 || ny<0){
                    continue;
                }

                // 돌
                if(map[nx][ny] == 'X'){
                    continue;
                }

                // 도착점
                if(map[nx][ny] == 'D'){
                    return nday;
                }

                // 빈칸일 때만 이동 가능
                // 단 물이 안 오거나 / 물보다 먼저 도착해야 함 (물 차오름 예정 칸 못감)
                if(!visited[nx][ny] && map[nx][ny]=='.' &&  (water[nx][ny]==-1 || nday<water[nx][ny])){

                    visited[nx][ny] = true; // char 비어있음
                    q.add(new Node(nx, ny, nday));
                }
            }



        }

        return -1;
    }
}
