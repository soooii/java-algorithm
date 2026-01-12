import java.io.*;
import java.util.*;

public class Main {

    static int go(int A, int l, int P) {
        int[] arr = new int[l];
        int idx = 0;

        // 자리수 분해
        for (int i = l; i >= 1; i--) {
            int div = (int) Math.pow(10, i - 1);
            arr[idx++] = A / div;
            A = A % div;

            if (i == 1) break;
        }

        // P번 곱해서 합 구하기
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += (int) Math.pow(arr[i], P);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        while (true) {
            
            // 이미 나온 수면 반복 시작
            if (set.contains(A)) {
                System.out.println(list.indexOf(A));
                break;
            }
            
            // 리스트에 추가
            list.add(A);
            set.add(A);

            // 숫자 길이
            int l = String.valueOf(A).length();

            // 다음 수 계산
            A = go(A, l, P);
        }
    }
}
