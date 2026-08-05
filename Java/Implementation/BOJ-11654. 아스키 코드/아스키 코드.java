import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // String -> char -> int
        char c = sc.next().charAt(0);
        int answer = (int)c;
        System.out.println(answer);
    }
}