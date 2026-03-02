import java.util.*;
import java.io.*;

public class Main {
    static int A, B;
    static boolean[] visited;

    static class Node {
        int num;
        String command;

        Node(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i=0;i<T;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new boolean[10000]; // 매 케이스마다 초기화
            System.out.println(bfs());
        }
    }

    static String bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(A, ""));
        visited[A] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.num == B) {
                return curr.command;
            }

            // D, S, L, R 4가지 방향으로 탐색
            String[] ops = {"D", "S", "L", "R"};
            for (String op : ops) {
                int nextNum = calculate(curr.num, op);

                if (!visited[nextNum]) {
                    visited[nextNum] = true;
                    // 현재까지의 명령어에 방금 수행한 명령어(op)를 더해줌
                    q.add(new Node(nextNum, curr.command + op));
                }
            }
        }
        return "";
    }

    static int calculate(int n, String alpha) {
        switch (alpha) {
            case "D":
                n = (n * 2) % 10000;
                break;
            case "S":
                n = (n == 0) ? 9999 : n - 1;
                break;
            case "L":
                // 왼쪽 회전: 1234 -> 2341
                n = (n % 1000) * 10 + (n / 1000);
                break;
            case "R":
                // 오른쪽 회전: 1234 -> 4123
                n = (n % 10) * 1000 + (n / 10);
                break;
        }
        return n;
    }
}