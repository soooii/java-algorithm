import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] ss = s.split(" ");
        int a1 = Integer.parseInt(ss[0]);
        int a0 = Integer.parseInt(ss[1]);
        int c = Integer.parseInt(br.readLine());
        int n0 = Integer.parseInt(br.readLine());
        boolean satisfied = true;
        for(int i=n0; i<=100; i++){
            int f = a1*i+a0;
            int g = i;
            if(f > c*g){
                satisfied = false;
                break;}
        }
        if(satisfied == true){System.out.println(1);}
        else{System.out.println(0);}
        
    }
}