class Solution {
    public int solution(int n) {
        int answer = 0;
        int count=0;
        for(int i=1;i<=n;i++){
            int sum=0;
            int temp=i;
            
            while(true){
                if(sum==n){
                    count++;
                    break;
                }
                else if(sum>n){
                    break;
                }
                else{
                    sum +=temp;
                    temp++;
                }
            }
        }
        answer=count;
        return answer;
    }
}