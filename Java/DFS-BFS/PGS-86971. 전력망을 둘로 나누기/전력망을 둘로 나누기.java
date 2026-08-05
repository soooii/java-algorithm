import java.util.*;
import java.io.*;
class Solution {
    
    static boolean[] visited;
    static int count;
    static int n;
    static int[][] wires;
    
    static int[][] map;
    public int solution(int n, int[][] wires) {
        this.n=n;
        map=new int[n+1][n+1];
        visited = new boolean[n-1];
        
        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            map[v1][v2] = map[v2][v1] = 1;
        }
        
        int min=n+1;
        
        for(int i=1;i<=n;i++){
            count=0; //하나의 dfs 영역을 이루고 있는 개수
            for(int j=1;j<=n;j++){
                //연결끊기
                if(map[i][j]==1){
                    map[i][j]=map[j][i]=0;
                    
                    visited = new boolean[n + 1]; 
                    count = 1;
                    
                    dfs(j);
                    
                    int w=n-count; //반대편 영역 개수
                    min=Math.min(min,Math.abs(w-count)); //차이
            
                    //원상복구
                    map[i][j]=map[j][i]=1;
                }
                
            }
           
        }
        return min;
        
    }
    
    void dfs(int start){
        visited[start]=true;
        for(int i=1;i<=n;i++){
            if(map[start][i]==1 && !visited[i]){
                count++;
                dfs(i);
            }
        }
    }
}