import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      int k = Integer.parseInt(br.readLine());
      
      int[] sensors = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
      Integer[] gaps = new Integer[n - 1];
      for(int i=0; i<n-1; i++) {
          gaps[i] = sensors[i + 1] - sensors[i];
      }
      Arrays.sort(gaps, (o1, o2) -> o2 - o1);
      gaps = Arrays.copyOf(gaps, k - 1);
      
      int sum = 0;
      int lastSensorIdx = 0;
      
      for(int i=1; i<n; i++) {
          int gap = sensors[i] - sensors[i - 1];
          if(isInGaps(gaps, gap)) {
              sum += sensors[i - 1] - sensors[lastSensorIdx];
              lastSensorIdx = i;
              continue;
          }
      }
      sum += sensors[n-1] - sensors[lastSensorIdx];
      
      System.out.println(sum);
    }
    
    static boolean isInGaps(Integer[] gaps, int gap) {
        for(int i=0; i<gaps.length; i++) {
            if(gap == gaps[i]) {
                gaps[i] = -1;
                return true;
            }
        }
        return false;
    }
}