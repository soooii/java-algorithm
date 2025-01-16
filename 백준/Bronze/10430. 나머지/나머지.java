import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int answer1 = (a+b)%c;
        int answer2 = ((a%c)+(b%c))%c;
        int answer3 = (a*b)%c;
        int answer4 = ((a%c)*(b%c))%c;
        
        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);
}
}