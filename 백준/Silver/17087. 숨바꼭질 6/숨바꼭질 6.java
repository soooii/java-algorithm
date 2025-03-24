import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        String[] ss1 = s1.split(" ");
        String[] ss2 = s2.split(" "); // 동생 위치 모음

        int n = Integer.parseInt(ss1[0]); // 동생 수
        int s = Integer.parseInt(ss1[1]); // 수빈이 위치

        // 1초 당 수빈이 이동 D
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            int len = s-Integer.parseInt(ss2[i]);
            if(len<0){len = -len;}
            arr[i] = len;
        }

        int answer = arr[0];
        if(arr.length != 1){
            for(int i=0; i<n; i++){
                answer = gcd(answer,arr[i]);
            }
        }
        System.out.println(answer);

    }

    public static int gcd(int a, int b){
        int r;
        while(b != 0){
            r=a%b;
            a=b;
            b=r;
        }
        return a;
    }
}