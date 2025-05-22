import java.io.*;

import java.util.*;

public class Main {
    static int n;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n + 1];
        parent = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            String[] split = br.readLine().split(" ");
            int u = Integer.parseInt(split[0]);
            int v = Integer.parseInt(split[1]);

            // 양방향 간선 저장 (연결)
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(1);

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    static void dfs(int current) {
        visited[current] = true;
        for (int next : graph.get(current)) {
            if (!visited[next]) {
                parent[next] = current;
                dfs(next);
            }
        }
    }
}
