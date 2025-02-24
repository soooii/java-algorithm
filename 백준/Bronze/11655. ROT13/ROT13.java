import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if('a'<=c && c<='z'){
                int n1 = (int)c + 13;
                int n2 = (int)('z');
                if(n1<=n2){
                    char c1 = (char)(c+13);
                    //System.out.println(c1);
                    sb.append(c1);
                }else{
                    int plus = 13-(n2-(int)c)-1;
                    char c1 = (char)('a'+plus);
                    sb.append(c1);
                }

            }else if('A'<=c && c<='Z'){
                int n1 = (int)c + 13;
                int n2 = (int)('Z');
                if(n1<=n2){
                    char c1 = (char)(c+13);
                    sb.append(c1);
                }else{
                    int plus = 13-(n2-(int)c)-1;
                    char c1 = (char)('A'+plus);
                    sb.append(c1);
                }
            }
            else{
                sb.append(c);
            }

        }
        System.out.println(sb);

    }
}