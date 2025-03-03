import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        List<Edge>[] adj = (List<Edge>[]) new List[n + 1];
        for(int i=1; i<=n; i++) {
            adj[i] = new ArrayList<Edge>();
        }
        
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adj[end].add(new Edge(start, dist));
        }
        
        long[] cities = new long[n + 1];
        Arrays.fill(cities, Long.MAX_VALUE);
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
            int c = Integer.parseInt(st.nextToken());
            cities[c] = 0;
            for(Edge e : adj[c]) {
                if(cities[e.dest] < e.dist) continue;
                cities[e.dest] = e.dist;
                queue.add(e); 
            }
        }
        
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            if(cities[edge.dest] < edge.dist) continue;

            for(Edge e: adj[edge.dest]) {
                long newDist = edge.dist + e.dist;
                if(cities[e.dest] < newDist) continue;
                cities[e.dest] = newDist;
                queue.offer(new Edge(e.dest, newDist));
            }
        }
        
        int index = -1;
        long max = 0;
        for(int i=1; i<=n; i++) {
            if(cities[i] > max) {
                index = i;
                max = cities[i];
            }
        }
        
        System.out.println(index);
        System.out.println(max);
    }
}

class Edge implements Comparable<Edge> {
    int dest;
    long dist;
    
    public Edge(int dest, long dist) {
        this.dest = dest;
        this.dist = dist;
    }
    
    public int compareTo(Edge other) {
        if(dist > other.dist) return 1;
        else if(dist < other.dist) return -1;
        else return 0;
    }
}