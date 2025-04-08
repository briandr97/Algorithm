import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            sb.append("#").append(tc).append(" ");
            
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] volumes = new int[N];
            int[] costs = new int[N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                volumes[i] = Integer.parseInt(st.nextToken());
                costs[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] dp = new int[K + 1];
            for(int i=0; i<N; i++) {
                int volume = volumes[i];
                int cost = costs[i];
                
                for(int j=K; j>0; j--) {
                    if(volume > j) {
                        break;
                    }
                    
                    if(j - volume >= 0) {
                        dp[j] = Math.max(dp[j], cost + dp[j - volume]);
                    } else {
                        dp[j] = Math.max(dp[j], cost);
                    }
                }
            }
            
            sb.append(dp[K]).append("\n");
        }
        
        System.out.println(sb);
    }
}