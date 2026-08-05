import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        Character[] alpha = new Character[26];
        for(int i=0;i<26;i++){
            alpha[i] = (char)('a'+i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++){
            // alpha[i]가 Character여도 indexOf가 적용됨
            int index = s.indexOf(alpha[i]);
            sb.append(index).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}