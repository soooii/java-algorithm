import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String new_s = s.trim();
  
        if (new_s.isEmpty()) {
            System.out.println(0);
        } else {
            // 공백을 기준으로 문자열을 분리하여 배열에 저장한 후, 배열의 길이를 출력합니다.
            String[] ss = new_s.split(" ");
            System.out.println(ss.length);
        }
    }
}