import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int con = n;
        int max_con = 0;
        while(con>0){
            int sum = 0;
            int temp = con;
            while(temp>0){ // temp가 0이면 종료
                sum += temp%10;
                temp = temp/10;
            }
            if((sum+con) == n){
                max_con = con;
            }
            con--;
        }

        System.out.println(max_con);

    }
}