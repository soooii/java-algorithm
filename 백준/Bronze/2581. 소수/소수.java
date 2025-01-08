import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int sum = 0;
        int min = 100001;
        if(m==1){m=m+1;} // 1은 소수가 아님으로 다음 자연수부터 판별
        for (int i = m; i <= n; i++) {
            boolean isDecimal = true;
            int k = (int) Math.sqrt(i); // 제곱근
            for (int j = 2; j <= k; j++) {
                if (i % j == 0) { // 소수가 아니다
                    isDecimal = false;
                    break;
                }
            }
            // 위 break 실행되어도 for문만 탈출
            // 아래 코드는 무조건 실행된다
            if (isDecimal) {
                sum += i;
                if (min > i) {
                    min = i;
                }

            }
        }
        if (sum==0){System.out.println(-1);}
        else{
            System.out.println(sum);
            System.out.println(min);
        }
    }
}