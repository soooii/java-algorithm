import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt(); //b진법
        int a; //몫
        int r; //나머지
        StringBuilder sb = new StringBuilder();
        // 몫이 0일때 까지는 구해야하니 n!=0으로
        while(n != 0){
            a= n/b;
            r= n%b;
            if(r>=10){
                int len = r-10;
                char c = (char) ('A'+len);
                sb.insert(0,c);
            }
            else{sb.insert(0,r);}
            n=a;
        }

        System.out.println(sb.toString());
    }
}
