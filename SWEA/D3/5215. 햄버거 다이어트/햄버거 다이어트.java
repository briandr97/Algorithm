import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      StringTokenizer st = null;
      
      int test = Integer.parseInt(br.readLine());
      for(int tc=1; tc<=test; tc++) {
          sb.append("#").append(tc).append(" ");
          st = new StringTokenizer(br.readLine());
          int ingredientCount = Integer.parseInt(st.nextToken());
          int calorieLimit = Integer.parseInt(st.nextToken());
          int[] scores = new int[ingredientCount + 1];
          int[] calories = new int[ingredientCount + 1];
          
          for(int i=1; i<=ingredientCount; i++) {
              st = new StringTokenizer(br.readLine());
              scores[i] = Integer.parseInt(st.nextToken());
              calories[i] = Integer.parseInt(st.nextToken());
          }
          
          int[][] dp = new int[ingredientCount + 1][calorieLimit + 1];
          for(int i=1; i<=ingredientCount; i++) {
              for(int j=1; j<=calorieLimit; j++) {
                  // 이 재료를 넣지 않는다.
                  dp[i][j] = dp[i - 1][j];
                  
                  // 이 재료를 넣는다.
                  int score = scores[i];
                  int calorie = calories[i];
                  if(calorie > j) continue;
                  score = dp[i-1][j - calorie] + score;
                  
                  dp[i][j] = Math.max(dp[i][j], score);
              } 
          }
          
          sb.append(dp[ingredientCount][calorieLimit]).append("\n");
      }
      
      System.out.println(sb);
  }
}