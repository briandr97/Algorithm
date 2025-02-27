import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
	static int N, L, R;
	static int[][] countries;
	static boolean[][] visited;
	static int[][] direction = {{1,0}, {0,-1}, {-1,0}, {0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		countries = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				countries[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		while(true) {
			boolean isMoved = false;
			visited = new boolean[N][N];
			List<Point> adj = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]) continue;
					int sum = dfs(i, j, adj);
					int average = sum / adj.size(); 
					for(Point country: adj) {
						countries[country.row][country.column] = average;
					}
					if(adj.size() != 1) isMoved = true;
					adj.clear();
				}
			}
			if(!isMoved) break;
			count++;
		}
		
		System.out.println(count);
	}
	
	static int dfs(int row, int column,  List<Point> adj) {
		int value = countries[row][column];
		adj.add(new Point(row, column));
		visited[row][column] = true;		
		int sum = value;
		
		for(int[] d: direction) {
			int nextRow = row + d[0];
			int nextColumn = column + d[1];
			if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N) continue;
			if(visited[nextRow][nextColumn]) continue;
			
			int nextValue = countries[nextRow][nextColumn];
			int gap = Math.abs(value - nextValue);
			if(gap >= L && gap <= R) {
				sum += dfs(nextRow, nextColumn, adj);
			}
		}
		
		return sum;
	}
	
	static class Point {
		int row, column;
		
		public Point(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
}
