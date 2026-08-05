import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;

        while(!(s=br.readLine()).equals("end")){
            boolean first = false; // 첫번째 조건
            boolean isAcceptable = true; // 두번째,세번째 조건
            int n=0; // 연속된 모음 개수
            int m=0; // 연속된 자음 개수

            for(int i=0;i<s.length();i++){
                if("aeiou".indexOf(s.charAt(i)) != -1){
                    first=true;
                    n++;
                    m=0;
                }
                else{
                    m++;
                    n=0;
                }

                if(n==3 || m==3 ){
                    isAcceptable=false;
                    break;
                }

                if(i>0){
                    if(s.charAt(i) == s.charAt(i-1) && s.charAt(i)!='e' && s.charAt(i)!='o'){
                        isAcceptable=false;
                        break;
                    }
                }
            }
            if(first && isAcceptable){
                System.out.println("<"+s+"> "+"is acceptable.");
            }else{
                System.out.println("<"+s+"> "+"is not acceptable.");
            }
        }
    }
}