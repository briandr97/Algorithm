import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int vertexCount = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int firstTarget = Integer.parseInt(st.nextToken());
        int secondTarget = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(br.readLine().trim());

        Graph graph = new Graph(vertexCount);
        
        while(edgeCount-- > 0) {
            StringTokenizer input = new StringTokenizer(br.readLine().trim());
            int peopleOne = Integer.parseInt(input.nextToken());
            int peopleTwo = Integer.parseInt(input.nextToken());
            graph.addEdge(peopleOne, peopleTwo);
        }
        
        int answer = graph.bfs(firstTarget, secondTarget);
        System.out.println(answer);
    }
}

class Graph {
    int vertexCount;
    List<ArrayList<Integer>> adj;
    public Graph(int vertexCount) {
        this.vertexCount = vertexCount + 1;
        adj = IntStream.range(0, this.vertexCount).mapToObj(i->new ArrayList<Integer>()).collect(Collectors.toList());
    }
    
    public void addEdge(int p1, int p2) {
        adj.get(p1).add(p2);
        adj.get(p2).add(p1);
    }
    
    public int bfs(int start, int target) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visited = new boolean[vertexCount];
        q.add(start);
        visited[start] = true;
        int level = 0;
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int i=0; i<qSize; i++) {
                int v = q.poll();
                if(v == target) return level;
                
                for(int e: adj.get(v)) {
                    if(visited[e]) continue;
                    visited[e] = true;
                    q.add(e);
                }   
            }
            level++;
        }
        return -1;
    }
}