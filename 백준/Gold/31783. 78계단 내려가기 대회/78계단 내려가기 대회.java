import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    static int n;
    static long[] dp;
    static long[] heights;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null; 
        n = Integer.parseInt(br.readLine());
        heights = Stream.of(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        dp = new long[n];
        long[] tScores = new long[n];
        long[] tStrenghts = new long[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n; i++) {
            tScores[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n; i++) {
            tStrenghts[i] = Long.parseLong(st.nextToken());
        }
        
        for(int i=1; i<n; i++) {
            // 현재 계단의 보물 상자를 열 때
            long requiredHeight = heights[i] + tStrenghts[i];
            long openTreasure = 0;
            if(requiredHeight <= heights[0]) {
                int stair = getPreviousStair(requiredHeight, i);
                openTreasure = dp[stair] + tScores[i];
            }
            
            // 현재 계단의 보물 상자를 열지 않을 때
            long passTreasure = dp[i - 1];
            
            dp[i] = Math.max(openTreasure, passTreasure);
        }
        
        System.out.println(dp[n-1]);
    }
    
    static int getPreviousStair(long requiredHeight, int curStair) {
        int start = 0;
        int end = curStair;
        
        while(start + 1 < end) {
            int middle = (start + end) / 2;
            
            if(heights[middle] >= requiredHeight) {
                start = middle;
            } else end = middle;
        }
        
        return start;
    }
}