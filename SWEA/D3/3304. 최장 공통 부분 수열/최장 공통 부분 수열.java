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
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            
            // DP는 파티셔닝이다.
            // i번째 문자열이 공통인가? 아닌가?
            int dp[][] = new int[s1.length() + 1][s2.length() + 1];
            
            for(int i=1; i<=s1.length(); i++) {
                for(int j=1; j<=s2.length(); j++) {
                    char c1 = s1.charAt(i - 1);
                    char c2 = s2.charAt(j - 1);
                    
                    if(c1 == c2) { // 공통인가 
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else { // 아닌가
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            sb.append(dp[s1.length()][s2.length()]).append("\n");
        }
        
        System.out.println(sb);
    }
}
