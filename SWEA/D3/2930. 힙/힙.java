import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            sb.append("#").append(tc).append(" ");
            
            int orderCount = Integer.parseInt(br.readLine());
            for(int i=0; i<orderCount; i++) {
                st = new StringTokenizer(br.readLine());
            
                int order = Integer.parseInt(st.nextToken());
                switch(order) {
                  case 1:{
                      int value = Integer.parseInt(st.nextToken());
                      maxHeap.add(value);
                      break;
                  }
                  case 2: {
                      if(maxHeap.isEmpty()) {
                          sb.append("-1").append(" ");
                      } else {
                          sb.append(maxHeap.poll()).append(" ");  
                      }
                      break;
                  }
                  default: {
                      throw new IllegalArgumentException();
                  }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}