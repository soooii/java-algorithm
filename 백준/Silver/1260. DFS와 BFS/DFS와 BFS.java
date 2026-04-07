import java.io.*;
import java.util.*;

public class Main {

	static int M,N,V;
	static int[][] map;
;
	static boolean[] visitedDfs;
	static boolean[] visitedBfs;
	static List<Integer> Dfs=new ArrayList<>();
	static List<Integer> Bfs=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); //정점
		M = Integer.parseInt(st.nextToken()); //간선
		V = Integer.parseInt(st.nextToken()); //시작 정점

		map=new int[N+1][N+1];
		visitedDfs=new boolean[N+1];
		visitedBfs=new boolean[N+1];

		for(int i=0;i<M;i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int a =Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());

			map[a][b]=map[b][a]=1;
		}

		dfs(V);

		StringBuilder st1= new StringBuilder();
		for(int num:Dfs){
			st1.append(num+" ");
		}

		System.out.println(st1);

		bfs(V);

		StringBuilder st2= new StringBuilder();
		for(int num:Bfs){
			st2.append(num+" ");
		}

		System.out.println(st2);




	}

	static void dfs(int start){
		visitedDfs[start]=true;
		Dfs.add(start);
		for(int i=1;i<=N;i++){
			if(map[start][i]==1 && !visitedDfs[i]){
				dfs(i);
			}
		}
	}

	static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();

		q.offer(start);
		Bfs.add(start);
		visitedBfs[start]=true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for(int i=1;i<=N;i++){
				if(map[cur][i]==1 && !visitedBfs[i]){
					visitedBfs[i]= true;
					Bfs.add(i);
					q.add(i);
				}
			}
		}

		return -1;
	}

}