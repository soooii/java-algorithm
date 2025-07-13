import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] s1 = br.readLine().split(" ");
        int[] num = new int[n];
        for(int i=0;i<n;i++){
            num[i] = Integer.parseInt(s1[i]);
        }

        int m = Integer.parseInt(br.readLine());
        String[] s2 = br.readLine().split(" ");
        int[] num2 = new int[m];

        for(int i=0;i<m;i++){
            num2[i] = Integer.parseInt(s2[i]);
        }

        // 이분탐색 : 중복 포함, 몇개 갖고있는지 개수 count, 상한선-하한선
        Arrays.sort(num);
        int result;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            result=upperBound(num,num2[i])-lowerBound(num,num2[i]);
            sb.append(result+" ");
        }
        System.out.println(sb);

    }

    public static int lowerBound(int[] arr, int key){
        int lo=0;
        int hi=arr.length;

        while(lo<hi){
            int mid = (lo+hi)/2;

            if(key<=arr[mid]){
                hi=mid;
            }
            else{
                lo=mid+1;
            }
        }

        return lo;
    }

    public static int upperBound(int[] arr, int key){
        int lo=0;
        int hi=arr.length;
        while(lo<hi){
            int mid = (lo+hi)/2;
            if(key<arr[mid]){
                hi=mid;
            }
            else {
                lo=mid+1;
            }
        }

        return lo;
    }
}