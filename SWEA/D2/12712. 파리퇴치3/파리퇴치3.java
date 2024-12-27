import java.util.Scanner;
import java.io.FileInputStream;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            // 입력
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] arr = new int[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
             
            // 구현
            int max = Integer.MIN_VALUE;
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    max = Math.max(max, calculate(arr, n, m, i, j));
                }
            }
             
            System.out.println("#" + test_case + " " + max);
        }
    }
     
    private static int calculate(int[][] arr, int n, int m, int row, int column) {
        int[][] plusDirection = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] xDirection = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
         
        int plusSum = arr[row][column];
        int xSum = arr[row][column];
        for(int i=1; i<m; i++) {
            for(int[] pd: plusDirection) {
                int nextRow = row + pd[0] * i;
                int nextColumn = column + pd[1] * i;
                if(isOut(n, nextRow, nextColumn)) continue;
                plusSum += arr[nextRow][nextColumn];
            }
            for(int[] xd: xDirection) {
                int nextRow = row + xd[0] * i;
                int nextColumn = column + xd[1] * i;
                if(isOut(n, nextRow, nextColumn)) continue;
                xSum += arr[nextRow][nextColumn];
            }
        }
         
        return Math.max(plusSum, xSum);
    }
     
    private static boolean isOut(int n, int row, int column) {
        if(row < 0 || row >= n) return true;
        if(column < 0 || column >= n) return true;
        return false;
    }
}