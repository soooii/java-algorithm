import java.io.*;
import java.util.*;

public class Main{

    static int n;
    static int m;
    static int v;
    static int[][] arr;
    static boolean[] visited; // Java에서 boolean 배열의 기본 초기값은 false
    static StringBuilder sbdfs = new StringBuilder();
    static StringBuilder sbbfs = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String p = br.readLine();
        String[] pp = p.split(" ");
        n = Integer.parseInt(pp[0]); // 정점 개수
        m = Integer.parseInt(pp[1]); // 간선 개수
        v = Integer.parseInt(pp[2]); // 탐색 시작할 정점 번호

        // 방문할 수 있는 정점이 여러 개인 경우 정점 번호가 작은 것부터 방문
        // 더 이상 방문할 수 있는 점 없는 경우 종료
        // 정점번호 1~N
        arr = new int[n+1][n+1]; // 정점

        for(int i=0;i<m;i++){
            String m1 = br.readLine();
            String[] m2 = m1.split(" ");

            // 간선이 연결하는 두 정점 번호
            int x = Integer.parseInt(m2[0]);
            int y = Integer.parseInt(m2[1]);

            // 1,2면 1->2, 2->1로 갈 수 있으니...
            arr[x][y] = arr[y][x] = 1; // 연결되어있다 의미
        }

        visited = new boolean[n+1];
        dfs(v);

        visited = new boolean[n + 1];
        bfs(v);

        System.out.println(sbdfs);
        System.out.println(sbbfs);

    }

    // 재귀함수 이용
    public static void dfs(int start){
        visited[start] = true;
        sbdfs.append(start).append(" ");
        for(int node=1; node<=n; node++){
            // 연결된 노드 방문하지 않았으면
            if(arr[start][node]==1 && visited[node]==false){
                dfs(node); // 연결된 노드부터 다시 탐색
            }
        }
    }

    // 큐 -> linkedList로 구현 / 선입선출, 줄기다리기
    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        visited[start]=true;
        sbbfs.append(start).append(" ");
        // 연결된 노드부터 탐색하는 DFS와 다르게
        // 방문하지 않은 노드는 큐에 넣어주고
        // poll을 통해 작은 정점 번호부터 탐색 시작
        // 큐에 정점이 없을 때까지
        while(!queue.isEmpty()){
            int temp = queue.poll(); // 큐에서 가장 앞 담겨져있는 번호
            for(int node=1; node<=n;node++){
                // 연결된 노드 방문하지 않았으면
                if(arr[temp][node]==1 && visited[node]==false){
                    queue.add(node); // 큐에 넣어줘 줄 서게 함
                    visited[node] = true;
                    sbbfs.append(node).append(" ");
                }
            }
        }
    }

}