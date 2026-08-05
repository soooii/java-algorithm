import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();
        
        int x = 0;
        int y = (c*d-a*f)/(b*d-a*e);
        if(a != 0){
            x = (c-b*y)/a;
        }else{
            x = (f-e*y)/d;
        }
        
        System.out.println(x + " " + y);
        
    }
}