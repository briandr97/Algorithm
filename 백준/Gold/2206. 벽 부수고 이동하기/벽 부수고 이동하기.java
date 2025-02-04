import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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
        for (int i = 0; i < n; i++) {
            board[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][2];
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        queue.add(new Point(0, 0, 1));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.row == n - 1 && p.column == m - 1) return p.value;

            for (int[] d : direction) {
                int nextRow = p.row + d[0];
                int nextColumn = p.column + d[1];
                if (isOut(nextRow, nextColumn)) continue;

                if (board[nextRow][nextColumn] == 1) { // 다음 칸이 벽이면
                    if (p.isCrashed || visited[nextRow][nextColumn][1]) continue; // 이미 벽을 부쉈거나, 다음 칸을 방문했었다면 패스
                    visited[nextRow][nextColumn][1] = true;
                    queue.add(new Point(nextRow, nextColumn, p.value + 1, true));
                } else { // 다음 칸이 벽이 아니면
                    int idx = p.isCrashed ? 1 : 0;
                    if (visited[nextRow][nextColumn][idx]) continue; // 다음 칸을 방문했었다면 패스
                    visited[nextRow][nextColumn][idx] = true;
                    queue.add(new Point(nextRow, nextColumn, p.value + 1, p.isCrashed));
                }
            }
        }

        return -1;
    }

    private static boolean isOut(int row, int column) {
        if (row < 0 || row >= n) return true;
        if (column < 0 || column >= m) return true;
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
