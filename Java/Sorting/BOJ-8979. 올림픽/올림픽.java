import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String[] ss = s1.split(" ");

        int n = Integer.parseInt(ss[0]); // 국가의 수
        int m = Integer.parseInt(ss[1]); // 등수 알고 싶은 국가
        int[][] arr = new int[n][4]; // 나라, 금은동
        for(int i=0;i<n;i++){
            String s2 = br.readLine();
            String[] ss2 = s2.split(" ");
            arr[i][0] = Integer.parseInt(ss2[0]); // 국가 번호
            arr[i][1] = Integer.parseInt(ss2[1]); // 금
            arr[i][2] = Integer.parseInt(ss2[2]); // 은
            arr[i][3] = Integer.parseInt(ss2[3]); // 동

        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Integer.compare(b[1], a[1]);       // 1번째 기준 내림차순
            else if (a[2] != b[2]) return Integer.compare(b[2], a[2]);  // 2번째 기준 내림차순
            else return Integer.compare(b[3], a[3]);                    // 3번째 기준 내림차순
        });

        int rank=1;

        for(int i=0;i<n;i++){
            if(i>0){ // 전과 비교를 위해 index 1부터
                if(arr[i][1] != arr[i-1][1] || arr[i][2] != arr[i-1][2] || arr[i][3] !=arr[i-1][3]){
                    rank = i+1;
                }
            }
            if(arr[i][0]==m){
                break;
            }
        }

        System.out.println(rank);
        

    }
}