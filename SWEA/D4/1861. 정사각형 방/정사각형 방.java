import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
	static int N;
	static int maxMovement, minValue;
	static int[][] board;
	static int[][] results;
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
			results = new int[N][N];
			maxMovement = 0;
			minValue = 1001;
			
			for(int i=0; i<N; i++) {
				board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(results[i][j] != 0) continue;
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
		if(results[row][column] != 0) return results[row][column]; 
		results[row][column] = 1;
		
		for(int[] d: direction) {
			int nextRow = row + d[0];
			int nextColumn = column + d[1];
			
			if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N) continue;
			if(board[nextRow][nextColumn] - board[row][column] != 1) continue;
			
			results[row][column] += dfs(nextRow, nextColumn);
			return results[row][column];
		}
		
		return results[row][column];
	}
}
