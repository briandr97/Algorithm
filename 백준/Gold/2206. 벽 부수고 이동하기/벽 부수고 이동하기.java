import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Main {
    private static int n;
    private static int m;
    private static int[][] board;
    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for(int i=0; i<n; i++) {
            board[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        int[][][] visited = new int[n][m][2];
        int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};

        queue.add(new Point(0,0,1));
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                Arrays.fill(visited[i][j], MAX);
            }
        }
        visited[0][0][0] = 1;
        visited[0][0][1] = 1;

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int[]d: direction) {
                int nextRow = p.row + d[0];
                int nextColumn = p.column + d[1];
                if(isOut(nextRow, nextColumn)) continue;
                if(board[nextRow][nextColumn] == 1) {
                    if(p.isCrashed) continue;
                    if(visited[nextRow][nextColumn][1] <= p.value + 1) continue;
                    visited[nextRow][nextColumn][1] = p.value + 1;
                    queue.add(new Point(nextRow, nextColumn, visited[nextRow][nextColumn][1], true));
                } else {
                    int idx = p.isCrashed ? 1 : 0;
                    if(visited[nextRow][nextColumn][idx] <= p.value + 1) continue;
                    visited[nextRow][nextColumn][idx] = p.value + 1;
                    queue.add(new Point(nextRow, nextColumn, visited[nextRow][nextColumn][idx], p.isCrashed));
                }
            }
        }

        int result = Math.min(visited[n-1][m-1][0], visited[n-1][m-1][1]);
        if(result == MAX) return -1;
        return result;
    }

    private static boolean isOut(int row, int column) {
        if(row < 0 || row >= n) return true;
        if(column < 0 || column >= m) return true;
        return false;
    }
}

class Point {
    int row;
    int column;
    int value;
    boolean isCrashed;

    public Point(int row, int column, int value, boolean isCrashed) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.isCrashed = isCrashed;
    }

    public Point(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
        isCrashed = false;
    }
}