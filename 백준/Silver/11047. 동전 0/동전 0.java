import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());

		int[] A = new int[N+1];
		for(int i=1;i<=N;i++){
			A[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[K+1];
		for(int i=0;i<=K;i++){
			dp[i]=K+1;
		}

		dp[0]=0;

		for(int i=0;i<=K;i++){
			for(int j=1;j<=N;j++){
				if(i>=A[j]){
					dp[i]=Math.min(dp[i],dp[i-A[j]]+1);
				}
			}
		}

		if (dp[K] == K+1) {
			System.out.println(-1);
		} else {
			System.out.println(dp[K]);
		}
	}
}
