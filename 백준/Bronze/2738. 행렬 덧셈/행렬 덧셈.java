import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] ss = s.split(" ");
        int n = Integer.parseInt(ss[0]);
        int m = Integer.parseInt(ss[1]);
        int[][] a = new int[n][m];
        int[][] b = new int[n][m];
        int[][] answer = new int[n][m];
        for(int i=0; i<n; i++){
            String s1 = br.readLine();
            String[] ss1 = s1.split(" ");
            for(int j=0; j<m; j++){
                a[i][j] = Integer.parseInt(ss1[j]);
            }
        }

        for(int i=0; i<n; i++){
            String s2 = br.readLine();
            String[] ss2 = s2.split(" ");
            for(int j=0; j<m; j++){
                b[i][j] = Integer.parseInt(ss2[j]);
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                answer[i][j] = a[i][j] + b[i][j];
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}