import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static int[] arr;
    private static int[] result;
    private static boolean[] visited;
    private static final StringBuilder answer = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      // 입력
      StringTokenizer NM = new StringTokenizer(br.readLine());
      N = Integer.parseInt(NM.nextToken());
      M = Integer.parseInt(NM.nextToken());
      visited = new boolean[N];
      result = new int[M];
      
      StringTokenizer input = new StringTokenizer(br.readLine());
      arr = new int[N];
      for(int i=0; i<N; i++) {
        arr[i] = Integer.parseInt(input.nextToken());
      }
      
      // 정렬
      Arrays.sort(arr);
      
      // 구현
      permutation(0);
      
      // 출력
      bw.write(answer.toString());
      bw.flush();
      br.close();
      bw.close();
  }
  
  private static void permutation(int count) throws IOException {
    if(count == M) {
      for(int i=0; i<M; i++) {
        answer.append(result[i]);
        if(i == M-1) answer.append("\n");
        else answer.append(" ");
      }
      return;
    }
    
    for(int i=0; i<N; i++) {
      if(visited[i]) continue;
      result[count] = arr[i];
      visited[i] = true;
      permutation(count + 1);
      visited[i] = false;
    }
  }
}
