import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        //5kg 이용
        //3kg..
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int n = Integer.parseInt(line);
        int answer = -1;
        boolean findanswer =false;
        //5의 횟수를 줄여가야하는데..
        if(n%5==0){
            answer = n/5;
        }
        else{
            int firstn = n;
            
            for(int i = n/5; i>=1;i--){
                n =firstn;
                n = n - i*5;
                if(n%3 == 0){
                    answer = i + n/3;
                    findanswer = true;
                    break;
                }
            }
            
            if(findanswer == false){
                n = firstn;
                if(n%3 ==0){
                    answer = n/3;
                }
                else{
                    answer = -1;
                }
            
            }
           
        }
        System.out.println(answer);
        
        
    }
}
