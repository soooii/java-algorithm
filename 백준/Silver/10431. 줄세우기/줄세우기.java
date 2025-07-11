import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int p = Integer.parseInt(br.readLine());
        for(int i=0;i<p;i++){
            String s = br.readLine();
            String[] ss = s.split(" ");
            int[] arr = new int[20];
            for(int j=0;j<20;j++){
                arr[j]= Integer.parseInt(ss[j+1]);
            }

            int index =0;
            int cnt=0;
            int cnt_sum=0;
            while (index != 20){
                for(int j=0;j<index;j++){
                    if(arr[j]>arr[index]){
                        cnt++;
                    }
                }
                cnt_sum += cnt;
                cnt=0;
                index++;
            }

            System.out.println(ss[0]+" " + cnt_sum);



        }

    }
}