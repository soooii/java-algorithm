import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<tangerine.length;i++){
            map.put(tangerine[i],map.getOrDefault(tangerine[i],0)+1);
        }
        
        List<Integer> count = new ArrayList<>(map.values());
        
        count.sort((a,b)->Integer.compare(b,a));
        
        int answer=0;
        int sum=0;
        for(int i=0;i<count.size();i++){
            if(sum>=k){
                break;
            }
            sum+=count.get(i);
            answer++;
        }
        return answer;
    }
}