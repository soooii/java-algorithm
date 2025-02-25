import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String stringn = reader.readLine();
        int n = Integer.parseInt(stringn);

        int[] dp = new int[n + 1];

        dp[1] = 0;

        for (int i = 2; i <= n; i++) { //최종 숫자는 n이기 때문에 n까지 반복연산
            // 3이나 2로 나누어지지 않으면, 주어진 숫자-1에 대한 최소 연산 횟수 + 연산을 한 횟수 1
            dp[i] = dp[i - 1] + 1;

            //헐 자바에서 if문 둘다 만족하면 둘다 실행되네..

            // i가 2로 나누어 떨어지면 i/2로 가는 연산을 고려
            if (i % 2 == 0) {
                //저 dp[i]가 위에서 계산한 dp[i-1]+1이랑 비교하는거네..
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            // i가 3으로 나누어 떨어지면 i/3으로 가는 연산을 고려
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }

        System.out.println(dp[n]);
    }

}
