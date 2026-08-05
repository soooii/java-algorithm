import java.util.*;
import java.io.*;

public class Main {
    // 이동 방향 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static String target = "123456780"; // 도달해야 할 목표 상태

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = ""; // 초기 상태를 담을 문자열

        // 1. 입력 받기 (3x3 격자를 하나의 문자열로 합침)
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                origin += st.nextToken();
            }
        }

        // 2. BFS 시작
        System.out.println(bfs(origin));
    }

    static int bfs(String start) {
        Queue<String> q = new LinkedList<>();
        // map: <퍼즐 모양, 이동 횟수> 저장 (방문 체크 겸용)
        Map<String, Integer> map = new HashMap<>();

        q.add(start);
        map.put(start, 0);

        while (!q.isEmpty()) {
            String curr = q.poll();
            
            // 목표 상태에 도달했다면 현재까지의 이동 횟수 반환
            if (curr.equals(target)) {
                return map.get(curr);
            }

            int zeroIndex = curr.indexOf('0'); // 빈칸(0)의 위치
            int r = zeroIndex / 3; // 2차원 행 좌표
            int c = zeroIndex % 3; // 2차원 열 좌표

            for (int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                // 3x3 격자 범위를 벗어나지 않는 경우에만 이동
                if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3) {
                    int nextIndex = nr * 3 + nc; // 이동할 곳의 1차원 인덱스

                    // 문자열에서 '0'과 숫자의 위치를 바꿈 (Swap)
                    char[] nextArr = curr.toCharArray();
                    char temp = nextArr[zeroIndex];
                    nextArr[zeroIndex] = nextArr[nextIndex];
                    nextArr[nextIndex] = temp;
                    String nextStr = new String(nextArr);

                    // 처음 보는 퍼즐 모양이라면 큐에 추가
                    if (!map.containsKey(nextStr)) {
                        map.put(nextStr, map.get(curr) + 1);
                        q.add(nextStr);
                    }
                }
            }
        }

        // 큐가 빌 때까지 target을 못 찾으면 불가능한 경우
        return -1;
    }
}