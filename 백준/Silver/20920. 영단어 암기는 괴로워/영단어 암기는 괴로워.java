import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 단어 개수
        int m = Integer.parseInt(st.nextToken()); // 외울 단어 길이 기준

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (word.length() < m) continue;
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> words = new ArrayList<>(map.keySet());

        Collections.sort(words, (a, b) -> {
            int freqA = map.get(a);
            int freqB = map.get(b);
            if (freqA != freqB) return freqB - freqA;
            if (a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        });

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word).append('\n');
        }
        System.out.print(sb);
    }
}
