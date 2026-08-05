import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        String[] ss = s.split("");
        LinkedList<String> list = new LinkedList<>(Arrays.asList(ss));
        ListIterator<String> it = list.listIterator();
        // 명령어 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다.
        while(it.hasNext()){
            it.next();
        }
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            String com = br.readLine();
            String[] comm = com.split(" ");
            if(comm[0].equals("P")){
                it.add(comm[1]);
            }
            if(comm[0].equals("L")){
                if(it.hasPrevious()){
                    it.previous();
                }
            }
            if(comm[0].equals("D")){
                if(it.hasNext()){
                    it.next();
                }
            }
            if(comm[0].equals("B")){
                if(it.hasPrevious()){
                    it.previous();
                    it.remove();
                }
            }
        }

        for(String ch : list){
            bw.write(ch);
        }
        
        bw.flush();
        bw.close();

    }
}