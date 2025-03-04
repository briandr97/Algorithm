import java.util.*;
import java.io.*;

public class Solution {
	static int n;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int test = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test; tc++) {
			sb.append("#").append(tc).append("\n");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();

			board = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(direction.equals("up")) for(int i=0; i<3; i++) rotate();
			else if(direction.equals("right")) for(int i=0; i<2; i++) rotate();
			else if(direction.equals("down")) rotate();
			
			ArrayDeque<Integer> queue = new ArrayDeque<>();
			for(int i=0; i<n; i++) {
				boolean isMerged = false;
				for(int j=0; j<n; j++) {
					if(board[i][j] == 0) continue;
					if(!isMerged && !queue.isEmpty() && queue.peekLast() == board[i][j]) {
						queue.pollLast();
						queue.add(board[i][j] * 2);
						isMerged = true;
						continue;
					}
					queue.offer(board[i][j]);
					isMerged = false;
				}
				
				for(int j=0; j<n; j++) {
					board[i][j] = 0;
					if(queue.isEmpty()) continue;
					board[i][j] = queue.poll();
				}
			}
			
			if(direction.equals("up")) rotate();
			else if(direction.equals("right")) for(int i=0; i<2; i++) rotate();
			else if(direction.equals("down")) for(int i=0; i<3; i++) rotate();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void rotate() {
		int[][] temp = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				temp[j][n - 1 - i] = board[i][j];
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				board[i][j] = temp[i][j];
			}
		}
	}
}
