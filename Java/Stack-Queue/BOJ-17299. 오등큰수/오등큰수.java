import java.io.*;
import java.util.Stack;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        String[] ss = s.split(" ");

        // F(Ai) : Ai가 수열에서 등장한 횟수
        // Ai의 오등큰수 : 오른쪽, F(Ai)보다 등장한 횟수가 큰 수 중 가장 왼쪽
        // 오등큰수 없으면 -1
        // 이중 for문 시간초과 -> 스택을 사용하자

        // 등장 횟수를 기록하는 배열 (1 ≤ Ai ≤ 1,000,000)
        int[] count = new int[1000001];

        // 각 원소 값의 등장 횟수 구하기
        for (int i = 0; i < n; i++) {
            count[Integer.parseInt(ss[i])]++;
        }

        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];
        stack.push(0);
        for(int i=1;i<n;i++){
            // EmptyStackException 방지
            // stack이 있을 경우에만 peek하도록
            while(!stack.isEmpty() && count[Integer.parseInt(ss[stack.peek()])]<count[Integer.parseInt(ss[i])]){
                int index = stack.pop();
                answer[index] = Integer.parseInt(ss[i]);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int index = stack.pop();
            answer[index] = -1;
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