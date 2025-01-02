import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String[] ss = s.split("");

        Map<String,Integer> map = new HashMap<>();
        for(String s1 :ss){
            String key = s1.toUpperCase();
            if(map.get(key) == null){
                map.put(key,1);
            }else{
                int origin = map.get(key);
                map.replace(key, origin+1);
            }
        }
        List<String> keys = new ArrayList<>(map.keySet());
        if(keys.size()==1){
            System.out.println(keys.get(0));
        } else {
            Collections.sort(keys, (v1, v2) -> (
                    map.get(v2).compareTo(map.get(v1))
            ));
            String first = keys.get(0);
            String second = keys.get(1);

            if ((int)map.get(first) == (int)map.get(second)) {
                System.out.println("?");
            } else {
                System.out.println(first);
            }
        }
    }
}