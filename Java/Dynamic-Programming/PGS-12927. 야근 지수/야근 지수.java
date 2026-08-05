import java.io.*;
import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
       // dp? -> 그리디였다 (큰 수에서 빼는 것이 가장 이득)
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);
        for(int work:works){
            q.add(work);
        }
        
        for(int i=0;i<n;i++){
            if(q.isEmpty()) break;
            int max = q.poll();
            if(max==0) break;
            q.add(max-1);
        }
        long sum=0;
        for(int time: q){
            sum +=time*time;
        }
        
        return sum;
        
    }
}