import java.util.*;
import java.io.*;

class Main {
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine().trim();
        String second = br.readLine().trim();
        int firstLen = first.length();
        int secondLen = second.length();
        dp = new int[first.length()+1][second.length()+1];
        
        for(int i=1; i<firstLen+1; i++) {
            for(int j=1; j<secondLen+1; j++) {
                if(first.charAt(i-1) == second.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        int row = firstLen;
        int column = secondLen;
        StringBuilder sb = new StringBuilder();
        while(dp[row][column] != 0) {
            if(first.charAt(row-1) == second.charAt(column-1)) {
                sb.append(first.charAt(row-1));
                row -= 1;
                column -= 1;
                continue;
            }
            if(dp[row-1][column] > dp[row][column-1]) {
                row -= 1;
            } else {
                column -= 1;
            }
        }
        
        String reversedAnswer = sb.toString();
        System.out.println(reversedAnswer.length());
        for(int i=reversedAnswer.length()-1; i>=0; i--) {
            System.out.print(reversedAnswer.charAt(i));
        }
    }
    
    private static int max (int num1, int num2) {
        if(num1 > num2) return num1;
        else return num2;
    }
}