import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            String s1 = Integer.toBinaryString(c-'0'); // 각 자리를 2진수로
            if(s1.length()==2 && i !=0){ // 2진수 각 자리 3자리
                s1 = "0"+s1;
            }
            else if(s1.length()==1 && i !=0){
                s1 = "00"+s1;
            }
            sb.append(s1);
        }
        System.out.println(sb.toString());
    }
}