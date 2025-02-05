import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		final int n = 19;
		final int winingStandard = 5;
		final int empty = 0;
		int[][] board = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<Point> points = new ArrayList<>();
		int[][] direction = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, 1 } };
		int[][] oppositeDirection = { { -1, 0 }, { 0, -1 }, { -1, -1 }, { 1, -1 } };
		
		loop: for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int color = board[i][j];
				if (color == empty) continue;		
				
				for(int k=0; k<4; k++) {
					points.add(new Point(i, j, color));
					
					int[] d = direction[k];
					int row = i;
					int column = j;
					for (int r = 0; r < 5; r++) {
						row += d[0];
						column += d[1];
						if (row < 0 || row >= n || column < 0 || column >= n) break;
						if (board[row][column] != color) break;
						points.add(new Point(row, column, color));
					}
					
					d = oppositeDirection[k];
					row = i;
					column = j;
					for (int r = 0; r < 5; r++) {
						row += d[0];
						column += d[1];
						if (row < 0 || row >= n || column < 0 || column >= n) break;
						if (board[row][column] != color) break;
						points.add(new Point(row, column, color));
					}
					
					if (points.size() == winingStandard) break loop;
					points.clear();
				}
			}
		}

		if (points.size() != winingStandard) {
			System.out.println(0);
			return;
		}

		Collections.sort(points);
		sb.append(points.get(0).color).append("\n").append(points.get(0).row + 1).append(" ").append(points.get(0).column + 1);
		System.out.println(sb);
	}

}

class Point implements Comparable<Point> {
	int row, column, color;

	public Point(int row, int column, int color) {
		this.row = row;
		this.column = column;
		this.color = color;
	}

	@Override
	public int compareTo(Point other) {
		int result = column - other.column;
		if (result == 0) return row - other.row;
		return result;
	}
}
