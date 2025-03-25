import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        // 소수점 기준 3자리씩 묶기
        //끝부터 시작
        int count =0;
        int sum=0;
        StringBuilder sb = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){

            // char을 int 타입으로
            int n = Character.getNumericValue(s.charAt(i));

            if(count==0){
                sum+=n*1;
            }
            if(count==1){
                sum+=n*2;
            }
            if(count==2){
                sum+=n*4;
            }
            count++;

            if(count==3 || i==0){
                sb.insert(0,sum);
                count=0;
                sum=0;
            }
        }
        

        System.out.println(sb.toString());
    }
}