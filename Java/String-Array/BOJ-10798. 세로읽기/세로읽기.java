import java.io.*;

public class Main{
    public static void main(String[] ars) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] answer = new String[5][15];
        int length_max = 0;
        for(int i=0; i<5; i++){
            String s = br.readLine();
            String[] ss = s.split("");
            int l = ss.length;
            if(l>length_max){
                length_max = l;
            }
            for(int j=0; j<l; j++){
                answer[i][j] = ss[j];
            }
        }

        StringBuilder sb = new StringBuilder("");
        for(int j=0;j<length_max;j++){
            for(int i=0;i<5;i++){
                if(answer[i][j] != null){
                    sb.append(answer[i][j]);
                }
            }
        }
        System.out.println(sb);

    }
}