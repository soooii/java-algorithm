import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));        
        
        int len = routes.length;
        
        int lastcamera = routes[0][1];
        int count=1;
        for(int[] route:routes){
            if(route[0]>lastcamera){
                count++;
                lastcamera=route[1];
            }
        }
        return count;
    }
}