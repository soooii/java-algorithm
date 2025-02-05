import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String[] ss = s.split(" ");
        int[] num = new int[n];
        for(int i=0;i<n;i++){
            num[i] = Integer.parseInt(ss[i]);
        }
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && (num[stack.peek()]<num[i])){
                answer[stack.pop()] = num[i];
            }
            stack.push(i);
            
        }
        while(!stack.isEmpty()){
            answer[stack.pop()] = -1;
        }
        StringBuilder sb = new StringBuilder();
        for(int a : answer){
            sb.append(a).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}