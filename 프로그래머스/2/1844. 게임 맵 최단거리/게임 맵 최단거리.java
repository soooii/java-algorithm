import java.util.*;
class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n,m;
    static boolean[][] visited;
    
    class Node{
        int x,y,dist;
        public Node(int x, int y, int dist){
            this.x=x;
            this.y=y;
            this.dist=dist;
        }
    }
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        
        return bfs(0,0, maps);
        
        
        
    }
    
    public int bfs(int x, int y, int [][] maps){
        Queue<Node> q = new LinkedList<>();
        
        // 시작점 삽입, 시작 dist 1
        q.offer(new Node(x,y,1));
        visited[x][y]=true;
        
        while(!q.isEmpty()){
            Node cur= q.poll();
        
            if(cur.x==n-1 && cur.y==m-1) return cur.dist;
        
        
            for(int i=0;i<4;i++){
                int nextX = cur.x+dx[i];
                int nextY = cur.y+dy[i];
                
                if(nextX>=n || nextY>=m || nextX<0 || nextY<0) continue; 

            
                if( maps[nextX][nextY]==1 && !visited[nextX][nextY]){
                    visited[nextX][nextY]=true;
                    q.offer(new Node(nextX,nextY,cur.dist+1));
            }
        }
        }
        return -1;
    }
}