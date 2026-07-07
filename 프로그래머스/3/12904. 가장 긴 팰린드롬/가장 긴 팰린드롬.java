class Solution
{
    public int solution(String s)
    {   
        int maxLen=0;
        for(int i=0;i<s.length();i++){
            maxLen = Math.max(maxLen, getPailndrome(s,i,i));
            
            maxLen = Math.max(maxLen, getPailndrome(s,i,i+1));
        }
        
        return maxLen;
               
    }
    
    public int getPailndrome(String s, int left, int right){
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        
        return (right-1)-(left+1)+1;
    }
               
}