import java.util.*;
import java.io.*;
class Solution {
    public int solution(String s) {
        
        int end = s.length();
        int count =0;
        
        for(int i=0;i<end;i++){
            StringBuilder sb = new StringBuilder(s);
            
            for(int j=0;j<i;j++){
                sb.append(s.charAt(j));
            }
            
            String rotatedString = sb.substring(i);
            
            if(judge(rotatedString)){
                count++;
            }
            
        }
        return count;
        
    }
    
    boolean judge(String s){
            Stack<Character> stack = new Stack<>();
            
            for(int i=0;i<s.length();i++){
                char w = s.charAt(i);
                
                // 여는 괄호 나온 경우
                if(w=='('|| w=='[' || w=='{'){
                    stack.push(w);
                }
                
                // 닫는 괄호 나온 경우
                else{
                    if(stack.isEmpty()) return false;
                    char top = stack.pop();
                    if(w==')' && top != '(') return false;
                    if(w=='}' && top != '{') return false;
                    if(w==']' && top != '[') return false;
                }
            }
            
            return stack.isEmpty();
        }
}