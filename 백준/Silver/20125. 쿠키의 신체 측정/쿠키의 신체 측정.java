import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int heartX = 0;
        int heartY = 0;

        // 1. 머리 찾기 → 심장 위치 = 머리 바로 아래
        boolean found = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '*') {
                    heartX = i + 1; // 심장은 머리 바로 아래
                    heartY = j;
                    found = true;
                    break; // 안쪽 for 탈출
                }
            }
            if (found) break; // 바깥쪽 for도 탈출
        }

        // 2. 팔 계산
        int leftArm = 0;
        int j = heartY-1; // 심장의 왼쪽부터 시작

        while (j >= 0 && map[heartX][j] == '*') {
            leftArm++;
            j--; // 왼쪽으로 한 칸 이동
        }

        int rightArm = 0;
        int k= heartY+1;
        while(k<n && map[heartX][k]=='*'){
            rightArm++;
            k++;
        }

        // 3. 허리 계산
        int waist = 0;
        int waistX = heartX + 1;

        while (waistX < n && map[waistX][heartY] == '*') {
            waist++;
            waistX++; // 아래로 한 칸 이동
        }
        
        waistX--; // 실제 허리 끝 좌표로 보정
        
        // 4. 다리 계산
        int leftLeg = 0;
        int leftX = waistX + 1; // 다리 열은 심장기준 왼쪽, 오른쪽
        while (leftX < n && map[leftX][heartY - 1] == '*') {
            leftLeg++;
            leftX++;
        }

        int rightLeg = 0;
        int rightX = waistX + 1;
        while (rightX < n && map[rightX][heartY + 1] == '*') {
            rightLeg++;
            rightX++;
        }

        System.out.println((heartX + 1) + " " + (heartY + 1));
        System.out.println(leftArm + " " + rightArm + " " + waist + " " + leftLeg + " " + rightLeg);
    }
}