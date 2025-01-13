import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        int num = 0;
        while(count != n ){
            num++;
            String s = Integer.toString(num);
            if(s.contains("666")){
                count++;}
        }
        
        System.out.println(num);
    }
}