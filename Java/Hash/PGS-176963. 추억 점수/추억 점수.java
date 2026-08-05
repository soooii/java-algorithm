import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo)      {
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<name.length;i++){
            map.put(name[i], yearning[i]);
        }
        
        int[] answer = new int[photo.length];
        
        for(int i=0;i<photo.length;i++){
            String[] target = photo[i];
            int score=0;
            for(String t:target){
                score += map.getOrDefault(t,0);
            }
            answer[i]=score;
        }
        
        return answer;
    }
}