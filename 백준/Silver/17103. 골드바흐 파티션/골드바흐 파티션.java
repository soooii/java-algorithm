import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        // 미리 소수인지 판별해놓는다
        boolean[] arr = new boolean[1000001]; //0~1000,000
        for(int i=1;i<1000001;i++){
            arr[i] = isPrime(i);
        }

        // 짝수 N을 두 소수의 합으로 나타내는 표현을 골드바흐 파티션
        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            int count=0;
            for(int j=2; j<=n/2; j++){
                int a = j;
                int b= (n-j);
                if(arr[a] && arr[b]){
                    count++;
                }

            }
            sb.append(count).append("\n");
        }

        System.out.println(sb.toString());

    }

    // 소수 : 1보다 큰 자연수 중 1과 자기 자신만을 약수로 가지는 수
    public static boolean isPrime(int num){
        if(num == 1) {return false;}
        for(int i=2; i*i<=num; i++){
            if(num % i == 0) {return false;}
        }
        return true;
    }
}