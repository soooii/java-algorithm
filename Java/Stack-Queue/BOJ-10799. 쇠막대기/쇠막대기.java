import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = bf.readLine();
        Stack<Character> stack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') { // 열린 괄호 / 스택 추가
                stack.push('(');
                continue;
            }
            if (s.charAt(i) == ')') { // 닫힌 괄호
                stack.pop(); // 일단 stack에서 pop을 실행

                if (s.charAt(i - 1) == '(') { // 전 괄호가 열린 괄호면 레이저
                    answer += stack.size(); // 현재 stack의 사이즈만큼 더함 (스택에 막대기가 시작할 때마다 쌓였음으로)
                } else { // 전 괄호가 닫힌 괄호면 레이저가 아님 (스택의 가장 top에 있던 막대기가 끝났다는 의미)
                    answer++; // 1만 더해줌
                }
            }
        }

        bw.write(String.valueOf(answer)); // 정수 문자열로 변환
        bw.flush();
        bf.close();
        bw.close();
    }

}