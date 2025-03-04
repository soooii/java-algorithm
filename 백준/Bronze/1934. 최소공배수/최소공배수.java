import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            int a = Integer.parseInt(ss[0]);
            int b = Integer.parseInt(ss[1]);
            int answer = lcm(a,b);
            sb.append(answer).append("\n");
        }

        System.out.println(sb.toString());


    }

    public static int gcd(int a, int b){
        int r;
        while(b != 0){
            r=a%b;
            a=b;
            b=r;
        }
        return a;
    }

    public static int lcm(int a, int b){
        return (a*b)/gcd(a,b);
    }
}