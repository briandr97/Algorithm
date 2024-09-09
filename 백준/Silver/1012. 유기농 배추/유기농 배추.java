import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(br.readLine().trim());
        
        for(int i=0; i<repeat; i++) {
            String[] s = br.readLine().trim().split(" ");
            List<Integer> input = Stream.of(s).map(Integer::parseInt).collect(Collectors.toList());
            Graph g = new Graph(input.get(0), input.get(1), input.get(2));
            
            for(int vertexCount=0; vertexCount<input.get(2); vertexCount++) {
                String vertexString = br.readLine().trim();
                List<Integer> vertex = Stream.of(vertexString.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
                g.addVertex(vertex.get(0), vertex.get(1));
            }
            System.out.println(g.dfs());
        }
    }
}

class Graph {
    private List<ArrayList<Boolean>> visited;
    private List<ArrayList<Integer>> cabages;
    
    public Graph(int column, int row, int count) {
        visited = IntStream.range(0, column).mapToObj(c -> 
            new ArrayList<Boolean>(IntStream.range(0, row).mapToObj(r -> false).collect(Collectors.toList()))
        ).collect(Collectors.toList());
        cabages = IntStream.range(0, column).mapToObj(c ->
            new ArrayList<Integer>(IntStream.range(0, row).map(r -> 0).boxed().collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
    
    public void addVertex(int column, int row) {
        cabages.get(column).set(row, 1);
    }
    
    public int dfs() {
        int componentCount = 0;
        for(int column=0; column<getColumnSize(); column++) {
            for(int row=0; row<getRowSize(); row++) {
                if(cabages.get(column).get(row) == 0) continue;
                if(visited.get(column).get(row)) continue;
                dfs(column, row);
                componentCount++;
            }
        }
        return componentCount;
    }
        
    private void dfs(int column, int row) {
        if(column < 0 || row < 0 || column >= getColumnSize() || row >= getRowSize()) return;
        if(cabages.get(column).get(row) == 0) return;
        if(visited.get(column).get(row)) return;
        visited.get(column).set(row, true);
        dfs(column-1, row);
        dfs(column+1, row);
        dfs(column, row-1);
        dfs(column, row+1);
    }
    
    private int getColumnSize() {
        return visited.size();
    }
    
    private int getRowSize() {
        return visited.get(0).size();
    }
}