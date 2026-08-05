import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answer = new StringBuilder();
        String s="";
        while((s=br.readLine())!= null){
            answer.append(count(s)).append("\n");
        }
        bw.write(answer.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    public static String count(String s){
        int small =0;
        int big = 0;
        int num = 0;
        int blank = 0;
        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            if('A'<=c && c<='Z'){
                big++;
            }else if('a'<=c && c<='z'){
                small++;
            }else if(c== ' '){
                blank++;
            }/*else if(0<= (int)c && (int)c<=9){
                num++;
            }*/else{
                num++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(small).append(" ").append(big).append(" ").append(num).append(" ").append(blank);
        return sb.toString();
    }
}