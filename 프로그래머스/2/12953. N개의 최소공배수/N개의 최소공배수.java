class Solution {
    public int solution(int[] arr) {
        int lcm1 = arr[0];
        for(int i=1;i<arr.length;i++){
            int answer = lcm(lcm1,arr[i]);
            lcm1 = answer;
        }
        return lcm1;
    }
    
    public int gcd(int a, int b){
        int r;
        while(b!=0){
            r=a%b;
            a=b;
            b=r;
        }
        return a;
    }
    
    public int lcm(int a, int b){
        return (a*b)/gcd(a,b);
    }
}