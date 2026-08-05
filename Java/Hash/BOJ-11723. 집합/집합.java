import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String[] cmd = br.readLine().split(" ");
            String op = cmd[0];

            if (op.equals("add")) {
                set.add(Integer.parseInt(cmd[1]));
            } else if (op.equals("remove")) {
                set.remove(Integer.parseInt(cmd[1]));
            } else if (op.equals("check")) {
                if(set.contains(Integer.parseInt(cmd[1]))){
                    sb.append("1\n");
                }else {
                    sb.append("0\n");
                }
            } else if (op.equals("toggle")) {
                int x = Integer.parseInt(cmd[1]);
                if (set.contains(x)){
                    set.remove(x);
                }
                else {
                    set.add(x);
                }
            } else if (op.equals("all")) {
                set.clear();
                for (int j = 1; j <= 20; j++) {
                    set.add(j);
                }
            } else if (op.equals("empty")) {
                set.clear();
            }
        }
        System.out.print(sb);
    }
}