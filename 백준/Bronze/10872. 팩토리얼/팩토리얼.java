import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 1;
        if(n!=0){
           for(int i=0; i<n; i++){
            answer = answer * (n-i);
              
        } 
        
        
      }
      System.out.println(answer);
}
}