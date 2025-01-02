import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        StringBuilder sb = new StringBuilder("");
        sb.append(s);
        sb.reverse();
        
        if(s.equals(sb.toString())){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
}