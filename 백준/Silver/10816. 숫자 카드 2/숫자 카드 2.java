import java.io.*;
import java.util.*;

public class Main {

	static int N,M;
	static int[] user1;;
	static int[] num;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N=Integer.parseInt(st.nextToken()); // 상근

		Map<Integer,Integer> map = new HashMap<>();
		user1=new int[N];


		StringTokenizer str = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			user1[i] = Integer.parseInt(str.nextToken());
			if(!map.containsKey(user1[i])){
				map.put(user1[i],1);
			}
			else{
				int count = map.get(user1[i]);
				map.put(user1[i],count+1);
			}
		}

		M = Integer.parseInt(br.readLine());
		num=new int[M];
		StringTokenizer strr = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<M;i++){
			num[i] = Integer.parseInt(strr.nextToken());
			if(map.containsKey(num[i])){
				sb.append(map.get(num[i])).append(" ");
			}
			else{
				sb.append(0).append(" ");
			}
		}

		System.out.println(sb);

	}
}
