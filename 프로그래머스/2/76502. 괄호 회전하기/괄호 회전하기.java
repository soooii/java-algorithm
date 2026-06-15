import java.util.*;
import java.io.*;
class Solution {
    public int solution(String s) {
        int end = s.length();
        int start=0;
        int count=0;
        for(int i=0;i<end;i++){
            StringBuilder sb = new StringBuilder(s);
            for(int j=0;j<i;j++){
                char w=s.charAt(j);
                sb.append(w);
            }
            String rotatedString = sb.substring(i);
            if(judge(rotatedString)){
                count++;
            }
        
        }
        return count;
        
    }
    
    public boolean judge(String s){
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char w = s.charAt(i);
            
            // 여는 괄호는 스택에 담아둠
            if(w == '(' || w == '[' || w == '{'){
                stack.push(w);
            } 
            // 닫는 괄호가 나왔을 때
            else {
                if(stack.isEmpty()) return false; 
                
                char top = stack.pop(); // 가장 최근에 열린 괄호를 꺼내서 비교
                if(w == ')' && top != '(') return false;
                if(w == ']' && top != '[') return false;
                if(w == '}' && top != '{') return false;
            }
        }
        
        // 다 돌았는데 스택이 비어있어야 모든 짝이 맞은 것
        return stack.isEmpty();
    }
}