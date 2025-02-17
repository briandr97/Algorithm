import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    static List<List<Edge>> edges;
    static boolean[] visited;
    static int v, e;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        visited = new boolean[v + 1];
        
        edges = new ArrayList<>();
        for(int i=0; i<=v; i++) {
            edges.add(new ArrayList<Edge>());
        }
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.get(v1).add(new Edge(v2, weight));
            edges.get(v2).add(new Edge(v1, weight));
        }
        
        System.out.println(prim());
    }
    
    static int prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        edges.get(1).forEach(e -> pq.add(e));
        visited[1] = true;
        int edgeCount = 0;
        int sum = 0;
        
        while(!pq.isEmpty() && edgeCount < v) {
            Edge edge = pq.poll();
            if(visited[edge.v]) continue;
            
            visited[edge.v] = true;
            edgeCount++;
            sum += edge.w;
            edges.get(edge.v).forEach(e -> {
                if(!visited[e.v]) pq.add(e);
            });
        }
        
        return sum;
    }
}

class Edge implements Comparable<Edge> {
    int v, w;
    
    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
    
    @Override
    public int compareTo(Edge other) {
        return w - other.w;
    }
}