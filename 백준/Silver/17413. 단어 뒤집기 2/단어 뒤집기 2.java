import java.io.*;
import java.util.Stack;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        boolean check = false;
        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if(c.equals('<')){
                // 태그와 단어 사이에는 공백이 없음으로 abc<면 <를 만나기전 단어를 출력해줘야함
                while(!stack.isEmpty()){
                    answer.append(stack.pop());
                }
                check = true;
                answer.append('<');
            }
            else if(c.equals('>')){
                check = false;
                answer.append('>');
            }
            else if(check){
                answer.append(c);
            }
            else if(check == false){
                if(c.equals(' ')){
                    while(!stack.isEmpty()){
                        answer.append(stack.pop()); //역순으로
                    }
                    answer.append(' ');
                }
                else{
                    stack.push(c);
                }
            }

          


        }
        // 문자열 끝에서는 <나 ' '를 만나지 못하고 끝날 수 있기 때문에
        // 스택에 들어 있는 것들을 pop해주고 종료해줘야 함
        while(!stack.empty()){
            answer.append(stack.pop());
        }
        System.out.println(answer.toString());
    }
}