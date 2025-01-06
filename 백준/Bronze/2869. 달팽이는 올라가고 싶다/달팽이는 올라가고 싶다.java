import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] ss = s.split(" ");
        int a = Integer.parseInt(ss[0]);
        int b = Integer.parseInt(ss[1]);
        int v = Integer.parseInt(ss[2]);

        int days = (v - b) / (a - b); // 정상에 오르기 전날까지 날
        if ((v-b) % (a-b) != 0) {
            System.out.println(days + 1);
        } else {
            System.out.println(days);
        }

    }
}