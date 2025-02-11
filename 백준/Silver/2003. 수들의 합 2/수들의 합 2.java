import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(br.readLine());
      int[] nums = new int[n];
      for(int i=0; i<n; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }
      
      int front = 0;
      int rear = 0;
      
      int answer = 0;
      int sum = nums[0];
      
      while(front < n && rear < n) {
        if(sum == m) {
          answer++;
          if(rear < n - 1) {
            sum += nums[++rear];
            continue;
          } else break;
        }
        
        if(sum < m) {
          if(rear >= n - 1) break;
          sum += nums[++rear];
        } 
        else if(sum > m) sum -= nums[front++];
      }
      
      System.out.println(answer);
  }
}