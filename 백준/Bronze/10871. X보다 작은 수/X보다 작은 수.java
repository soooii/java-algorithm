import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int X = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        String[] sarray = s.split(" ");
        int[] narray = new int[N];
        int i=0;
        for(String ss : sarray){
            int num = Integer.parseInt(ss);
            if(num < X){
                narray[i] = num;
                i++;
            }
        }
        for(int num : narray){
            if(num > 0){
                System.out.print(num + " ");
            }
        }

    }
}