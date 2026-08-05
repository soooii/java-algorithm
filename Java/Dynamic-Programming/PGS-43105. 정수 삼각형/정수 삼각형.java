import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        
        int l = triangle.length;
        // Top-down, 누적합
        int[][] dp = new int[l][l];
        dp[0][0]=triangle[0][0];
        
        // 두번째줄 부터 시작, dp[0][0]이 첫번째줄
        for(int i=1;i<l;i++){
            for(int j=0;j<=i;j++){
               if(j==0){
                   // 맨 왼쪽 끝 칸은 위 줄의 맨 왼쪽 칸에서만 올 수 있음
                   dp[i][j]=dp[i-1][j]+triangle[i][j];
               }else if(j==i){
                   // 맨 오른쪽 끝 칸은 위 줄의 맨 오른쪽 칸에서만 올 수 있음
                   dp[i][j]=dp[i-1][j-1] + triangle[i][j];
               }else{
                   // 가운데 칸들은 위 줄의 왼쪽 위(j-1)와 오른쪽 위(j) 중 큰 값을 선택
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
               }
            }
        }
        
        int max=0;
        // 마지막 줄에 쌓인 값들 중 최댓값을 찾는다
        for(int j=0;j<l;j++){
            max=Math.max(max,dp[l-1][j]);
        }
        
 
        
        
        return max;
    }
}