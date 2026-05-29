import java.io.*;
import java.util.*;

class Solution {
    
    static int[] dx={0,0,1,-1};
    static int[] dy={-1,1,0,0};
    static boolean[][] visited;
    static int[][] map;
    int n,m;
    
    static class Node{
        int dist;
        int x;
        int y;
        Node(int x, int y, int dist){
            this.x=x;
            this.y=y;
            this.dist=dist;
        }
    }

    public int solution(int[][] maps) {
        int row = maps.length;
        int col = maps[0].length;
        visited = new boolean[row][col];
        map = maps;
        n = row-1;
        m = col-1;
        return bfs(0,0);
        
    }

    public int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,1));
        visited[x][y]=true;
        
        while(!q.isEmpty()){
            Node cur = q.poll();
            
            if(cur.x==n && cur.y==m){
                return cur.dist;
            }
            
            for(int i=0;i<4;i++){
                int nx = cur.x+dx[i];
                int ny = cur.y+dy[i];
                
                if(nx<0 || nx>n || ny<0 || ny>m ) continue;
                
                if(map[nx][ny]==0) continue;
                
                if(!visited[nx][ny]){
                    visited[nx][ny]=true;
                    q.offer(new Node(nx,ny,cur.dist+1));
                }
                
            }
        }
        
        return -1;
    }
}

