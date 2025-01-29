import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            String com = ss[0];

            if(com.equals("push_front")){
               deque.addFirst(Integer.parseInt(ss[1]));
            }
            if(com.equals("push_back")){
                deque.addLast(Integer.parseInt(ss[1]));
            }
            if(com.equals("pop_front")){
                if(deque.isEmpty()){
                    sb.append(-1 + "\n");
                }else{
                    sb.append(deque.pollFirst() + "\n");
                }

            }
            if(com.equals("pop_back")){
                if(deque.isEmpty()){
                    sb.append(-1 + "\n");
                }else{
                    sb.append(deque.pollLast() + "\n");
                }

            }
            if(com.equals("size")){
                sb.append(deque.size()+"\n");
            }
            if(com.equals("empty")){
                if(deque.isEmpty()){
                    sb.append(1 + "\n");
                }else{
                    sb.append(0 + "\n");
                }
            }
            if(com.equals("front")){
                if(deque.isEmpty()){
                    sb.append(-1 + "\n");
                }else{
                    sb.append(deque.getFirst()+"\n");
                }
            }
            if(com.equals("back")){
                if(deque.isEmpty()){
                    sb.append(-1 + "\n");
                }else{
                    sb.append(deque.getLast()+"\n");
                }
            }
        }
        System.out.println(sb);

    }
}