import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] NMR = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        Graph graph = new Graph(NMR[0]);
        
        for(int i=0; i<NMR[1]; i++) {
            int[] edge = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.addEdge(edge[0], edge[1]);
        }
        
        int[] answer = graph.dfsAll(NMR[2]);
        for(int i=1; i<answer.length; i++) {
            bw.write(answer[i] + "\n");
        }
        
        bw.flush();
        bw.close();
    }
}

class Graph {
    List<ArrayList<Integer>> adjacentVertexes;
    int[] visited;
    int label = 1;
    
    public Graph(int vertexCount) {
        adjacentVertexes = IntStream.range(0, vertexCount + 1).mapToObj(i->new ArrayList<Integer>()).collect(Collectors.toList());
        visited = new int[vertexCount + 1];
    }
    
    public void addEdge(int v1, int v2) {
        adjacentVertexes.get(v1).add(v2);
        adjacentVertexes.get(v2).add(v1);
    }
    
    public int[] dfsAll(int start) {
        for(ArrayList<Integer> arr: adjacentVertexes) {
            arr.sort(Comparator.naturalOrder());
        }
        dfs(start);
        return visited;
    }
    
    private void dfs(int cur) {
        if(visited[cur]!=0) return;
        visited[cur] = label++;
        
        ArrayList<Integer> curAdj = adjacentVertexes.get(cur);
        for(int e: curAdj) {
            if(visited[e]==0) dfs(e);
        }
    }
}