import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] cro = {"dz=","c=","c-","d-","lj","nj","s=","z="};

        for (String croAlpha : cro) {
            s = s.replace(croAlpha, "*");
        }
        System.out.println(s.length());

    }
}