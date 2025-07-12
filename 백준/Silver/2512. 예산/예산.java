import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 예산 분배
        // 모든 요청 배정 X -> 특정 정수 상한액 설정, 그 이상인 요청은 모두 상한액 배정
        // total : 가능한 한 최대의 총 예산 구하기

        int n = Integer.parseInt(br.readLine()); // 지방 수
        String[] s = br.readLine().split(" ");
        int[] arr = new int[n];
        int sum=0;
        int max=-1;
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(s[i]);
            sum += arr[i];
            if(max<arr[i]){max=arr[i];}
        }
        int total = Integer.parseInt(br.readLine());

        if(total>=sum){
            System.out.println(max);
            return;
        }

        int min=0;

        // 이분 탐색
        // left, right
        while(min<=max){
            int mid = (min+max)/2; // 상한선
            long budget=0; // 책정 예산 합
            for(int i=0;i<n;i++){
                budget += Math.min(arr[i],mid);
            }
            if(budget<=total){ // 예산 이하, 상한선 높이기
                min = mid+1;
            }else{ // 예산 초과, 상한선 낮추기
                max = mid-1;
            }
        }

        System.out.println(max);

    }
}
