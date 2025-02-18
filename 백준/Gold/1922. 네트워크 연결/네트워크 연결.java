import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      	StringTokenizer st = null;
      	
      	int n = Integer.parseInt(br.readLine());
      	int m = Integer.parseInt(br.readLine());
      	Kruskal kruskal = new Kruskal(n);
      	
      	for(int i=0; i<m; i++) {
      	    st = new StringTokenizer(br.readLine());
      	    int v1 = Integer.parseInt(st.nextToken());
      	    int v2 = Integer.parseInt(st.nextToken());
      	    int w = Integer.parseInt(st.nextToken());
      	    kruskal.addEdge(v1, v2, w);
      	}
      	
      	System.out.println(kruskal.solution());
    }
}

class Kruskal {
    UnionFind uf;
    PriorityQueue<Edge> edges;
    int n;
    
    public Kruskal(int n) {
        this.n = n;
        uf = new UnionFind(n);
        edges = new PriorityQueue<>();
    }
    
    public void addEdge(int v1, int v2, int w) {
        edges.add(new Edge(v1, v2, w));    
    }
    
    public int solution() {
        int edgeCount = 0;
        int sum = 0;
        
        while(!edges.isEmpty() && edgeCount < n) {
            Edge e = edges.poll();
            if(uf.merge(e.v1, e.v2)) continue;
            
            sum += e.w;
            edgeCount++;
        }
        
        return sum;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        
        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int idx) {
        if(parent[idx] != idx) parent[idx] = find(parent[idx]);
        return parent[idx];
    }
    
    public int find2(int idx) {
        if(parent[idx] == idx) return idx;
        return find2(parent[idx]);
    }
    
    public boolean merge(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        
        if(p1 == p2) return true;
        
        if(rank[p1] > rank[p2]) {
            parent[p2] = p1;
        } else if(rank[p2] > rank[p1]) {
            parent[p1] = p2;
        } else {
            parent[p2] = p1;
            rank[p1]++;
        }
        
        return false;
    }
}

class Edge implements Comparable<Edge> {
    int v1, v2, w;
    
    public Edge(int v1, int v2, int w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
    
    @Override
    public int compareTo(Edge other) {
        return w - other.w;
    }
}