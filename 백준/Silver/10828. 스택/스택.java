import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            String[] ss = s.split(" ");
            String key = ss[0];
            if(ss.length>1){
                if(key.equals("push")){
                    stack.push(Integer.parseInt(ss[1]));
                }
            }else{
                if(key.equals("pop")){
                    if(stack.isEmpty()){
                        answer.append(-1);
                    }else{
                        answer.append(stack.pop());
                    }
                }
                if(key.equals("top")){
                    if(stack.isEmpty()){
                        answer.append(-1);
                    }else{
                        answer.append(stack.peek());
                    }
                }
                if(key.equals("empty")){
                    if(stack.isEmpty()){
                        answer.append(1);
                    }else{
                        answer.append(0);
                    }
                }
                if(key.equals("size")){
                    answer.append(stack.size());
                }
                answer.append("\n");
            }
        }
        System.out.println(answer);
    }
}