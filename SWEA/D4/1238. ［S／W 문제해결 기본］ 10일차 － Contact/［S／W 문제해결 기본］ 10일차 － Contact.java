import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        for(int tc=1; tc<=10; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            
            int n = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            
            List<Integer>[] adj = new List[101];
            for(int i=1; i<=100; i++) { 
                adj[i] = new ArrayList<Integer>();
            }
            
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n/2; i++) {
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                adj[s].add(e);
            }
            
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            boolean[] visited = new boolean[101];
            queue.add(start);
            visited[start] = true;
            
            int max = 0;
            while(!queue.isEmpty()) {
                int qSize = queue.size();
                int curMax = -1;
                
                while(qSize-- > 0) {
                    int cur = queue.poll();
                    for(int next: adj[cur]) {
                        if(visited[next]) continue;
                        queue.add(next);
                        visited[next] = true;
                        curMax = Math.max(curMax, next);
                    }
                }
                if(curMax != -1) max = curMax;
            }
            
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}