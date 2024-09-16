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

        for (int i = 0; i < n; i++) {
            int[] row = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[i] = row;
        }

        Graph graph = new Graph(map);
        System.out.println(graph.solution());
    }
}

class Graph {
    int[][] map;
    Queue<int[]> gonnaBeMelted = new LinkedList<>();
    int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int outsideLabel = 2;
    private final int rowSize;
    private final int columnSize;

    public Graph(int[][] map) {
        this.map = map;
        rowSize = map.length;
        columnSize = map[0].length;
    }

    private boolean isOutOfIndex(int row, int column) {
        return row < 0 || column < 0 || row >= rowSize || column >= columnSize;
    }

    public int solution() {
        int time = 0;

        while (true) {
            int cheeseCount = 0;
            dfsOutsideCheese(0, 0);

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] != 1) continue;
                    if (isMeltingCheese(i, j)) cheeseCount++;
                }
            }

            while (!gonnaBeMelted.isEmpty()) {
                int[] cheese = gonnaBeMelted.poll();
                map[cheese[0]][cheese[1]] = outsideLabel;
            }

            if (cheeseCount == 0) break;
            time++;
            outsideLabel++;
        }
        return time;
    }

    public void dfsOutsideCheese(int row, int column) {
        if (isOutOfIndex(row, column)) return;
        if(map[row][column] == 1) return;
        if(map[row][column] == outsideLabel) return;

        map[row][column] = outsideLabel;
        for (int[] d : direction) {
            dfsOutsideCheese(row + d[0], column + d[1]);
        }
    }

    public boolean isMeltingCheese(int row, int column) {
        if (isOutOfIndex(row, column)) return false;

        int labelCount = 0;
        for (int[] d : direction) {
            if (isOutOfIndex(row + d[0], column + d[1])) continue;
            int label = map[row + d[0]][column + d[1]];
            if (label == outsideLabel) labelCount++;
        }

        if (labelCount >= 2) {
            gonnaBeMelted.add(new int[]{row, column});
            return true;
        }
        return false;
    }
}
