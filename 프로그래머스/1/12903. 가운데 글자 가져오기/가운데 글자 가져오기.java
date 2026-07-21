import java.io.*;
import java.util.*;
class Solution {
    public String solution(String s) {
        int l = s.length();
        int i = l%2;
        int k = l/2;
        StringBuilder sb = new StringBuilder();
        if(i==0){
            return sb.append(s.charAt(k-1)).append(s.charAt(k)).toString();
        }else{
            return sb.append(s.charAt(k)).toString();
        }
    }
}