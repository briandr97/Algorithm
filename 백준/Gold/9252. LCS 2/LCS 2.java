import java.util.*;
import java.io.*;

public class Main {
    static String s1;
    static String s2;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      s1 = br.readLine();
      s2 = br.readLine();
      StringBuilder sb = new StringBuilder();
      
      dp = new int[s1.length() + 1][s2.length() + 1];
      for(int i=1; i<s1.length() + 1; i++) {
        for(int j=1; j<s2.length() + 1; j++) {
          char c1 = s1.charAt(i - 1);
          char c2 = s2.charAt(j - 1);
          if(c1 == c2) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
          } else {
            dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
          }
        }
      }
      
      sb.append(dp[s1.length()][s2.length()]).append("\n");
      if(dp[s1.length()][s2.length()] == 0) {
        System.out.println(sb);
        return;
      }
      
      ArrayDeque<Character> stack = new ArrayDeque<>();
      dfs(stack, s1.length(), s2.length());
      while(!stack.isEmpty()) {
        sb.append(stack.removeLast());
      }
      System.out.println(sb);
  }
  
  static void dfs(ArrayDeque<Character> stack, int row, int column) {
    if(row < 1 || column < 1) return;
    char c1 = s1.charAt(row - 1);
    char c2 = s2.charAt(column - 1);
    if(c1 == c2) {
      stack.add(c1);
      dfs(stack, row-1, column-1);
    } else {
      if(dp[row-1][column] > dp[row][column-1]) {
        dfs(stack, row-1, column);
      } else {
        dfs(stack, row, column-1);
      }
    }
  }
}