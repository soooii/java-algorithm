import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int n = Integer.parseInt(s.split(" ")[0]);
        String game  =s.split(" ")[1]; // 게임 종류

        int person; // 게임에 필요한 인원
        if(game.equals("Y")) person=2;
        else if(game.equals("F")) person=3;
        else person=4;

        // 동일 사람과 다시 게임 X
        Set<String> set = new HashSet<>();

        for(int i=0;i<n;i++) {
            String name = br.readLine();
            set.add(name);
        }

        // 임스 무조건 참가 -> 인원수-1만큼 필요
        int max = set.size()/(person-1);

        System.out.println(max);
    }
}