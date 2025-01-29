import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            String com = ss[0];
            
            if(com.equals("push_front")){
                list.add(0,Integer.parseInt(ss[1]));
            }
            if(com.equals("push_back")){
                list.add(Integer.parseInt(ss[1]));
            }
            if(com.equals("pop_front")){
                if(list.size()==0){
                   sb.append(-1 + "\n"); 
                }else{
                    sb.append(list.get(0) + "\n");
                    list.remove(0);
                }
               
            }
            if(com.equals("pop_back")){
                if(list.size()==0){
                  sb.append(-1 + "\n");
                }else{
                  sb.append(list.get(list.size()-1) +"\n");
                  list.remove(list.size()-1);  
                }
                
            }
            if(com.equals("size")){
                 sb.append(list.size()+"\n");
            }
            if(com.equals("empty")){
                if(list.size()==0){
                    sb.append(1 + "\n");
                }else{
                    sb.append(0 + "\n");
                }
            }
            if(com.equals("front")){
                if(list.size()==0){
                  sb.append(-1 + "\n");
                }else{
                  sb.append(list.get(0)+"\n"); 
                }
            }
            if(com.equals("back")){
                if(list.size()==0){
                  sb.append(-1 + "\n");
                }else{
                  sb.append(list.get(list.size()-1)+"\n");
                }
            }
        }
        System.out.println(sb);
        
    }
}