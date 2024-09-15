import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            int[] WH = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(WH[0]==0 && WH[1]==0) break;
            
            int[][] map = new int[WH[1]][WH[0]];
            for(int i=0; i<WH[1]; i++) {
                int[] input = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                map[i] = input;
            }
            
            Graph graph = new Graph(map);
            bw.write(graph.dfs() + "\n");
        }
        
        bw.flush();
        bw.close();
    }
}

class Graph {
    int[][] map;
    boolean[][] visited;
    int[][] direction = { {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    int componentCount = 0;
    
    public Graph(int[][] map) {
        this.map = map;
        visited = new boolean[map.length][map[0].length];
    }
    
    public int dfs() {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[0].length; j++) {
                if(visited[i][j]) continue;
                if(map[i][j]!=1) continue;
                dfs(i, j);
                componentCount++;
            }
        }
        return componentCount;
    }
    
    public void dfs(int column, int row) {
        if(column<0 || row<0 || column>=map.length || row>=map[0].length) return;
        if(visited[column][row]) return;
        
        visited[column][row] = true;
        if(map[column][row] != 1) return;
        
        for(int i=0; i<direction.length; i++) {
            dfs(column + direction[i][0], row + direction[i][1]);
        }
    }
}