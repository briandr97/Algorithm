import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      final int LION = 1;
      final int APEACH = 2; 
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      int[] dolls = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int[] lionCountAccum = new int[n];
      for(int i=0; i<n; i++) {
          if(dolls[i] == LION) lionCountAccum[i]++;
          if(i > 0) lionCountAccum[i] += lionCountAccum[i - 1];
      }
      
      int[] compressedCount = new int[lionCountAccum[n - 1] + 1];
      int idx = 1;
      for(int i=0; i<n; i++) {
          if(lionCountAccum[i] == idx) {
              compressedCount[idx++] = i;
          }
      }
      
      int min = 1000001;
      for(int i=compressedCount.length - 1; i>0; i--) {
          if(i < k) break;
          int last = compressedCount[i];
          int start = compressedCount[i - k + 1];
          int result = last - start + 1;
          min = Math.min(min, result);
      }
      
      if(min == 1000001) min = -1;
      System.out.println(min);
    }
}