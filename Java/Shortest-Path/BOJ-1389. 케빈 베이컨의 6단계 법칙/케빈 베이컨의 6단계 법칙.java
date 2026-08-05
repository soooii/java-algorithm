import java.io.*;
import java.util.*;

public class Main{
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int cntsum;

    static class Node{
        int curr;
        int cnt;

        Node(int curr, int cnt){
            this.curr=curr;
            this.cnt=cnt;
        }
    }

    // 자신 제외하고 모든 친구하고 연결될 수 있는 합
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        graph = new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // get: 지정된 위치(index)에 저장된 객체를 반환한다.
            graph.get(a).add(b);
            graph.get(b).add(a);

        }
        int min=Integer.MAX_VALUE;
        int minperson=-1;

        //1~N
        for(int j=1; j<=N; j++){
            cntsum=0;
            for(int i=1;i<=N;i++){
                visited = new boolean[N+1];
                if(j==i){
                    continue;
                }
                cntsum += bfs(j,i);


            }
            //어차피 for문 돌면서 작은 번호부터라서 같아도 작은 사람 출력됨
            if(min>cntsum){
                min=cntsum;
                minperson=j;
            }
        }

        System.out.println(minperson);


    }

    // 연결은 최단거리로 구해야함
    static int bfs(int current, int end){
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(current,0));
        visited[current] = true;

        while(!q.isEmpty()){
            Node curr = q.poll();

            if(curr.curr==end){
                return curr.cnt;
            }

            for(int next: graph.get(curr.curr)){
                if(!visited[next]){
                    visited[next] = true;
                    q.add(new Node(next, curr.cnt+1));
                }
            }

        }

        return 0;
    }
}