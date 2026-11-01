import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Character> ss= new Stack<>();
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                ss.push('(');
            }
            else{
                if(ss.isEmpty()) return false;
                ss.pop();
            }
        }
        
        if(ss.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}