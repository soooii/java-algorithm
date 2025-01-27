import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] ss = s.split(" ");
        int n = Integer.parseInt(ss[0]);
        int k = Integer.parseInt(ss[1]);
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            list.add(i);
        }
        int in = k-1;
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(int i=0;i<n;i++){
            if(list.size()==1){
                sb.append(list.get(0)+">");
            }
            else if(in<list.size()){
                sb.append(list.get(in)+", ");
                list.remove(in);
                in -=1;
                in+=k;
            }else{
                while(list.size()<=in){
                    int a= in-list.size();
                    in = a;
                }
                sb.append(list.get(in)+", ");
                list.remove(in);
                in -= 1;
                in+=k;
            }

        }

        System.out.println(sb);

    }
}