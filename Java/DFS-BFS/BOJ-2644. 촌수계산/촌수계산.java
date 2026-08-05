import java.io.*;
import java.util.*;
public class Main{


    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] parent;
    static int answer = -1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 전체 사람 수
        String s = br.readLine(); // 사람 번호 2개
        String[] ss = s.split(" ");
        int a = Integer.parseInt(ss[0]);
        int b = Integer.parseInt(ss[1]);

        int m = Integer.parseInt(br.readLine()); // 부모-자식 관계 수

        graph = new ArrayList<>(); // 인접 리스트
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n + 1];

        for(int i=0;i<m;i++){ // 부모-자식
            String[] split = br.readLine().split(" ");
            int u = Integer.parseInt(split[0]);
            int v = Integer.parseInt(split[1]);

            // 양방향 간선 저장
            graph.get(u).add(v);
            graph.get(v).add(u);

        }

        dfs(a,b,0);

        System.out.println(answer);
    }

    static void dfs(int current, int end, int cnt) {
        if(current == end){
            answer = cnt;
            return;
        }
        visited[current] = true;
        for (int next : graph.get(current)) {
            if (!visited[next]) {
                dfs(next, end, cnt+1);
            }
        }
    }
}