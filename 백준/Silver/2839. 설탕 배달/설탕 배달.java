import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        //3kg, 5kg, 최대한 적은 봉지
        
        int[] dp = new int[N+1];
        
        for (int i = 0; i <= N; i++) {
            dp[i] = 10001;
        }
        
        dp[0]=0;
        
        for(int i=1;i<=N;i++){
            if(i>=3){
                dp[i]=Math.min(dp[i],dp[i-3]+1);
            }
            if(i>=5){
                dp[i]=Math.min(dp[i],dp[i-5]+1);
            }
        }
        
        if (dp[N] == 10001) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N]);
        }
          
        
     
    }
}