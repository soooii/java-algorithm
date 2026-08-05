import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = (int) Math.sqrt(n);
        StringBuilder sb = new StringBuilder();

        while (n != 1) { // n이 1인 경우 아무것도 출력하지 않는다.
            boolean found = false;
            for (int i = 2; i <= s; i++) {
                if (n % i == 0) {
                    sb.append(i).append("\n");
                    n /= i;
                    s = (int) Math.sqrt(n);
                    found = true;
                    break;
                }
            }
            if (!found) { // 소인수를 찾지 못한 경우(남은 n이 소수)
                sb.append(n);
                break;
            }
        }
        System.out.println(sb);
    }
}