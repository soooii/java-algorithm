import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<t;i++){
            String s = br.readLine();
            String[] ss = s.split("");
            int left = -1;
            int right = -1;
            List<String> list = new ArrayList<>(Arrays.asList(ss));

             while(true){
                 List<String> beforelist = new ArrayList<>(list);
                 left = list.indexOf("(");
                 right = list.indexOf(")");
                 if((left!=-1 && right!=-1) && (left<right)){
                     list.remove(left);
                     right = list.indexOf(")");
                     list.remove(right);
                 }
                 if(list.isEmpty()){
                     sb.append("YES"+"\n");
                     break;
                 }
                 if(list.equals(beforelist) ){
                     sb.append("NO"+"\n");
                     break;
                 }
             }
        }
        System.out.println(sb);
    }
}