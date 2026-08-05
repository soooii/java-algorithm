class Solution {
    public int solution(int[][] signals) {
        int value=1;
        
        //세 주기의 최소공배수 구하기
        for(int i=0;i<signals.length;i++){
            int cycle=signals[i][0]+signals[i][1]+signals[i][2];
            value=lcm(value, cycle); 
        }
        
        for(int i=1;i<=value;i++){
            boolean allYellow=true;
            
            for(int j=0;j<signals.length;j++){
                int g=signals[j][0];
                int y=signals[j][1];
                int r=signals[j][2];
                
                int cycle=g+y+r;
                int pos=(i-1)%cycle+1;
                
                if(!(g<pos&&pos<=g+y)){
                    allYellow=false;
                    break;
                }
            }
            
            if(allYellow){
                return i;
            }
        }
        
        return -1;
    }
    
    //최대공약수
    public int gcd(int a, int b){
        int r;
        while(b!=0){
            r=a%b;
            a=b;
            b=r;
        }
        return a;
    }
    
    //최소공배수
    public int lcm (int a, int b){
        return a*b/gcd(a,b); // (두수의 곱) / (최소공배수)
    }
}