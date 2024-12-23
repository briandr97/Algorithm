import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    private static int n;
    private static int[] wines;
    private static int[] dp;
    private static final int UN_FILL = -1;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      // 입력
      n = Integer.parseInt(br.readLine());
      wines = new int[n + 1];
      for(int i=1; i<=n; i++) {
        wines[i] = Integer.parseInt(br.readLine());
      }
      dp = new int[n + 1];
      Arrays.fill(dp, UN_FILL);
      dp[0] = 0;
      dp[1] = wines[1];
      if(n > 1) dp[2] = wines[1] + wines[2];
      
      // 구현
      int answer = recursive(n);
      
      // 출력
      bw.write(answer + "\n");
      bw.flush();
      br.close();
      bw.close();
  }
  
  private static int recursive(int idx) {
    if(dp[idx] != UN_FILL) return dp[idx];
    
    int suspended = recursive(idx - 2);
    int consecutive = wines[idx - 1] + recursive(idx - 3);
    dp[idx] = Math.max(suspended, consecutive) + wines[idx];
    dp[idx] = Math.max(dp[idx], recursive(idx - 1));
    return dp[idx];
  }
}