import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int startRow, startColumn;
    static int max;
    static boolean[] visited;
    static int[][] board;
    static int[][] direction = {{1,-1}, {1,1}, {-1,1}, {-1,-1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int test = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=test; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            visited = new boolean[101];
            max = -1;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N-1; j++) {
                    startRow = i;
                    startColumn = j;
                    dfs(i, j, 0, 0);
                }
            }
            
            sb.append(max).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void dfs(int row, int column, int curD, int sum) {
        if(row == startRow && column == startColumn && sum != 0) {
            max = Math.max(max, sum);
            return;
        }
        
        if(visited[board[row][column]]) {
            return;
        }
        
        visited[board[row][column]] = true;
        
        int[] d = direction[curD];
        int nextR = row + d[0];
        int nextC = column + d[1];
        
        if(!(nextR < 0 || nextR >= N || nextC < 0 || nextC >= N)) {
            dfs(nextR, nextC, curD, sum + 1);
        }
        
        if(curD < 3) {
            d = direction[(curD + 1)];
            if((curD == 2) && (row - startRow != column - startColumn)) {
                visited[board[row][column]] = false;
                return;
            }
            
            nextR = row + d[0];
            nextC = column + d[1];
            if(!(nextR < 0 || nextR >= N || nextC < 0 || nextC >= N)) {
                dfs(nextR, nextC, curD + 1, sum + 1);
            } 
        }
        
        visited[board[row][column]] = false;
    }
}