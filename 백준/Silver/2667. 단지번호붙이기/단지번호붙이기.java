import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine().trim());
        Graph graph = new Graph(n);
        for(int i=0; i<n; i++) {
            int[] input = Stream.of(br.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
            graph.addRow(i, input);
        }
        List<Integer> result = graph.dfs();
        System.out.println(result.size());
        result.stream().forEach(e -> System.out.println(e));
    }
}

class Graph {
    private int[][] arr;
    private HashMap<Integer, Integer> vertexCounts = new HashMap<>();
    private int label = 2;
    
    public Graph(int n) {
        arr = new int[n][n];
    }
    
    public void addRow(int column, int[] row) {
        for(int i=0; i<row.length; i++) {
            arr[column][i] = row[i];   
        }
    }
    
    public List<Integer> dfs() {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                if(arr[i][j] != 1) continue;
                vertexCounts.put(label, dfs(i, j));
                label++;
            }
        }
        
        return vertexCounts.values().stream().sorted().collect(Collectors.toList());
    }
    
    private int dfs(int column, int row) {
        if(column<0 || row<0 || column>=arr.length || row>=arr.length) return 0;
        if(arr[column][row] != 1) return 0;
        arr[column][row] = label;
        
        int count = 1;
        count += dfs(column-1, row);
        count += dfs(column+1, row);
        count += dfs(column, row-1);
        count += dfs(column, row+1);
        return count;
    }
}