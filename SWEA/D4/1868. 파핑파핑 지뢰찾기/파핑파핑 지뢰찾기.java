import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static char[][] board;
    static boolean[][] visited;
    static int[][] direction = {{1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int test = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=test; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            
            board = new char[N][N];
            for(int i=0; i<N; i++) {
                String str = br.readLine();
                for(int j=0; j<N; j++) {
                    board[i][j] = str.charAt(j);
                }
            }
            
            int zeroCount = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(board[i][j] != '.') continue;
                    setBombCount(i, j);
                }
            }
            
            int clickCount = 0;
            visited = new boolean[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(board[i][j] != '0' || visited[i][j]) continue;
                    dfs(i, j);
                    clickCount++;
                }
            }
            
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(board[i][j] == '*' || visited[i][j]) continue;
                    clickCount++;
                }
            }
            
            sb.append(clickCount).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void setBombCount(int row, int column) {
        char sum = '0';
        for(int[] d: direction) {
            int nRow = row + d[0];
            int nColumn = column + d[1];
            if(nRow < 0 || nRow >= N || nColumn < 0 || nColumn >= N) continue;
            if(board[nRow][nColumn] == '*') sum++;
        }
        board[row][column] = sum;
    }
    
    static void dfs(int row, int column) {
        visited[row][column] = true;
        if(board[row][column] != '0') return;
        
        for(int[] d: direction) {
            int nRow = row + d[0];
            int nColumn = column + d[1];
            if(nRow < 0 || nRow >= N || nColumn < 0 || nColumn >= N) continue;
            if(board[nRow][nColumn] == '*' || visited[nRow][nColumn]) continue;
            dfs(nRow, nColumn);
        }
    }
}