import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] board = new int[N+1][N+1];
        int[][] sum = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());  
            }
        }
        
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i == 1) {
                    sum[i][j] = board[i][j] + sum[i][j-1];
                } else if(j == 1) {
                    sum[i][j] = board[i][j] + sum[i-1][j];
                } else {
                    sum[i][j] = board[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1]; 
                }
            }
        }
        
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());  
            int y1 = Integer.parseInt(st.nextToken());  
            int x2 = Integer.parseInt(st.nextToken());  
            int y2 = Integer.parseInt(st.nextToken());
            sb.append(sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1-1][y1-1]).append("\n");
        }
        
        System.out.println(sb);
    }
}