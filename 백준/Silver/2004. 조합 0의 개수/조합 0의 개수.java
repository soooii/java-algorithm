import java.util.*;

//nCm
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 0의 끝자리 개수 = 2와 5의 짝지어진 개수
        // nCm = n! / ((n-m)! * m!)
        // n!의 2와 5의 짝 개수 - ((n-m)!의 2*5의 짝 개수 + m!의 2*5의 짝 개수)

        int twoCnt = get(n, 2) - get(m, 2) - get(n - m, 2);
        int fiveCnt = get(n, 5) - get(m, 5) - get(n - m, 5);

        System.out.println(Math.min(twoCnt, fiveCnt));
    }

    private static int get(int n, int k) {
        int cnt = 0;

        while(n >= k) {
            cnt += n / k;
            n /= k;
        }

        return cnt;
    }



}