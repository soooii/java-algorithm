import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        
        
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<26;i++){
            char key = (char)('a'+i);
            map.put(key,0);
        }
        for(int i=0;i<s.length();i++){
            int value = map.get(s.charAt(i)); //value
            map.put(s.charAt(i), value+1);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<26; i++){
            char alpha = (char)('a'+i);
            int count = map.get(alpha);
            sb.append(count).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}