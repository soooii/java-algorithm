import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); //목표
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0;i<N;i++){
            stack.push(Integer.parseInt(br.readLine()));
        }
        
        int count =0;
        while(K!=0){
          if(stack.peek()<=K){
            count += K/stack.peek();
            K = K%stack.peek();
            stack.pop();
            }else{
            stack.pop();
            }
        }
        
        System.out.println(count);
       
        
    }
}