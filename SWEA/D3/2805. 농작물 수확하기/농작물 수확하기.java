import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		StringBuilder sb = new StringBuilder();
    		StringTokenizer st = null;
    		int[][] direction = {{1,0}, {0,-1}, {-1,0}, {0,1}};
    		
    		int test = Integer.parseInt(br.readLine());
    		for(int tc=1; tc<=test; tc++) {
      			sb.append("#").append(tc).append(" ");
      			int N = Integer.parseInt(br.readLine());
      			int[][] farm = new int[N][N];
      			
      			for(int i=0; i<N; i++) {
      			    String input = br.readLine();
      			    for(int j=0; j<N; j++) {
      			        farm[i][j] = input.charAt(j) - '0';
      			    }
      			}
      			
      			boolean[][] visited = new boolean[N][N];
      			ArrayDeque<Point> queue = new ArrayDeque<>();
      			queue.add(new Point(N/2, N/2));
      			visited[N/2][N/2] = true;
      			int score = 0;
      			
      			for(int k=0; k<=N/2; k++) {
      			    int qSize = queue.size();
      			    
      			    for(int i=0; i<qSize; i++) {
      			        Point p = queue.poll();
      			        score += farm[p.row][p.column];
      			    
          			    for(int[] d: direction) {
          			        int nextRow = p.row + d[0];
          			        int nextColumn = p.column + d[1];
          			        
          			        if(nextRow < 0 || nextRow >= N || nextColumn < 0 || nextColumn >= N) continue;
          			        if(visited[nextRow][nextColumn]) continue;
          			        
          			        visited[nextRow][nextColumn] = true;
          			        queue.offer(new Point(nextRow, nextColumn));
          			    }
      			    }
      			}
      			
      			sb.append(score).append("\n");
		    }
		
		    System.out.println(sb);
	  }
}

class Point {
    int row, column;
    
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}