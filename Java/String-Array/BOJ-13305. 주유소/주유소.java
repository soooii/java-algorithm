import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 왼 -> 오
        // 도시들 사이 거리 존재
        // 1km당 1리터 기름, 도시마다 주유소 존재
        // 리터당 기름값 각 주유소마다 다름

        int n = Integer.parseInt(br.readLine()); // 도시 개수
        String[] len = br.readLine().split(" "); // 도시 간 거리
        String[] price = br.readLine().split(" "); // 리터당 가격

        long minPrice = Integer.parseInt(price[0]);
        long total=0;
        // 맨 왼쪽 도시는 무조건 기름 넣어야함
        for(int i = 0; i < n-1; i++){
            if(Long.parseLong(price[i])<minPrice){
                minPrice = Long.parseLong(price[i]);
            }

            total += minPrice*Long.parseLong(len[i]);
        }

        System.out.println(total);

    }
}