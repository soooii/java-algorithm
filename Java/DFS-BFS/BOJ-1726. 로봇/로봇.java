import java.io.*;
import java.util.*;

public class Main {

	static int M,N;
	static int[][] map;

	static int endRow,endCol,endDir;

	// 1: 동, 2: 서, 3: 남, 4: 북
	// 방향 번호를 1부터 그대로 쓰기위해 0,0 맨앞
	static int[] dr = {0, 0, 0, 1, -1};
	static int[] dc = {0, 1, -1, 0, 0};

	static class Node{
		int x,y, dir, count;

		Node(int x, int y, int dir, int count){
			this.x=x;
			this.y=y;
			this.dir=dir;
			this.count=count;
		}
	}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for(int i=0;i<M;i++){
			StringTokenizer str = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				int num = Integer.parseInt(str.nextToken());
				map[i][j] = num;
			}
		}

		StringTokenizer str1 = new StringTokenizer(br.readLine());
		int strartRow = Integer.parseInt(str1.nextToken())-1;
		int startCol = Integer.parseInt(str1.nextToken())-1;
		int startDir = Integer.parseInt(str1.nextToken());

		StringTokenizer str2 = new StringTokenizer(br.readLine());
		endRow = Integer.parseInt(str2.nextToken()) -1;
		endCol = Integer.parseInt(str2.nextToken())-1;
		endDir = Integer.parseInt(str2.nextToken());

		int answer = bfs(strartRow,startCol,startDir);
        System.out.println(answer);

    }

	static int bfs(int startRow, int startCol, int startDir) {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[M][N][5];

		q.offer(new Node(startRow, startCol, startDir, 0));
		visited[startRow][startCol][startDir] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.x == endRow && cur.y == endCol && cur.dir == endDir) {
				return cur.count;
			}

			// 1. Turn left
			int leftDir = turnLeft(cur.dir);
			if (!visited[cur.x][cur.y][leftDir]) {
				visited[cur.x][cur.y][leftDir] = true;
				q.offer(new Node(cur.x, cur.y, leftDir, cur.count + 1));
			}

			// 2. Turn right
			int rightDir = turnRight(cur.dir);
			if (!visited[cur.x][cur.y][rightDir]) {
				visited[cur.x][cur.y][rightDir] = true;
				q.offer(new Node(cur.x, cur.y, rightDir, cur.count + 1));
			}

			// 3. Go 1~3
			for (int k = 1; k <= 3; k++) {
				int nx = cur.x + dr[cur.dir] * k;
				int ny = cur.y + dc[cur.dir] * k;

				if (nx < 0 || nx >= M || ny < 0 || ny >= N) break;
				if (map[nx][ny] == 1) break;

				if (!visited[nx][ny][cur.dir]) {
					visited[nx][ny][cur.dir] = true;
					q.offer(new Node(nx, ny, cur.dir, cur.count + 1));
				}
			}
		}

		return -1;
	}

	static int turnLeft(int d) {
		if (d == 1) return 4; // 동 -> 북
		if (d == 2) return 3; // 서 -> 남
		if (d == 3) return 1; // 남 -> 동
		return 2;             // 북 -> 서
	}

	static int turnRight(int d) {
		if (d == 1) return 3; // 동 -> 남
		if (d == 2) return 4; // 서 -> 북
		if (d == 3) return 2; // 남 -> 서
		return 1;             // 북 -> 동
	}

}
