import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
    		StringBuilder sb = new StringBuilder();
    		StringTokenizer st = null;
    		
    		int test = sc.nextInt();
    		for(int tc=1; tc<=test; tc++) {
      			sb.append("#").append(tc).append(" ");
      			int N = sc.nextInt();
      			int[] mountains = new int[N];
      			for(int i=0; i<N; i++) {
      			    mountains[i] = sc.nextInt();
      			}
      			
      			// 봉우리 찾기
      		  List<Integer> topIndices = new ArrayList<>();
      		  for(int i=1; i<N-1; i++) {
      		      if(mountains[i] > mountains[i-1] && mountains[i] > mountains[i+1]) {
      		          topIndices.add(i);
      		      }
      		  }
      		  
      		  int count = topIndices.stream().mapToInt(index -> {
      		      // 좌측 탐색
      		      int left = 0;
      		      for(int i=index-1; i>=0; i--) {
      		          if(mountains[i] >= mountains[i + 1]) break;
      		          left++;
      		      }
      		      
      		      // 우측 탐색
      		      int right = 0;
      		      for(int i=index+1; i<N; i++) {
      		          if(mountains[i] >= mountains[i - 1]) break;
      		          right++;
      		      }
      		      
      		      return left * right;
      		  }).sum();
      		  
      			sb.append(count).append("\n");
		    }
		
		    System.out.println(sb);
	  }
}