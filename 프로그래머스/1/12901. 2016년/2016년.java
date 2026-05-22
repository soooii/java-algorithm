class Solution {
    public String solution(int a, int b) {
     
        int plus=0;
        switch(a){
            case 1:
                plus=(b-1)%7;
                break;
            case 2:
                plus=(31+b-1)%7;
                break;
            case 3:
                plus=(31+29+b-1)%7;
                break;
            case 4:
                plus=(31+29+31+b-1)%7;
                break;
            case 5:
                plus=(31+29+31+30+b-1)%7;
                break;
            case 6:
                plus=(31+29+31+30+31+b-1)%7;
                break;
            case 7:
                plus=(31+29+31+30+31+30+b-1)%7;
                break;
            case 8:
                plus=(31+29+31+30+31+30+31+b-1)%7;
                break;
            case 9:
                plus=(31+29+31+30+31+30+31+31+b-1)%7;
                break;
            case 10:
                plus=(31+29+31+30+31+30+31+31+30+b-1)%7;
                break;
            case 11:
                plus=(31+29+31+30+31+30+31+31+30+31+b-1)%7;
                break;
            case 12:
                plus=(31+29+31+30+31+30+31+31+30+31+30+b-1)%7;
                break;
        }
        String[] days=new String[] {"SUN","MON","TUE","WED","THU","FRI","SAT"};
        
        int start =5;
        int answer=start+plus;
        if(answer>6){
            answer=answer%7;
        }
        return(days[answer]);
      
    }
}