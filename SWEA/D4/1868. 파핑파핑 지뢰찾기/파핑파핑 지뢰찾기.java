import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution {
    private static final int TRAP = -2;
    private static final int DOT = -1;
    private static int n;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[][] direction = {{1, 1}, {1, 0}, {1, -1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, 1}, {0, -1}};
     
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++) {
            // 입력
            n = sc.nextInt();
            board = new int[n][n];
            visited = new boolean[n][n];
            for(int i=0; i<n; i++) {
                String input = sc.next();
                for(int j=0; j<n; j++) {
                    if(input.charAt(j) == '*') board[i][j] = TRAP;
                    else if(input.charAt(j) == '.') board[i][j] = DOT;
                }
            }
             
            // 구현
            for(int i=0; i<n; i++) { // 모든 칸 체크하고 숫자 대입 + visitied에 지뢰 칸 체크
                for(int j=0; j<n; j++) {
                    checkTrap(i, j);
                    if(board[i][j] == TRAP) visited[i][j] = true;
                }
            }
             
            int clickCount = 0;
            for(int i=0; i<n; i++) { // 0 클릭 횟수 세기
                for(int j=0; j<n; j++) {
                    if(visited[i][j]) continue;
                    if(board[i][j] != 0) continue;
                    clickCount++;
                    dfs(i, j);
                }
            }
             
            for(int i=0; i<n; i++) { // 남은 공간 클릭 횟수 세기
                for(int j=0; j<n; j++) {
                    if(visited[i][j]) continue;
                    clickCount++;
                }
            }
 
            // 출력
            System.out.println("#" + test_case + " " + clickCount);
        }
    }
     
    private static void dfs(int row, int column) {
        if(visited[row][column]) return;
        visited[row][column] = true;
        if(board[row][column] != 0) return;
         
        for(int[] d: direction) {
            int nextRow = row + d[0];
            int nextColumn = column + d[1];
            if(isOut(nextRow, nextColumn)) continue;
            dfs(nextRow, nextColumn);
        }
    }
     
    private static void checkTrap(int row, int column) {
        if(board[row][column] != DOT) return;
        int sum = 0;
        for(int[] d: direction) {
            int nextRow = row + d[0];
            int nextColumn = column + d[1];
            if(isOut(nextRow, nextColumn)) continue;
            if(board[nextRow][nextColumn] == TRAP) sum += 1;
        }
        board[row][column] = sum;
    }
     
    private static boolean isOut(int row, int column) {
        if(row < 0 || row >= n) return true;
        if(column < 0 || column >= n) return true;
        return false;
    }
}