import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n; // 정점의 개수
    static int m; // 간선의 개수
    static int[][] array; // 인접행렬 (연결 상태 표현)
    static boolean[] visited; // 방문 여부
    static int count = 0; // 연결 요소의 개수

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //정점의 개수와 간선의 개수를 읽어옴
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        array = new int[n+1][n+1];
        visited = new boolean[n+1];

        // m개의 간선 정보 읽어오기
        for(int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            // 무방향 그래프이므로 u에서 v로의 간선과 v에서 u로의 간선 모두 연결로 표시
            array[u][v] = 1;
            array[v][u] = 1;
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                // 방문하지 않은 정점에서 dfs 실행
                dfs(i);
                //연결 요소 개수 증가
                count++;
            }
        }
        System.out.println(count);
    }

    // DFS (깊이 우선 탐색) 메서드
    static void dfs(int i) {

        // 현재 정점이 이미 방문되었으면 종료
        if (visited[i]) {
            return;
        }

        // 현재 정점을 방문 처리
        visited[i] = true;

        // 현재 정점과 연결된 모든 정점에 대해 dfs 실행
        for (int j = 1; j <= n; j++) {
            if (array[i][j] == 1) {
                // 연결된 정점 j에 대해 dfs 재귀
                dfs(j);
            }
        }
    }
}
