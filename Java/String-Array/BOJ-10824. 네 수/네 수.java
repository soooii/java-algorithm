import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s =br.readLine();
        String[] ss = s.split(" ");
        String s1 = ss[0]+ss[1];
        String s2 = ss[2]+ss[3];
        Long answer = Long.parseLong(s1)+Long.parseLong(s2);
        System.out.println(answer);
    }
}