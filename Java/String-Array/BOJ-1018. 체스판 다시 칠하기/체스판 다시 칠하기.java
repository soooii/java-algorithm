import java.io.*;

public class Main {
    public static int getMinCost(int startrow, int startcol, String[] board){
        String[] compare = {"WBWBWBWB","BWBWBWBW"};
        // 가장 왼상단이 white인 기준의 최소 비용
        int count = 0;
        for(int i=0;i<8; i++){
            int row = startrow+i;
            for(int j=0;j<8;j++){
                int col = startcol+j;
                // 가장 왼상단이 white 기준이면
                // W가 0과 짝수 row, B가 홀수 row의 비교대상임으로 %2
                if(board[row].charAt(col) != compare[row%2].charAt(j)){
                    count++;
                }
            }
        }

        // 체스판 크기 = white 기준 최소비용 + black 기준 최소비용
        return Math.min(count, 64-count);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] ss = s.split(" ");
        int n = Integer.parseInt(ss[0]);
        int m = Integer.parseInt(ss[1]);
        String[] board = new String[n];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine();
        }
        br.close();
        int min_count = 65;
        for(int i=0;i<=n-8;i++) {
            for(int j=0;j<=m-8;j++) {
                int result = getMinCost(i,j,board);
                if(result < min_count) {
                    min_count = result;
                }
            }
        }
        System.out.println(min_count);

    }
}