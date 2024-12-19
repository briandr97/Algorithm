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
        arr = new int[n];
        dp = new int[n];
        
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        // 구현
        int max = recursive(n-1);
        
        bw.write(max + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
    
    private static int recursive(int idx) {
        if(idx == 0) return arr[0];
        if(idx == 1) return arr[0] + arr[1];
        if(idx == 2) return Math.max(arr[0] + arr[2], arr[1] + arr[2]);
        if(dp[idx] != 0) return dp[idx];
        
        int consecutive = recursive(idx-3) + arr[idx-1];
        int suspended = recursive(idx-2);
        dp[idx] = Math.max(consecutive, suspended) + arr[idx];
        return dp[idx];
    }
}
