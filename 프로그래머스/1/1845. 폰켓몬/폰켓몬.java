import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> h = new HashSet<>();
        
        for(int n:nums){
            h.add(n);
        }
        
        int l = h.size();
        if(l<=nums.length/2){
            return l;
        }
        else{
            return nums.length/2;
        }
    
        
    }
}