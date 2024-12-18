import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static int[] arr;
    private static int[] result;
    private static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      // 입력
      StringTokenizer NM = new StringTokenizer(br.readLine());
      N = Integer.parseInt(NM.nextToken());
      M = Integer.parseInt(NM.nextToken());
      result = new int[M];
      
      StringTokenizer input = new StringTokenizer(br.readLine());
      arr = new int[N];
      for(int i=0; i<N; i++) {
        arr[i] = Integer.parseInt(input.nextToken());
      }
      
      // 정렬
      Arrays.sort(arr);
      
      // 구현
      for(int i=0; i<N; i++) {
        permutation(0, i);  
      }
      
      // 출력
      bw.write(answer.toString());
      bw.flush();
      br.close();
      bw.close();
  }
  
  private static void permutation(int count, int idx) throws IOException {
    result[count++] = arr[idx];
    
    if(count == M) {
      for(int i=0; i<M; i++) {
        answer.append(result[i]);
        if(i == M-1) answer.append("\n");
        else answer.append(" ");
      }
      return;
    }
    
    for(int i=0; i<N; i++) {
      if(isInResult(count, arr[i])) continue;
      permutation(count, i);
    }
  }
  
  // 이미 선택된 원소인지 확인
  private static boolean isInResult(int count, int target) {
    for(int i=0; i<count; i++) {
      if(target == result[i]) return true;
    }
    return false;
  }
}