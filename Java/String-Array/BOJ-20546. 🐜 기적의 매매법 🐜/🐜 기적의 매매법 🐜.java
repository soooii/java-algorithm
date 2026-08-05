import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cash = Integer.parseInt(br.readLine());
        int jcash = cash;
        int scash = cash;
        int[] stock = new int[14]; // 날짜별 주가

        String s = br.readLine();
        String[] ss = s.split(" ");
        for(int j = 0; j < 14; j++) {
            stock[j] = Integer.parseInt(ss[j]);
        }

        // 준현이
        int jcount = 0; // 준현이 보유 주식 수
        for(int i = 0; i < 14; i++) {
            if(stock[i]<=cash){
                jcount += jcash / stock[i];
                jcash = jcash % stock[i];
            }
        }
        int jasset = jcash + jcount * stock[13];

        // 성민이
        int scount = 0;
        for(int i = 0; i < 11; i++) {
            if(stock[i]<stock[i+1] && stock[i+1]<stock[i+2]){
                // 전량 매도
                scash += stock[i+3] * scount;
                scount = 0;
            }else if(stock[i]>stock[i+1] && stock[i+1]>stock[i+2]){
                // 전량 매수
                scount += scash / stock[i+3];
                scash = scash % stock[i+3];

            }
        }
        int sasset = scash + scount * stock[13];

        if(jasset>sasset){
            System.out.println("BNP");
        }else if(jasset<sasset){
            System.out.println("TIMING");
        }else{
            System.out.println("SAMESAME");
        }

    }
}