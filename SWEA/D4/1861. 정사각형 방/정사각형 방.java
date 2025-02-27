import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
	static int N;
	static int maxMovement, minValue;
	static int[][] board;
	static boolean[][] visited;
	static int[][] direction = {{1,0}, {0,-1}, {-1,0}, {0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int test = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=test; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new boolean[N][N];
			maxMovement = 0;
			minValue = 1001;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					int result = dfs(i, j);
					
					if(result > maxMovement) {
						maxMovement = result;
						minValue = board[i][j];
					} else if(result == maxMovement) {
						if(board[i][j] < minValue) {
							maxMovement = result;
							minValue = board[i][j];
						}
					}
				}
			}
			
			sb.append(minValue).append(" ").append(maxMovement).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int dfs(int row, int column) {
		visited[row][column] = true;
		
		for(int[] d: direction) {
			int nextRow = row + d[0];
			int nextColumn = column + d[1];
			
			if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N) continue;
			if(board[nextRow][nextColumn] - board[row][column] != 1) continue;
			
			return dfs(nextRow, nextColumn) + 1;
		}
		
		return 1;
	}
}
