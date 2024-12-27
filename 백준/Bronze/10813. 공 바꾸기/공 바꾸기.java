import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 바구니 개수
        int M = sc.nextInt(); // 공 교환 횟수
        sc.nextLine();
        
        int[] num = new int[N]; // 바구니 속 공 숫자 배열
        
        for(int i=0; i<N; i++){
            num[i] = i+1; // 바구니 속 공 숫자 초기 세팅
        }
        
        for(int i=0; i<M; i++){
            String s = sc.nextLine();
            String[] array = s.split(" ");
            int a = Integer.parseInt(array[0])-1; // 바꿀 바구니 a
            int b = Integer.parseInt(array[1])-1; // 바꿀 바구니 b
            
            int a1 = num[a]; // a 속 공 숫자
            int b1 = num[b]; // b 속 공 숫자
            
            num[a] = b1; // 공 바꾸기
            num[b] = a1;
        }
        
        for(int n : num){
            System.out.print(n + " ");
        }
        
    }
}