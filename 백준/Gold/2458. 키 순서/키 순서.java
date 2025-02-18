import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      boolean[][] paths = new boolean[n + 1][n + 1];
      
      for(int i=0; i<m; i++) {
          st = new StringTokenizer(br.readLine());
          int v1 = Integer.parseInt(st.nextToken());
          int v2 = Integer.parseInt(st.nextToken());
          paths[v1][v2] = true;
      }
      
      for(int k=1; k<=n; k++) {
          for(int i=1; i<=n; i++) {
              for(int j=1; j<=n; j++) {
                  if(i == j) continue;
                  if(paths[i][j]) continue;
                  if(!paths[i][k] || !paths[k][j]) continue;
                  paths[i][j] = true;
              }
          }
      }
      
      int count = 0;
      for(int i=1; i<=n; i++) {
          int sum = 0;
          for(int j=1; j<=n; j++) {
              if(paths[i][j]) sum++;
              if(paths[j][i]) sum++;
          }
          if(sum == n - 1) count++;
      }
      
      System.out.println(count);
  }
}