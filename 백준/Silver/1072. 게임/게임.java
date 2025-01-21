import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      
      // 입력
      StringTokenizer st = new StringTokenizer(br.readLine());
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      long z = y * 100 / x;
      
      // 구현
      long ans = -1;
      if(z >= 99) {
        System.out.println(ans);
        return;
      }
      
      long low = 1;
      long high = x;
      while(low <= high) {
        long mid = (low + high) / 2;
        long curZ = (y + mid) * 100 / (x + mid);
        
        if(curZ >= z + 1) {
          ans = mid;
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      
      System.out.println(ans);
  }
}