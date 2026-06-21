import java.util.*;
class Solution {
    boolean[] visited;
    int N;
    public int solution(int n, int[][] computers) {
        visited=new boolean[n];
        N=n;
        int count=0;;
        for(int i=0;i<n;i++){
           if(!visited[i]){
               dfs(i,computers);
               count++;
           }
        }
        
        return count;
    }
    
    void dfs(int i,int[][] computers){
        visited[i]=true;
        
        for(int j=0;j<N;j++){
            if(i != j && computers[i][j]==1 && !visited[j]){
                dfs(j,computers);
            }
        }
    }
}