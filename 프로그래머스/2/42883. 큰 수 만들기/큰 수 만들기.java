class Solution {
    public String solution(String number, int k) {
        int len = number.length();
        StringBuilder sb = new StringBuilder();
     
        for (int i = 0; i < len; i++) {
            char current = number.charAt(i);
            
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) < current && k > 0) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            
            sb.append(current);
        }
        
        // 이미 내림차순이라 k가 남았다면 남은 개수만큼 뒤 자르기
        return sb.substring(0, sb.length() - k);
    }
}