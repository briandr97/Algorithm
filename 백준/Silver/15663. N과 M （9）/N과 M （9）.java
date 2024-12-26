import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    private static int n, m;
    private static int[] arr;
    private static boolean[] visited;
    private static final LinkedHashSet<String> result = new LinkedHashSet<>();
    private static final ArrayList<Integer> cur = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      // 입력 및 초기화
      StringTokenizer nm = new StringTokenizer(br.readLine());
      n = Integer.parseInt(nm.nextToken());
      m = Integer.parseInt(nm.nextToken());
      arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      Arrays.sort(arr);
      visited = new boolean[n];
      
      // 구현
      dfs(0);
      
      // 출력
      for(String s: result) {
        bw.write(s);  
      }
      bw.flush();
      br.close();
      bw.close();
    }
  
    private static void dfs(int count) {
      if(count == m) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++) {
          sb.append(cur.get(i));
          if(i == m-1) sb.append("\n");
          else sb.append(" ");
        }
        result.add(sb.toString());
        return;
      }
      
      for(int i=0; i<n; i++) {
        if(visited[i]) continue;
        cur.add(arr[i]);
        visited[i] = true;
        dfs(count + 1);
        cur.remove(cur.size() - 1);
        visited[i] = false;
      }
    }
}