import java.util.*;
class Solution {
    boolean[] visited;
    
    int N;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        N=n;
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(!visited[i]) {
                dfs(i, computers);
                result.add(1); 
            }
        }
        
        return result.size();
    }
    
    public void dfs(int com, int[][] computers){
        visited[com]=true;
        for(int i=0;i<N;i++){
            if(com !=i && computers[com][i]==1 && !visited[i]){
                dfs(i,computers);
            }
        }
    }
}