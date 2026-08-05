import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int minX = 100001;
        int maxX = -100001;
        int minY = 100001;
        int maxY = -100001;
        for(int i=0;i<n;i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            int x = Integer.parseInt(ss[0]);
            int y = Integer.parseInt(ss[1]);
            // 최소, 최대 x 좌표
            if(x<minX){minX = x;}
            if(x>maxX){maxX = x;}
            // 최소, 최대 y 좌표
            if(y<minY){minY = y;}
            if(y>maxY){maxY = y;}
        }
        int width = maxX-minX;
        int length = maxY-minY;
        
        System.out.println(width*length);
    }
}