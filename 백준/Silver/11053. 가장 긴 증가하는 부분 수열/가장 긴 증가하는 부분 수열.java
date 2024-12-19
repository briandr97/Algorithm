import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    private static int n;
    private static int[] arr;
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 입력 및 선언
        n = Integer.parseInt(br.readLine());
        arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[n];
        
        // 구현
        for(int i=0; i<n; i++) {
          lis(i);
        }
        
        // 최댓값 뽑기
        int max = 0;
        for(int i=0; i<n; i++) {
            max = Math.max(max, dp[i]);
        }
        
        bw.write(max + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
    
    private static int lis(int num) {
        if(dp[num] != 0) return dp[num];
        dp[num] = 1;
        
        for(int i=num-1; i>=0; i--) {
            if(arr[i] >= arr[num]) continue;
            dp[num] = Math.max(dp[num], lis(i) + 1);
        }
        
        return dp[num];
    }
}