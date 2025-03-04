import java.util.*;
import java.io.*;

public class Main {
	static int[][] direction = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
	static int n;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("2번_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());
		board = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = 1;
		for (int score = 1; score <= 100; score++) {
			int count = 0;
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j])
						continue;
					if (board[i][j] <= score)
						continue;
					dfs(visited, score, i, j);
					count++;
				}
			}
			max = Math.max(max, count);
		}

		System.out.println(max);
	}

	static void dfs(boolean[][] visited, int score, int row, int column) {
		visited[row][column] = true;

		for (int[] d : direction) {
			int nextRow = row + d[0];
			int nextColumn = column + d[1];

			if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n)
				continue;
			if (visited[nextRow][nextColumn])
				continue;
			if (board[nextRow][nextColumn] <= score)
				continue;

			dfs(visited, score, nextRow, nextColumn);
		}
	}
}
