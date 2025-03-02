import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] board = new int[n + 1][n + 1];
        final int INF = Integer.MAX_VALUE;
        
        for(int i=0; i<=n; i++) {
            Arrays.fill(board[i], INF);
        }
        
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            board[start][end] = Math.min(board[start][end], cost);
        }
        
        for(int mid=1; mid<=n; mid++) {
            for(int start=1; start<=n; start++) {
                if(board[start][mid] == INF) continue;
                for(int end=1; end<=n; end++) {
                    if(start == end) continue;
                    if(board[mid][end] == INF) continue;
                    int newCost = board[start][mid] + board[mid][end];
                    board[start][end] = Math.min(board[start][end], newCost);
                }
            }
        }
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(board[i][j] == INF) board[i][j] = 0;
                sb.append(board[i][j]);
                if(j != n) sb.append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}