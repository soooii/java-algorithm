import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A);

		int M = Integer.parseInt(br.readLine());
		StringTokenizer str = new StringTokenizer(br.readLine());
		int num;

		StringBuilder sb =new StringBuilder();
		for (int i = 0; i < M; i++) {
			num = Integer.parseInt(str.nextToken());
			boolean what = binarySearch(A,num);
			if(what){
				sb.append(1).append("\n");
			}else{
				sb.append(0).append("\n");
			}
		}

		System.out.println(sb);

	}

	static boolean binarySearch(int[] A, int target){
		int left=0;
		int right=A.length-1;

		while (left<=right){
			int mid =(left+right)/2;

			if(A[mid]==target){
				return true;
			}else if(A[mid]<target){
				left=mid+1;
			}else{
				right=mid-1;
			}
		}

		return false;
	}
}