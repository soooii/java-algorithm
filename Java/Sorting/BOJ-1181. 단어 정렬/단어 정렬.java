import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(br.readLine());
        }
        
        list=list.stream().distinct().collect(Collectors.toList());
        
        Collections.sort(list,(a,b)->{
           if(a.length() != b.length()) return a.length()-b.length();
           else return a.compareTo(b);
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i)).append("\n");
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        
        bw.flush();
        bw.close();
        
        
    }
}