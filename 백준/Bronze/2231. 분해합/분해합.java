import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int con = n;
        int max_con = 0;
        while(con>0){
            String s = Integer.toString(con);
            String[] ss = s.split("");
            int sum = 0;
            for(String s1 : ss){
                int s2 = Integer.parseInt(s1);
                sum += s2;
            }
            if((sum+con) == n){
                max_con = con;
            }
            con--;
        }
        
        System.out.println(max_con);
        
    }
}