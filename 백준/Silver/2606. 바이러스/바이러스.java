import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int vertexCount = Integer.parseInt(br.readLine().trim());
      int edgeCount = Integer.parseInt(br.readLine().trim());
      Graph graph = new Graph(vertexCount);
      while(edgeCount-- > 0) {
        int[] input = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph.addEdge(input[0], input[1]);
      }
      System.out.println(graph.dfs());
  }
}

class Graph {
  boolean[] visited;
  List<ArrayList<Integer>> adjacentVertexes;
  
  public Graph(int vertexCount) {
    adjacentVertexes = IntStream.range(0, vertexCount+1).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
    visited = new boolean[vertexCount+1];
  }
  
  public void addEdge(int v1, int v2) {
    adjacentVertexes.get(v1).add(v2);
    adjacentVertexes.get(v2).add(v1);
  }
  
  public int dfs() {
    dfs(1);
    int answer = 0;
    for(int i=2; i<visited.length; i++) {
      if(visited[i]) answer++;
    }
    return answer;
  }
  
  private void dfs(int cur) {
    visited[cur] = true;
    adjacentVertexes.get(cur).forEach(e -> {
      if(!visited[e]) dfs(e);
    });
  }
}