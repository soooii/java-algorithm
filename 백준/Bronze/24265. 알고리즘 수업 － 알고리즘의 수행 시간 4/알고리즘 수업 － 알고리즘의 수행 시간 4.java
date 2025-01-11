import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long time = n*(n+1)/2-n;

        System.out.println(time);
        System.out.println(2); // O(n^2)
    }
}

/*
public static int MenOfPassion(int[] A, int n){
    int sum = 0;
    for(int i=1;i<=n-1;i++){ // n-1
        for(int j=i+1;j<=n;j++){
            sum = sum + A[j]*A[i];
                
                //
                2~7 6
                3~7 5
                4~7 4
                5~7 3
                6~7 2
                7~7 1
                (n-1)*6 + (n-1)*5 + .... + (n-1)
                (n-1)(6+5+4+3+2+1)
                (n-1)(1에서 n-1까지의 합)
                1에서 n까지의 합(첫+끝 합 일정): n(n+1)/2
                1에서 n-1까지의 합 : n(n+1)/2-n
                //
        }
    }
    return sum;
}*/