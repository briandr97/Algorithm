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
    boolean[][] gonnaBeMelted;
    int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int outsideLabel = 2;

    public Graph(int[][] map) {
        this.map = map;
    }

    private int getWidth() {
        return map[0].length;
    }

    private int getHeight() {
        return map.length;
    }
    
    private boolean isOutOfIndex(int column, int row) {
        return column < 0 || row < 0 || column >= getHeight() || row >= getWidth();
    }

    public int solution() {
        int time = 0;
        while(true) {
            gonnaBeMelted = new boolean[getHeight()][getWidth()];
            dfsOutsideCheese(0, 0);
            int cheeseCount = 0;
            for(int i=0; i<map.length; i++) {
                for(int j=0; j<map[0].length; j++) {
                    if(map[i][j] != 1) continue;
                    if(isMeltingCheese(i, j)) cheeseCount++;
                }
            }
            for(int i=0; i<map.length; i++) {
                for(int j=0; j<map[0].length; j++) {
                    if(gonnaBeMelted[i][j]) map[i][j] = outsideLabel;
                }
            }
            if(cheeseCount==0) break;
            time++;
            outsideLabel++;
        }
        return time;
    }

    public void dfsOutsideCheese(int column, int row) {
        if(isOutOfIndex(column, row)) return;
        if(map[column][row] == 1) return;
        if(map[column][row] == outsideLabel) return;

        map[column][row] = outsideLabel;
        for (int[] d : direction) {
            dfsOutsideCheese(column + d[0], row + d[1]);
        }
    }

    public boolean isMeltingCheese(int column, int row) {
        if(isOutOfIndex(column, row)) return false;
        
        int labelCount=0;
        for (int[] d : direction) {
            if(isOutOfIndex(column + d[0], row + d[1])) continue;
            int label = map[column + d[0]][row + d[1]];
            if (label == outsideLabel) labelCount++;
        }
        
        if(labelCount >= 2) {
            gonnaBeMelted[column][row] = true;
            return true;
        }
        return false;
    }
}
