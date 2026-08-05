import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");
        while(true){
            String s = br.readLine();
            String[] ss = s.split(" ");
            int first = Integer.parseInt(ss[0]);
            int second = Integer.parseInt(ss[1]);
            
            if(first ==0 && second ==0){
                break;
            }
            else if(first ==0 || second ==0){sb.append("neither"+"\n");}
            else if(second % first == 0){sb.append("factor"+"\n");}
            else if (first % second == 0){sb.append("multiple"+"\n");}
            else{sb.append("neither"+"\n");}
        }
        System.out.print(sb);
    }
}