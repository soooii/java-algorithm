import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s =br.readLine();
        String[] arr = new String[s.length()];
        for(int i=0;i<s.length();i++){
            String s1 = s.substring(i);
            arr[i] = s1;
        }

        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for(String s2 : arr){
            sb.append(s2).append(" ");
        }
        System.out.println(sb.toString());

    }
}