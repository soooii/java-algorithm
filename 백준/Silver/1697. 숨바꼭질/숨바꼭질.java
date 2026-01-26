import java.util.*;
import java.io.*;

public class Main {

    // 위치의 최대값
    static final int MAX = 100000;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈이 위치
        int N = Integer.parseInt(st.nextToken());

        // 동생 위치
        int K = Integer.parseInt(st.nextToken());

        // 해당 위치를 이미 방문했는지 체크하는 배열
        boolean[] visited = new boolean[MAX + 1];

        // 각 위치까지 도달하는 데 걸린 시간을 저장하는 배열
        int[] time = new int[MAX + 1];

        // BFS에 사용할 큐 선언
        Queue<Integer> q = new LinkedList<>();

        // 시작 위치를 큐에 넣음
        q.offer(N);

        // 시작 위치는 방문 처리
        visited[N] = true;

        // 큐가 빌 때까지 반복 (BFS)
        while (!q.isEmpty()) {

            // 현재 위치 꺼내기
            int cur = q.poll();

            // 현재 위치가 동생 위치라면
            if (cur == K) {
                // BFS이므로 이 시간이 최단 시간
                System.out.println(time[cur]);
                return;
            }

            // X - 1로 이동 (걷기)
            if (cur - 1 >= 0 && !visited[cur - 1]) {
                visited[cur - 1] = true;          // 방문 처리
                time[cur - 1] = time[cur] + 1;   // 시간 1초 증가
                q.offer(cur - 1);                 // 큐에 삽입
            }

            // X + 1로 이동 (걷기)
            if (cur + 1 <= MAX && !visited[cur + 1]) {
                visited[cur + 1] = true;
                time[cur + 1] = time[cur] + 1;
                q.offer(cur + 1);
            }

            // 2 * X로 이동 (순간이동)
            if (cur * 2 <= MAX && !visited[cur * 2]) {
                visited[cur * 2] = true;
                time[cur * 2] = time[cur] + 1;
                q.offer(cur * 2);
            }
        }
    }
}
