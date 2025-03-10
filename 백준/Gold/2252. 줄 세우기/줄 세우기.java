import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<Integer>[] outEdges = (List<Integer>[]) new List[N + 1];
        for(int i=0; i<=N; i++) outEdges[i] = new ArrayList<Integer>();
        int[] inDegrees = new int[N + 1];
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            inDegrees[end]++;
            outEdges[start].add(end);
        }
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            if(inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty()) {
            int v = queue.poll();
            sb.append(v).append(" ");
            for(int dest: outEdges[v]) {
                inDegrees[dest]--;
                if(inDegrees[dest] == 0) {
                    queue.add(dest);
                }
            }
        }
        
        System.out.println(sb);
    }
}