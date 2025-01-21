import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      StringTokenizer st = null;
      
      // 입력
      int n = Integer.parseInt(br.readLine());
      int[] cards = new int[n];
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) cards[i] = Integer.parseInt(st.nextToken());
      
      int m = Integer.parseInt(br.readLine());
      int[] targets = new int[m];
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<m; i++) targets[i] = Integer.parseInt(st.nextToken());
      
      // 구현
      Arrays.sort(cards);
      for(int t: targets) {
        int start = 0;
        int end = n;
        boolean isSame = false;
        
        while(start < end) {
          int center = (start + end) / 2;
          
          if(cards[center] < t) start = center + 1;
          else if(cards[center] > t) end = center;
          else {
            isSame = true;
            break;
          }
        }
        
        if(isSame) sb.append(1);
        else sb.append(0);
        sb.append(" ");
      }
      
      System.out.println(sb.toString());
  }
}