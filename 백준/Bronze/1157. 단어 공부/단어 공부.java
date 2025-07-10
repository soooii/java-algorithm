import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next().toUpperCase();
        // 알파벳은 26개, 각 알파벳 등장 횟수 저장 위한 배열
        int[] count = new int[26];
        for(int i=0;i<s.length();i++){
            int num = s.charAt(i)-'A'; // index A는 0부터
            count[num]++;
        }
        int max = 0;
        char answer = '?';
        for(int i=0;i<count.length;i++){
            if(max<count[i]){
                max = count[i];
                answer=(char)('A'+i); // 문자 -> 알파벳
            }else if (max==count[i]){
                answer= '?';
            }
        }
        System.out.println(answer);

    }


}