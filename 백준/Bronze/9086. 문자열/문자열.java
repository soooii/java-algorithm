import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String s = br.readLine();
            int l = s.length();
     
            if(l==1){
                System.out.println(s+s);}
            else{
                char first = s.charAt(0);
                char last = s.charAt(l-1);
                System.out.print(first);
                System.out.println(last);
            }
        }
    }
}