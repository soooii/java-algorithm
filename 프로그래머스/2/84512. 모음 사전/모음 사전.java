import java.io.*;
import java.util.*;
class Solution {
    
    List<String> list = new ArrayList<>();
    String[] arr = {"A","E","I","O","U"};
    public int solution(String word) {
        
        dfs("");
        int answer = list.indexOf(word);
        return answer+1; //index 0부터 시작
        
    }
    
    void dfs(String str){
        if(str.length()>5) return;
        
        if(!str.equals("")){
            list.add(str);
        }
        
        for(int i=0;i<5;i++){
            dfs(str+arr[i]);
        }
    }
}