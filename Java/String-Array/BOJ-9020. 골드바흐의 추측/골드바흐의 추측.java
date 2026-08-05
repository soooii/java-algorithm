import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            int a = Integer.parseInt(br.readLine());
            int distance = 10001;
            int b = -1;
            int c = -1;
            for(int j=2;j<=(a/2);j++){
                if(isPrime(j)==true && isPrime(a-j)==true){
                    if(distance>Math.abs(j-a+j)){
                        distance = Math.abs(j-a+j);
                        b=j;
                        c=a-j;
                    }
                }
                
            }
            sb.append(b).append(" ").append(c).append("\n");
           
            
        }
        
        System.out.println(sb.toString());
    }
    
    public static boolean isPrime(int num){
        if(num==1){return false;}
        for(int i=2;i*i<=num;i++){
            if(num%i==0){return false;}
        }
        return true;
    }
}