import java.util.*;
public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int sum = 0;
        int n = 0;
        // n이 홀수면 위, 짝수면 아래에서 시작
        while(x>sum){
            n++;
            sum = sum + n;
            }
        int move = sum - x;
        if(n%2 !=0){
            int a = 1;
            int b = n;
            for(int i=0; i<move; i++){
            a++;
            b--;
            }
            System.out.println(a+"/"+b);}
        else{
            int a = n;
            int b = 1;
            for(int i=0; i<move; i++){
            a--;
            b++;
            }
            System.out.println(a+"/"+b);
            }

        }
      }