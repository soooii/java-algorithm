import java.util.*;


public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int b= sc.nextInt();
        int sum=0;
        int count=0;
        for(int i=s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if(c>='A' && c<='Z'){
                int len = s.charAt(i)-'A'; //char-char 연산은 int형 반환
                int num = len + 10;
                sum += num * Math.pow(b,count);
            }
            else{
                sum += Character.getNumericValue(c) * Math.pow(b,count);
            }
            count++;
        }

        System.out.println(sum);
    }
}