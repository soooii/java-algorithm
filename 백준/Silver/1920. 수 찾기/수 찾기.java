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
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<m;i++){
            num2[i] = Integer.parseInt(s2[i]);
            map.put(num2[i],0);
        }

        // 이분탐색 : 존재 여부만 판단, break
        Arrays.sort(num);

        for(int i=0;i<m;i++){
            int left = 0;
            int right = num.length;
            while(left<right){
                int mid = (left+right)/2;
                if(num[mid]>num2[i]){
                    right = mid;
                }
                else if (num2[i] == num[mid]){
                    map.put(num2[i],1);
                    break;
                }
                else {
                    left = mid+1;
                }

            }
        }

        for(int i=0;i<m;i++){
            System.out.println(map.get(num2[i]));
        }

    }
}