import java.io.*;
import java.util.*;

public class Main {
    static int N, answer = Integer.MAX_VALUE;
    static int[][] map;       
    static boolean[] visited;  

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0] = true;
        backTracking(0, 0, 0, 0);

        System.out.println(answer);
    }

    /**
     * 백트래킹을 이용한 경로 탐색
     * @param start : 여행을 시작한 최초의 도시 (나중에 돌아와야 함)
     * @param now   : 현재 내가 머물고 있는 도시
     * @param sum   : 현재까지 이동하며 발생한 누적 비용
     * @param depth : 현재까지 방문한 도시의 수 (시작 도시 제외)
     */
    static void backTracking(int start, int now, int sum, int depth) {
    
		    if (sum >= answer) return;
        
        if (depth == N - 1) { 
            if (map[now][start] != 0) { 
                answer = Math.min(answer, sum + map[now][start]);
            }
            return;
        }


        for (int i = 0; i < N; i++) {
            if (!visited[i] && map[now][i] != 0) {
                
                visited[i] = true;
                
                backTracking(start, i, sum + map[now][i], depth + 1);
                
                visited[i] = false; 
            }
        }
    }
}