import java.util.*;
class Solution {
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    static boolean[][] visited;
    static int n,m;
    
    static class Node{
        int x,y, dist;
        
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
        
        int answer = bfs(0,0,maps);
        
        return answer;
        
    }
    
    static int bfs(int x, int y, int[][] maps){
        Queue<Node> q = new LinkedList<>();
        
        q.offer(new Node(x,y,1));
        visited[x][y]=true;
        
        while(!q.isEmpty()){
            Node current = q.poll();
            
            if(current.x==n-1 && current.y==m-1){
                return current.dist;
            }
            
            for(int i=0;i<4;i++){
                int nx=current.x+dx[i];
                int ny=current.y+dy[i];
                
                if(nx>=0 && ny>=0 && nx<n && ny<m){
                    if(maps[nx][ny]==1 && !visited[nx][ny]){
                        visited[nx][ny]=true;
                        q.offer(new Node(nx,ny,current.dist+1));
                    }
                }
            }
        }
        
        return -1;
    }
}