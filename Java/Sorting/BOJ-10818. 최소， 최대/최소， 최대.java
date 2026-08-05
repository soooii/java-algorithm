import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        String[] sarray = bf.readLine().split(" ");
        int[] narray = new int[N];
        for(int i=0; i<narray.length; i++){
            narray[i] = Integer.parseInt(sarray[i]);
        }
        Arrays.sort(narray);
        System.out.print(narray[0]+ " " + narray[N-1]);
    }
}