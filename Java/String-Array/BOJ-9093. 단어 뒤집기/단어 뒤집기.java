import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for(int i=0;i<t;i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            for(String word : ss){
                StringBuilder sb = new StringBuilder(word);
                answer.append(sb.reverse().toString() + " ");
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}