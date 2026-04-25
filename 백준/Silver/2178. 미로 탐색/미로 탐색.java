import java.io.*;
import java.util.*;

public class Main {

	static int N,M;
	static int[][] map;
	static int[] dx={0,0,-1,1};
	static int[] dy={-1,1,0,0};
	static boolean[][] visited;

	static class Node{
		int x;
		int y;
		int dist;
		Node(int x,int y, int dist){
			this.x=x;
			this.y=y;
			this.dist=dist;
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for(int i=0;i<N;i++){
			String s = br.readLine();
			for(int j=0;j<M;j++){
				map[i][j] = s.charAt(j)-'0';
			}
		}

		System.out.println(bfs()+1);
	}

	static int bfs(){
		Queue<Node> q= new LinkedList<>();

		q.offer(new Node(0,0,0));
		visited[0][0] = true;

		while(!q.isEmpty()){
			Node curr = q.poll();

			if(curr.x==N-1 && curr.y==M-1){
				return curr.dist;
			}

			for(int i=0;i<4;i++){
				int nx = curr.x+dx[i];
				int ny = curr.y+dy[i];

				if(nx<0 || ny<0 || nx>=N || ny>=M){
					continue;
				}

				if(map[nx][ny]==1 && !visited[nx][ny]){
					visited[nx][ny]=true;
					q.offer(new Node(nx,ny, curr.dist+1));
				}
			}
		}
		return -1;
	}
}
