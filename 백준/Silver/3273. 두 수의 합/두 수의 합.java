import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null;
      
      // n 입력
      int n = Integer.parseInt(br.readLine());
      
      int[] nums = new int[n]; // 입력될 수열
      boolean[] hasNum = new boolean[1000001]; // 수열의 값이 해당 배열의 인덱스가 된다.
      
      // 수열 입력
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++) {
	      int num = Integer.parseInt(st.nextToken());
        nums[i] = num;
        hasNum[num] = true;
      }
      
      // x 입력
      int x = Integer.parseInt(br.readLine());
      
      // x와 수열의 수의 차이를 인덱스로 hasNum 배열을 탐색해 true이면 해당 값이 존재하므로 answer를 늘린다.
      int answer = 0;
      for(int i=0; i<n; i++) {
        int gap = x - nums[i];
        if(gap < hasNum.length && gap > 0 && hasNum[gap]) {
          answer++;
        }
      }
      
      System.out.println(answer/2);
  }
}