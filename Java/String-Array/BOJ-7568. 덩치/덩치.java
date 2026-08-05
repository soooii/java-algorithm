import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            arr[i][0] = Integer.parseInt(ss[0]); // 몸무게
            arr[i][1] = Integer.parseInt(ss[1]); // 키
        }

       // 브루트포스(완전탐색) / 가능한 모든 경우의 수 비교
        int i = 0;
        int cnt=0;
        while(i != n){

            for(int j = 0; j < n; j++){
                if(arr[i][0]!=arr[j][0]&& arr[i][1]!=arr[j][1]){
                    if(arr[i][0]<=arr[j][0]&& arr[i][1]<=arr[j][1]){
                        cnt++;
                    }
                }
            }
            System.out.print(cnt+1 + " ");
            cnt=0;
            i++;
        }

    }
}
