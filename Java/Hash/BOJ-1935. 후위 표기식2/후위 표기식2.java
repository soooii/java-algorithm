import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String[] ss = s.split("");
       
        Map<Character,Double> map = new HashMap<>();
        for(int i=0;i<n;i++){
            char key = (char) ('A'+i);
            double value = Double.parseDouble(br.readLine());
            map.put(key,value);
        }
        Stack<Double> stack = new Stack<>();

        for(int i=0;i<ss.length;i++){
            if(ss[i].matches("[A-Z]")){
                // ss[i]는 String 임으로 Charcter로 바꿔줘야
                // map에서 key를 찾을 수 있음
                // String 형태로 찾으면 NullPointerException 발생
                stack.push(map.get(ss[i].charAt(0)));
            }else{
                double a = stack.pop();
                double b = stack.pop();

                if(ss[i].equals("+")){
                   stack.push(b+a);
                }
                else if(ss[i].equals("-")){
                    stack.push(b-a);
                }
                else if(ss[i].equals("*")){
                    stack.push(b*a);
                }
                else if(ss[i].equals("/")){
                    stack.push(b/a);
                }
            }

        }
        String str = String.format("%.2f",stack.pop());
        System.out.println(str);

    }
}