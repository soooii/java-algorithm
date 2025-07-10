import java.io.*;

public class Main {
    static String[] chanel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        chanel = new String[n];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            chanel[i] = br.readLine();
        }

        // KBS1을 맨 앞으로
        int index = 0;
        while (!chanel[index].equals("KBS1")) {
            sb.append("1");
            index++;
        }
        while (index > 0) {
            swap(index, index - 1);
            index--;
            sb.append("4");
        }

        // KBS2 위치 다시 탐색
        index = 0;
        while (!chanel[index].equals("KBS2")) {
            sb.append("1");
            index++;
        }
        while (index > 1) {
            swap(index, index - 1);
            index--;
            sb.append("4");
        }

        System.out.println(sb);
    }

    public static void swap(int a, int b) {
        String tmp = chanel[a];
        chanel[a] = chanel[b];
        chanel[b] = tmp;
    }
}
