import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] original = new int[n][m];
      for(int i=0; i<n; i++) {
        original[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
      }
      
      int[][] target = new int[n][m];
      for(int i=0; i<n; i++) {
        target[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
      }
      
      int count = 0;
      for(int i=0; i<=n-3; i++) {
          for(int j=0; j<=m-3; j++) {
              if(original[i][j] != target[i][j]) {
                  count++;
                  convert(original, i, j);
              }
          }
      }
      
      for(int i=0; i<n; i++) {
          for(int j=0; j<m; j++) {
              if(original[i][j] != target[i][j]) {
                  System.out.println(-1);
                  return;
              }
          }
      }
      
      System.out.println(count);
    }
  
  static void convert(int[][] arr, int row, int column) {
      for(int i=row; i<row+3; i++) {
          for(int j=column; j<column+3; j++) {
              arr[i][j] ^= 1;
          }
      }
  }
}