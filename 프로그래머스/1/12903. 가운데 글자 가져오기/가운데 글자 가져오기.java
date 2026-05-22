import java.io.*;
import java.util.*;
class Solution {
    public String solution(String s) {
        int len = s.length();
        String answer;
        StringBuilder sb = new StringBuilder();
        if(len%2==0){ // 짝수
            sb.append(s.charAt(len/2-1));
            sb.append(s.charAt(len/2-1+1));
            answer=sb.toString();  
        }
        else{
            sb.append(s.charAt(len/2+1-1));
            answer=sb.toString();
        }
        return answer;
    }
}