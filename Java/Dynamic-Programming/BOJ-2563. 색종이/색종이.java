import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 색종이 개수
        boolean[][] dp = new boolean[100][100];
        int area = 0;
        for(int i=0; i<n; i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            int l = Integer.parseInt(ss[0]); // 왼쪽 변과의 거리
            int b = Integer.parseInt(ss[1]); // 아래쪽 변과의 거리
            for(int j=l; j<l+10; j++){
                for(int k=b; k<b+10; k++){
                    if(dp[j][k] == false){
                        dp[j][k] = true;
                        area ++;
                    }
                }
            }

        }

        System.out.println(area);
    }
}