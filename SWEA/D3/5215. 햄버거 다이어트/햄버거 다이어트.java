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
          
          int[] scores = new int[ingredientCount];
          int[] calories = new int[ingredientCount];
          for(int i=0; i<ingredientCount; i++) {
              st = new StringTokenizer(br.readLine());
              scores[i] = Integer.parseInt(st.nextToken());
              calories[i] = Integer.parseInt(st.nextToken());
          }
          
          int[] dp = new int[calorieLimit + 1];
          for(int i=0; i<ingredientCount; i++) {
              for(int j=calorieLimit; j>0; j--) {
                  if(calories[i] > j) continue;
                  dp[j] = Math.max(dp[j], dp[j - calories[i]] + scores[i]);
              }
          }
          
          sb.append(dp[calorieLimit]).append("\n");
      }
      
      System.out.println(sb);
  }
}