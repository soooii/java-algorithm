import java.math.BigInteger;
import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String f = fac(n).toString();
        String[] ff = f.split("");
        int count = 0;
        for(int i=ff.length-1;i>=0;i--){
            if(ff[i].equals("0")){
                count++;
            }else{
                System.out.println(count);
                break;
            }
        }


    }

    // int, long 타입 반환 시 오버플로우
    // 더 큰 정수 지원하는 BigInteger 타입 반환
    public static BigInteger fac(int n){
        BigInteger answer = BigInteger.ONE;
        for(int i=0;i<n;i++){
            answer = answer.multiply(BigInteger.valueOf(n-i));
        }
        return answer;
    }
}