import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> s = new Stack<>();
        int truckIdx = 0; 
        
        for (int box = 1; box <= order.length; box++) {
            s.push(box); 
            
            while (!s.isEmpty() && s.peek() == order[truckIdx]) {
                s.pop(); 
                truckIdx++; 
            }
        }
        
        return truckIdx;
    }
}