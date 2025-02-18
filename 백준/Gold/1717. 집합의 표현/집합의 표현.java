import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      	StringTokenizer st = new StringTokenizer(br.readLine());
      	
      	int n = Integer.parseInt(st.nextToken());
      	int m = Integer.parseInt(st.nextToken());
      	UnionFind uf = new UnionFind(n);
      	
      	for(int i=0; i<m; i++) {
      	    st = new StringTokenizer(br.readLine());
      	    int order = Integer.parseInt(st.nextToken());
      	    int a = Integer.parseInt(st.nextToken());
      	    int b = Integer.parseInt(st.nextToken());
      	    
      	    if(order == 0) {
      	        uf.union(a, b);
      	    } else {
      	        String result = uf.isConnected(a, b) ? "YES" : "NO";
      	        System.out.println(result);
      	    }
      	}
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    
    UnionFind(int n) {
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
    
    public boolean isConnected(int v1, int v2) {
        return find(v1) == find(v2);
    }
    
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        
        if(p1 == p2) return;
        
        if(rank[p1] > rank[p2]) {
            parent[p2] = p1;
        } else if(rank[p2] > rank[p1]) {
            parent[p1] = p2;
        } else {
            parent[p2] = p1;
            rank[p1]++;
        }
    }
}