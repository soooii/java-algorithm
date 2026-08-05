import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<t; i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            int n = Integer.parseInt(ss[0]);
            long sum = 0;
            for(int j=1; j<n+1; j++){
                for(int k=j+1;k<n+1;k++){
                    long g = gcd(Integer.parseInt(ss[j]),Integer.parseInt(ss[k]));
                    sum +=g;
                }
            }
            sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());

    }

    public static int gcd(int a, int b){
        int r;
        while(b!=0){
            r = a%b;
            a=b;
            b=r;
        }
        return a;
    }
}