import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // HashSet은 중복을 자동으로 제거 -> 존재여부
        HashSet<Integer> A = new HashSet<>(); 
        for(int i=0;i<N;i++){
            A.add(Integer.parseInt(st.nextToken()));
        }
        
        int M = Integer.parseInt(br.readLine());
        StringTokenizer str = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            if(A.contains(Integer.parseInt(str.nextToken()))){
               sb.append(1).append("\n");
            }else{
               sb.append(0).append("\n"); 
            }
        }
        System.out.println(sb);
    }
}