import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    private static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine().trim();
        String second = br.readLine().trim();
        dp = new int[first.length()+1][second.length()+1];
        for(int i=1; i<=first.length(); i++) {
            for(int j=1; j<=second.length(); j++) {
                if(first.charAt(i-1) == second.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        System.out.println(dp[first.length()][second.length()]);
    }
    
    private static int max(int n1, int n2) {
        if(n1 > n2) return n1;
        else return n2;
    }
}