import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            if(ss[0].equals("push")){
                list.add(Integer.parseInt(ss[1]));
            }
            if(ss[0].equals("pop")){
                if(list.isEmpty()){
                    sb.append(-1+"\n");
                }else{
                    sb.append(list.get(0) + "\n");
                    list.remove(0);
                }
            }
            if(ss[0].equals("size")){
                sb.append(list.size() + "\n");
            }
            if(ss[0].equals("empty")){
                if(list.isEmpty()){
                    sb.append(1 + "\n");
                }else{
                    sb.append(0 + "\n");
                }
            }
            if(ss[0].equals("front")){
                if(list.isEmpty()){
                    sb.append(-1+"\n");
                }else{
                    sb.append(list.get(0) + "\n");
                }
            }
            if(ss[0].equals("back")){
                if(list.isEmpty()){
                    sb.append(-1+"\n");
                }else{
                    sb.append(list.get(list.size()-1) + "\n");
                }
            }

        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();

    }
}