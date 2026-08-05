import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // 만들려고 하는 수열
        int[] array = new int[n];
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int pushLast = 0; // 마지막으로 푸시한 값

        // 입력받은 수열 저장
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 0; i < n; i++) {
            int target = array[i];

            if (target > pushLast) {
                // target보다 작은 값까지 스택에 푸시
                for (int j = pushLast + 1; j <= target; j++) {
                    stack.push(j);
                    sb.append('+').append('\n'); // 줄바꿈을 올바르게 추가
                }
                pushLast = target;
            }

            if (target == stack.peek()) {
                stack.pop();
                sb.append('-').append('\n'); // 줄바꿈을 올바르게 추가
            } else {
                // 수열을 만들 수 없는 경우
                System.out.println("NO");
                return; // 더 이상 작업을 하지 않도록 종료
            }
        }

        // 결과 출력
        System.out.println(sb.toString());
    }
}