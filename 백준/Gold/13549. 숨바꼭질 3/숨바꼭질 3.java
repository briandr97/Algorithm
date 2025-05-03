import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int MAX = 100000;
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int time = 0;
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(N);
        dist[N] = 0;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(cur == K) break;
            
            if(cur * 2 <= MAX && dist[cur * 2] > dist[cur]) {
                queue.addFirst(cur * 2);
                dist[cur * 2] = dist[cur];
            }
            
            if(cur + 1 <= MAX && dist[cur + 1] > dist[cur] + 1) {
                queue.add(cur + 1);
                dist[cur + 1] = dist[cur] + 1;
            }
            
            if(cur - 1 >= 0 && dist[cur - 1] > dist[cur] + 1) {
                queue.add(cur - 1);
                dist[cur - 1] = dist[cur] + 1;
            }
        }
        
        System.out.println(dist[K]);
    }
}