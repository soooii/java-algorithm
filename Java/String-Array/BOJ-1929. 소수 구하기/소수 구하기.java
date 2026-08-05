import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m =sc.nextInt();
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        
        for(int i=m;i<=n;i++){
            if(isPrime(i)){sb.append(i).append("\n");}
        }
        System.out.println(sb);
        
    }
    
    public static boolean isPrime(int a){
        if(a==1){return false;}
        for(int i=2;i*i<=a;i++){
            if(a%i==0){return false;}
        }
        return true;
    }
}