import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int[] NM = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray(); 
      Graph graph = new Graph(NM[0]);
      for(int i=0; i<NM[1]; i++) {
        int[] edge = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph.addEdge(edge[0], edge[1]);
      }
      System.out.println(graph.dfs());
  }
}

class Graph {
  List<Stack<Integer>> vertexes;
  boolean[] visited;
  boolean[] finished;
  int componentCount = 0;
  
  public Graph(int vertexCount) {
    vertexes = IntStream.range(0, vertexCount+1).mapToObj(i -> new Stack<Integer>()).collect(Collectors.toList());
    visited = new boolean[vertexCount+1];
    finished = new boolean[vertexCount+1];
  }
  
  public void addEdge(int v1, int v2) {
      vertexes.get(v1).push(v2);
      vertexes.get(v2).push(v1);
  }
  
  public int dfs() {
    for(int i=1; i<vertexes.size(); i++) {
      if(visited[i]) continue;
      dfs(i);
    }
    return componentCount;
  }
  
  private void dfs(int cur) {
    visited[cur] = true;
    
    if(vertexes.get(cur).isEmpty()) {
      componentCount++;
      finished[cur] = true;
      return;
    }

    int next = vertexes.get(cur).peek();
    if(visited[next]) {
      if(!finished[next]) {
        componentCount++;
      }
    } else {
      dfs(next);
    }
    
    finished[cur] = true;
    vertexes.get(cur).forEach(n -> {
      visited[n]=true;
      finished[n]=true;
    });
  }
}