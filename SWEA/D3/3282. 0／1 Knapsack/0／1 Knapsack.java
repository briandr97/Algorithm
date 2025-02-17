import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] weights = new int[n + 1];
            int[] values = new int[n + 1];
            
            for(int i=1; i<=n; i++) {
                st = new StringTokenizer(br.readLine());
                weights[i] = Integer.parseInt(st.nextToken());
                values[i] = Integer.parseInt(st.nextToken());
            }
            
            int[][] dp = new int[n + 1][k + 1];
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=k; j++) {
                    int weight = weights[i];
                    int value = values[i];
                    // i번 물건을 담고 무게가 j일 때 최댓값
                    if(j - weight >= 0) {
                      dp[i][j] = dp[i - 1][j - weight] + value;  
                    }
                    
                    // i번 물건을 담지 않고 무게가 j일 때 최댓값
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
            
            sb.append(dp[n][k]).append("\n");
        }
        System.out.println(sb);
    }
}