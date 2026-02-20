import java.util.*;
import java.io.*;

// 1. 길의 정보를 담는 바구니
class Node {
    int v, cost;
    Node(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = 100_000_000;
    static List<Node>[] graph; // 인접 리스트
    static int[] dist;         // 최단 거리 수첩

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        // 인접 리스트 및 거리 배열 초기화
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        // 최단 거리 수첩 초기화
        dist = new int[V + 1];
        Arrays.fill(dist, INF); // 아직 모름(무한대)로 초기화

        // 간선 정보 입력 (방향 그래프)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // u에서 v로 가는 가중치 w
            graph[u].add(new Node(v, w));
        }

        dijkstra(K);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) sb.append("INF\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dijkstra(int start) {
       
        // 우선순위 큐 (비용이 적은 순서대로 정렬)
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        // 시작점 세팅, 나 자신에게 가는 비용 0원
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.v;
            int nowCost = curr.cost;

            // 이미 더 싼 길을 수첩에 적어놨다면 패스
            if (nowCost > dist[now]) continue;

            // graph를 보고 현재 도시(now)에서 갈 수 있는 다음 도시들을 확인
            for (Node next : graph[now]) {
                // 수첩에 적힌 값보다 '지금 나를 거쳐가는 비용'이 더 싸다면 업데이트
                if (dist[next.v] > dist[now] + next.cost) {
                    dist[next.v] = dist[now] + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}
    