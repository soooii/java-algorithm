import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			time[i] = Integer.parseInt(st.nextToken());
		}

		int[] answer = new int[N]; 
		
		Arrays.sort(time);
		
		answer[0] = time[0];
		
		for(int i=1;i<N;i++){
			answer[i]=answer[i-1]+time[i];
		}
		
		int sum=0;
		for(int i=0;i<N;i++){
			sum += answer[i];
		}
		
		System.out.println(sum);


    }
}
