import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            int absA = Math.abs(a);
            int absB = Math.abs(b);
            
            if(absA == absB){
                return a-b;
            }
            else{
                return absA-absB;
            }
        });
        
        for(int i=0;i<N;i++){
            int x =Integer.parseInt(br.readLine());
            if(x !=0){
                pq.add(x);
            }
            else{
                if(pq.isEmpty()){
                    sb.append(0).append("\n");
                }
                else{
                    sb.append(pq.poll()).append("\n");
                }
               
            }
            
        }
        
        System.out.println(sb);
        
        
        
    }
}