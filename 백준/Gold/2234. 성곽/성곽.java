import java.io.*;
import java.util.*;

public class Main {

	static int M,N;
	static int[][] map;
	static int[][] roomNum;
	static int room=1;
	static boolean[][] visited;
	static int[] roomSize;

	// 1: 동, 2: 서, 3: 남, 4: 북
	static int[] dr = {0, 0, 0, 1, -1};
	static int[] dc = {0, 1, -1, 0, 0};

	static class Node{
		int x,y;

		Node(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		roomNum = new int[M][N];
		roomSize = new int[M*N+1];
		visited = new boolean[M][N];

		for(int i=0;i<M;i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str.nextToken());
			}

		}

		// 1. 방 번호 붙이기 + 각 방 크기 구하기
		int maxRoomSize =-1;
		for(int i=0;i<M;i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]){
					int size = bfs(i,j);
					roomSize[room] =size;
					maxRoomSize = Math.max(maxRoomSize, size);
					room++;
				}
			}

		}

		// 2. 벽 하나 제거했을 때 최대 방 크기
		int maxAfterBreak = 0;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 1; d <= 4; d++) {
					int nx = i + dr[d];
					int ny = j + dc[d];

					if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

					int roomA = roomNum[i][j];
					int roomB = roomNum[nx][ny];

					if (roomA != roomB) {
						maxAfterBreak = Math.max(maxAfterBreak, roomSize[roomA] + roomSize[roomB]);
					}
				}
			}
		}


		/*
		방을 하나 처리할 때마다 마지막에 room++
		모든 방 처리가 끝난 뒤 room은 다음에 붙일 번호가 됨
		따라서 -1 필요
		 */
		System.out.println(room-1); // 방 개수
		System.out.println(maxRoomSize); // 가장 큰 방
		System.out.println(maxAfterBreak); // 벽 하나 제거 후 최대 크기


	}

	static int bfs(int startRow, int startCol) {
		Queue<Node> q = new LinkedList<>();

		q.offer(new Node(startRow, startCol));
		visited[startRow][startCol] = true;
		roomNum[startRow][startCol] =room;

		int size=0;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			size++;

			int num = map[cur.x][cur.y];

			int nextX= cur.x;
			int nextY= cur.y; // 초기화 필요

			// 비트연산, 0이 아니면 벽이 있음 -> 막혀있다
			
			// 0일 경우에만 이동가능
			if ((num&1) ==0){ //서
				nextX=cur.x+dr[2];
				nextY=cur.y+dc[2];
				if(nextX>=M || nextY>=N || nextX<0 || nextY<0) {
					continue;
				}
				if(!visited[nextX][nextY]){
					visited[nextX][nextY]=true;
					roomNum[nextX][nextY] =room;
					q.add(new Node(nextX,nextY));
				}
			}
			if ((num&2) ==0){ //북
				nextX=cur.x+dr[4];
				nextY=cur.y+dc[4];
				if(nextX>=M || nextY>=N || nextX<0 || nextY<0){
					continue;
				}
				if(!visited[nextX][nextY]){
					visited[nextX][nextY]=true;
					roomNum[nextX][nextY] =room;
					q.add(new Node(nextX,nextY));
				}
			}
			if ((num&4) ==0){//동
				nextX=cur.x+dr[1];
				nextY=cur.y+dc[1];
				if(nextX>=M || nextY>=N || nextX<0 || nextY<0){
					continue;
				}
				if(!visited[nextX][nextY]){
					visited[nextX][nextY]=true;
					roomNum[nextX][nextY] =room;
					q.add(new Node(nextX,nextY));
				}
			}
			if ((num&8) ==0){ //남
				nextX=cur.x+dr[3];
				nextY=cur.y+dc[3];
				if(nextX>=M || nextY>=N || nextX<0 || nextY<0){
					continue;
				}
				if(!visited[nextX][nextY]){
					visited[nextX][nextY]=true;
					roomNum[nextX][nextY] =room;
					q.add(new Node(nextX,nextY));
				}
			}
		}

		return size;
	}

}