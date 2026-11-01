import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String,Integer> map = new HashMap<>();
        
        for(String s:completion){
            map.put(s,map.getOrDefault(s,0)+1);
        }
        
        for(String s1:participant){
            if(map.getOrDefault(s1,0) == 0) return s1;
            map.put(s1,map.get(s1)-1);
        }
        
        return "";
    }
}