import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer ve = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(ve.nextToken()); 
        int e = Integer.parseInt(ve.nextToken());
        int start = Integer.parseInt(br.readLine());
        
        Graph graph = new Graph(v, e);
        for(int i=0; i<e; i++) {
            StringTokenizer edgeToken = new StringTokenizer(br.readLine());
            graph.addEdge(
                Integer.parseInt(edgeToken.nextToken()), 
                Integer.parseInt(edgeToken.nextToken()), 
                Integer.parseInt(edgeToken.nextToken())
            );
        }
        
        Integer[] result = graph.solution(start);
        
        for(int i=1; i<v+1; i++) {
            if(result[i] == Graph.INF) bw.write("INF\n");
            else bw.write(result[i] + "\n");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }
}

class Graph {
    private final int v, e;
    private final List<ArrayList<Edge>> adj;
    private final boolean[] visited;
    private final PriorityQueue<Edge> minHeap = new PriorityQueue<>();
    private final Integer[] result;
    public static int INF = Integer.MAX_VALUE;
    
    public Graph(int v, int e) {
        this.v = v;
        this.e = e;
        adj = IntStream.range(0, v + 1).mapToObj(i -> new ArrayList<Edge>()).collect(Collectors.toList());
        visited = new boolean[v + 1];
        result = new Integer[v + 1];
        Arrays.fill(result, INF);
    }
    
    public void addEdge(int start, int end, int cost) {
        adj.get(start).add(new Edge(end, cost));
    }
    
    public Integer[] solution(int start) {
        minHeap.add(new Edge(start, 0));
        result[start] = 0;

        while(!minHeap.isEmpty()) {
            // 다음 노드 찾기
            Edge current = minHeap.poll();
            if(visited[current.destination]) continue;
            visited[current.destination] = true;
            
            // 다음 노드의 간선 확인하기
            for(Edge e: adj.get(current.destination)) {
                int newCost = result[current.destination] + e.cost;
                if(result[e.destination] > newCost) {
                    result[e.destination] = newCost;
                    minHeap.add(new Edge(e.destination, result[e.destination]));
                }
            }
        }
        return result;
    }
}

class Edge implements Comparable<Edge> {
    int destination, cost;
    
    public Edge(int destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge other) {
        if(cost > other.cost) return 1;
        else if(cost == other.cost) return 0;
        else return -1;
    }
}