import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      int k = Integer.parseInt(br.readLine());
      if(k > n) {
          System.out.println(0);
          return;
      }
      
      int[] sensors = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
      Integer[] gaps = new Integer[n - 1];
      for(int i=0; i<n-1; i++) {
          gaps[i] = sensors[i + 1] - sensors[i];
      }
      Arrays.sort(gaps);
      gaps = Arrays.copyOf(gaps, n - k);
      
      int sum = Stream.of(gaps).mapToInt(g -> g).sum();
      System.out.println(sum);
    }
}
