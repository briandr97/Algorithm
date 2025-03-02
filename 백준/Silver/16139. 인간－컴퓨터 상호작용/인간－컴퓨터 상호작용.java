import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
      StringTokenizer st = null;
      StringBuilder sb = new StringBuilder();
      
      String str = br.readLine();
      int N = 'z' - 'a' + 1;
      int[][] sums = new int[str.length() + 1][N];
      
      for(int i=1; i<=str.length(); i++) {
        for(int j=0; j<N; j++) {
          sums[i][j] = sums[i - 1][j];
        }
        
        char c = str.charAt(i - 1);
        int cIndex = c - 'a';
        sums[i][cIndex]++;
      }
      
      int Q = Integer.parseInt(br.readLine());
      for(int i=0; i<Q; i++) {
        st = new StringTokenizer(br.readLine());
        char c = st.nextToken().charAt(0);
        int start = Integer.parseInt(st.nextToken()) + 1;
        int end = Integer.parseInt(st.nextToken()) + 1;
        
        int cIndex = c - 'a';
        int result = sums[end][cIndex];
        if(start > 0) {
          result -= sums[start - 1][cIndex];
        }
        sb.append(result).append("\n");
      }
      
      System.out.println(sb);
    }
}