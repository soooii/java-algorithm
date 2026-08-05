import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[16]; // 한 변의 점 개수
        // 2, 3, 5(3+2), 9(5+4), 
        num[0] = 2;
        for(int i=1; i<=15; i++){
            num[i] = num[i-1] + (num[i-1]-1);
        }
        int answer = num[n] *num[n];
        System.out.println(answer);
    }
}