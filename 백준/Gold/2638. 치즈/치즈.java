import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[n][m];
        
        for(int i=0; i<n; i++) {
            int[] row = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[i] = row;
        }
        
        Graph graph = new Graph(map);
        System.out.println(graph.solution());
    }
}

class Graph {
    int[][] map;
    boolean[][] gonnaBeMelt;
    int outsideLabel = 2;
    int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public Graph(int[][] map) {
        this.map = map;    
    }
    
    private int getWidth() {
        return map[0].length;
    }
    
    private int getHeight() {
        return map.length;
    }
        
    public int solution() {
        int time = 0;
        while(true) {
            gonnaBeMelt = new boolean[getHeight()][getWidth()];
            dfsOutsideCheese(0, 0);
            int cheeseCount = 0;
            for(int i=0; i<map.length; i++) {
                for(int j=0; j<map[0].length; j++) {
                    if(map[i][j] != 1) continue;
                    if(isMeltedCheese(i, j)) cheeseCount++;
                }
            }
            for(int i=0; i<map.length; i++) {
                for(int j=0; j<map[0].length; j++) {
                    if(gonnaBeMelt[i][j]) map[i][j] = outsideLabel;
                }
            }
            if(cheeseCount==0) break;
            time++;
            outsideLabel++;
            cheeseCount = 0;
        }
        return time;
    }
    
    public void dfsOutsideCheese(int column, int row) {
        if(column<0 || row<0 || column>=map.length || row>=map[0].length) return;
        if(map[column][row] == 1) return;
        if(map[column][row] == outsideLabel) return;
        
        map[column][row] = outsideLabel;
        for(int i=0; i<direction.length; i++) {
            dfsOutsideCheese(column + direction[i][0], row + direction[i][1]);
        }
    }
    
    public boolean isMeltedCheese(int column, int row) {
        if(column<0 || row<0 || column>=getHeight() || row>=getWidth()) return false;
        int labelCount=0;
        for(int i=0; i<direction.length; i++) {
            int nextColumn = column + direction[i][0];
            int nextRow = row + direction[i][1];
            if(nextColumn<0 || nextRow<0 || nextColumn>=getHeight() || nextRow>=getWidth()) continue;
            int label = map[nextColumn][nextRow];
            if(label == outsideLabel) labelCount++;
        }
        if(labelCount >= 2) {
            gonnaBeMelt[column][row] = true;
            return true;
        }
        return false;
    }
}