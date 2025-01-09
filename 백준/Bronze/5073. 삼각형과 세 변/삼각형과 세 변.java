import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            String s = br.readLine();
            String[] ss = s.split(" ");
            int a = Integer.parseInt(ss[0]);
            int b = Integer.parseInt(ss[1]);
            int c = Integer.parseInt(ss[2]);
            int[] tri = new int[3];
            tri[0] = a;
            tri[1] = b;
            tri[2] = c;
            Arrays.sort(tri);
            if(a==0 && b ==0 && c==0){
                break;
            }
            else{
                if(tri[2] >= tri[0] + tri[1]){sb.append("Invalid"+"\n");}
                else if(a==b && b==c){sb.append("Equilateral"+"\n");}
                else if((a==b) || (b==c) || (c==a)){sb.append("Isosceles"+"\n");}
                else{sb.append("Scalene"+"\n");}
            }
        }
        System.out.println(sb);

    }
}