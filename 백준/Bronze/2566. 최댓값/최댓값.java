import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] answer = new int[9][9];
        int max = -1;
        int a = 0;
        int b = 0;
        for(int i=0; i<9; i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            for(int j=0; j<9; j++){
                answer[i][j] = Integer.parseInt(ss[j]);
            }
        }

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(max<answer[i][j]){
                    max = answer[i][j];
                    a = i;
                    b = j;
                }
            }
        }

        // 문제에서 행과 열은 1행 1열부터 시작
        System.out.println(max);
        System.out.print(a + 1 + " ");
        System.out.print(b + 1);


    }
}