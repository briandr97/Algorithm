import java.io.*;
import java.util.*;
import java.util.stream.*;

class Solution
{
    static int n;
    static int[][] board;
    static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            for(int i=0; i<n; i++) {
                board[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
            System.out.println("#" + test_case + " " + bfs());
		}
	}
    
    private static int bfs() {
        PriorityQueue<Point> queue = new PriorityQueue<>();
        int[][] result = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }

        queue.add(new Point(0, 0, board[0][0]));
        result[0][0] = board[0][0];

        while (!queue.isEmpty()) {
            Point q = queue.poll();
            if (visited[q.row][q.column]) continue;
            visited[q.row][q.column] = true;

            for (int[] d : direction) {
                int nextRow = q.row + d[0];
                int nextColumn = q.column + d[1];
                if (isOut(nextRow, nextColumn)) continue;

                int nextTime = result[q.row][q.column] + board[nextRow][nextColumn];
                if (result[nextRow][nextColumn] > nextTime) result[nextRow][nextColumn] = nextTime;

                queue.add(new Point(nextRow, nextColumn, result[nextRow][nextColumn]));
            }
        }

        return result[n - 1][n - 1];
    }

    private static boolean isOut(int row, int column) {
        if (row < 0 || row >= n) return true;
        if (column < 0 || column >= n) return true;
        return false;
    }
}

class Point implements Comparable<Point> {
    final int row, column, time;

    public Point(int row, int column, int time) {
        this.row = row;
        this.column = column;
        this.time = time;
    }

    @Override
    public int compareTo(Point other) {
        if (time > other.time) return 1;
        else if (time == other.time) return 0;
        else return -1;
    }
}