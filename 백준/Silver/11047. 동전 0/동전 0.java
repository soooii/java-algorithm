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

		int count=0;

		for(int i=N;i>0;i--){
			if(K>=A[i]){
				count += K/A[i];
				K %= A[i];
			}
		}

		System.out.println(count);
	}
}