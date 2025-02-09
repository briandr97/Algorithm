import java.util.*;
import java.io.*;

public class Main {
    private static int[] dp;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      dp = new int[n + 1];
      System.out.println(recursive(n));
  }
  
  private static int recursive(int n) {
      if(n % 2 != 0) return 0;
      if(n == 0) return 1;
      if(n == 2) return 3;
      
      int sum = recursive(n-2) * recursive(2);
      for(int i=n-4; i>=0; i -= 2) {
          sum += recursive(i) * 2;
      }
      dp[n] = sum;
      return sum;
  }
}