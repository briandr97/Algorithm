import java.util.*;
import java.io.*;

class Main {
    private static int N, K;
    private static boolean[] visited = new boolean[100001];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bw.write(bfs() + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
    
    private static int bfs() {
        int count = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(N);
        
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            while(qSize-- > 0) {
                int e = queue.poll();
                if(e == K) return count;
                if(!isOutOrVisited(e + 1)) queue.add(e + 1);
                if(!isOutOrVisited(e - 1)) queue.add(e - 1);
                if(!isOutOrVisited(e * 2)) queue.add(e * 2);    
            }
            count++;
        }
        throw new IllegalStateException();
    }
    
    private static boolean isOutOrVisited(int idx) {
        if(idx < 0 || idx > 100000) return true;
        if(visited[idx]) return true;
        visited[idx] = true;
        return false;
    }
}