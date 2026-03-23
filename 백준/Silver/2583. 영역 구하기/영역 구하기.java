import java.io.*;
import java.util.*;


public class Main{
    static int M,N,K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static int countArea;//연결 요소 개수
    static int count; // 각 연결 요소 넓이
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];
        //사각형 좌표
        for(int i=0;i<K;i++){
            StringTokenizer str = new StringTokenizer(br.readLine());
            //왼 아래
            int x1=Integer.parseInt(str.nextToken());
            int y1=Integer.parseInt(str.nextToken());

            // 오위
            int x2=Integer.parseInt(str.nextToken());
            int y2=Integer.parseInt(str.nextToken());

            // ** y만 뒤집히는 것
            for(int y=y1;y<y2;y++){
                for(int x=x1;x<x2;x++){
                    map[y][x] = 1;
                }
            }

        }

        List<Integer> list = new ArrayList<>();
        // 모든 좌표를 돌지만 1이 아니고 방문하지 않은 곳 기준 시작
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]!=1 && !visited[i][j]){
                    count=0;
                    dfs(i,j);
                    countArea++;
                    list.add(count);
                }
            }
        }

        Collections.sort(list);
        System.out.println(countArea);
        for(int c:list){
            System.out.print(c+" ");
        }

    }

    // y번째 행, x번째 열 (배열은 행,열)
    static void dfs(int y, int x){
        visited[y][x]=true;
        count++; // ** 시작칸도 넓이에 추가하기 위해 
        for(int i=0;i<4;i++){
            int nexty=y+dy[i];
            int nextx=x+dx[i];

            if(nexty>=M || nexty<0 || nextx>=N || nextx<0){
                continue;
            }
            if(visited[nexty][nextx] || map[nexty][nextx]==1){
                continue;
            }
            dfs(nexty,nextx);
        }

    }
}
