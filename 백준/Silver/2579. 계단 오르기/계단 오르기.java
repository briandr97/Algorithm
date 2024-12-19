import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    private static int n;
    private static int[] arr;
    private static int[] consecutiveSum;
    private static int[] suspendedSum;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 입력 및 선언
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        consecutiveSum = new int[n];
        suspendedSum = new int[n];
        
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        // 구현
        int max = Math.max(consecutiveLis(n-1), suspendedLis(n-1));
        
        bw.write(max + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
    
    private static int consecutiveLis(int idx) {
        if(idx == 0) return arr[0];
        if(idx == 1) return arr[0] + arr[1];
        if(consecutiveSum[idx] == 0) {
            consecutiveSum[idx] = suspendedLis(idx-1) + arr[idx];
        }
        return consecutiveSum[idx];
    }
    
    private static int suspendedLis(int idx) {
        if(idx <= 1) return arr[idx];
        if(suspendedSum[idx] == 0) {
            suspendedSum[idx] = Math.max(consecutiveLis(idx-2), suspendedLis(idx-2)) + arr[idx];
        }
        return suspendedSum[idx];
    }
}