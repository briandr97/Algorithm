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
      
      int front = 0, rear = 0;
      int sum = nums[0];
      int answer = 0;
      
      while(front < n) {
        if(sum == m) answer++;
        
        if(sum < m && rear < n - 1) sum += nums[++rear];
        else sum -= nums[front++];
      }
      
      System.out.println(answer);
  }
}