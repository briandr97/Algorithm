import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
	static int[][] direction = { { 0, 1 }, { 0, -1 }, { -1, 0 } };
	static boolean[][] visited;
	static int[][] board;
	static final int N = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			sb.append("#").append(tc).append(" ");
			board = new int[N][N];
			visited = new boolean[N][N];
			
			int row = 0;
			int column = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 2) {
						row = i;
						column = j;
					}
				}
			}
			
			visited[row][column] = true;
			while(row > 0) {
				for(int[] d: direction) {
					int nextRow = row + d[0];
					int nextColumn = column + d[1];
					if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N) continue;
					if(visited[nextRow][nextColumn]) continue;
					if(board[nextRow][nextColumn] == 1) {
						visited[nextRow][nextColumn] = true;
						row = nextRow;
						column = nextColumn;
						break;
					}
				}
			}
			
			sb.append(column).append("\n");
		}
		
		System.out.println(sb);
	}
}
