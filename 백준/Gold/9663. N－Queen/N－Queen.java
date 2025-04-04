  import java.util.*;
  import java.io.*;
  
  public class Main {
      static int N;
      static int[] queens;
      
      public static void main(String[] args) throws IOException {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          StringBuilder sb = new StringBuilder();
          
          N = Integer.parseInt(br.readLine());
          queens = new int[N];
          System.out.println(perm(0));
      }
      
      private static int perm(int x) {
          if(x == N) return 1;
          
          int sum = 0;
          for(int y=0; y<N; y++) {
              if(!isValid(x, y)) continue;
              
              queens[x] = y;
              sum += perm(x + 1);
          }
          
          return sum;
      }
      
      private static boolean isValid(int x, int y) {
          for(int i=0; i<x; i++) {
              int j = queens[i];
              if(j == y || Math.abs(x - i) == Math.abs(y - j)) {
                  return false;
              }
          }
          
          return true;
      }
  }