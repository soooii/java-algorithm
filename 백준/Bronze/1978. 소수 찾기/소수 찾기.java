import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String[] ss = s.split(" ");
        int count=0;
        for(int i=0;i<n;i++){
            if(isPrime(Integer.parseInt(ss[i]))){
                count++;
            }
        }
        System.out.println(count);
    }
    public static boolean isPrime(int num){
        if(num==1){return false;}
        for(int i=2;i*i<=num;i++){
            if(num%i==0){return false;}
        }
        return true;
    }
}