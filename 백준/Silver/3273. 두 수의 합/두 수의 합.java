import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null;
      
      // n 입력
      int n = Integer.parseInt(br.readLine());
      
      // 수열 입력
      int[] nums = new int[n];
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }
      
      // x 입력
      int x = Integer.parseInt(br.readLine());
      
      // 구현 시작
      int answer = 0;
      
      // n이 1이면 투포인터를 시작하는 조건을 만족할 수 없으므로 미리 처리한다.
      if(n == 1) {
        if(nums[0] == x) answer = 1;
        System.out.println(answer);
        return;
      }
      
      // 정렬 후 투포인터로 체크한다.
      Arrays.sort(nums);
      
      int front = 0;
      int rear = n-1;
      
      while(front < rear) {
          int sum = nums[front] + nums[rear];
          if(sum < x) front++;
          else if(sum > x) rear--;
          else {
            answer++;
            front++;
            rear--;
          }
      }
      
      System.out.println(answer);
  }
}