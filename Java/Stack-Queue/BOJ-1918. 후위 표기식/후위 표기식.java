import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        // 연산자를 스택에 넣어야 함
        // 연산자의 우선 순위를 지정
        // 연산자를 스택에 넣기 전에, 현재 연산자의 우선순위보다
        // 큰 연산자가 stack의 맨 위에 있다면
        // 없을 때까지 pop

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            }
            else if(c == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }
                stack.pop(); // 남아있는 '(' 꺼내줌
            }else if(c=='+' || c=='-' || c=='*' || c=='/'){
                while(!stack.isEmpty() && priority(stack.peek()) >= priority(c)){
                    sb.append(stack.pop());
                }
                stack.push(c); // 바로 append하지않고 stack에 push
            }else{ // 알파벳
                sb.append(c);
            }

        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
    
    // 우선순위
    public static int priority(char operator){
        if(operator == '(' || operator == ')'){
            return 0; // 괄호는 우선순위 비교대상이 아님
        }else if(operator == '+' || operator == '-'){
            return 1;
        }else if(operator == '*' || operator == '/'){
            return 2;
        }else{
            return -1;
        }
    }
}