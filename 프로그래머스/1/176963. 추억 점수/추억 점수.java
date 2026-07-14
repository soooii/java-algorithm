import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo)      {
       Map<String, Integer> map = new HashMap<>();
        
        for(int i=0;i<name.length;i++){
            map.put(name[i],yearning[i]);
        }
        int i=0;
        int[] answer=new int[photo.length];
        for(String[] p:photo){
            int sum=0;
            for(String s:p){
                int score=map.getOrDefault(s,0);
                sum+=score;
            }
            answer[i]=sum;
            i++;
        }
        
        return answer;
    }
}