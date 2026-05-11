import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> h = new HashSet<>();
        for(int num:nums){
            h.add(num);
        }
        int answer;
        int max = nums.length/2;
        if(h.size()>max){
            answer=max;
        }
        else{
            answer=h.size();
        }
        
        return answer;
    }
}