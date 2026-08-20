import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        
        int l = triangle.length;
        int[][] dp =new int[l][l];
        
        dp[0][0]=triangle[0][0];
        
        for(int i=1;i<l;i++){
            for(int j=0;j<=i;j++){
                if(j==0){
                    dp[i][j]=dp[i-1][j]+triangle[i][j];  
                }
                else if(j==i){
                    dp[i][j]=dp[i-1][j-1]+triangle[i][j];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j-1],dp[i-1][j])+triangle[i][j];
                }
            }
        }
        
        int answer=-1;
        for(int i=0;i<l;i++){
            answer=Math.max(answer,dp[l-1][i]);
        }
        
        return answer;
    }
}