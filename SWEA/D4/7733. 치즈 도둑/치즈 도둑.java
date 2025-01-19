import java.io.*;
import java.util.*;

class Solution {
    private static final int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int[][] copy;
    private static int n;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            int max = 1;
            n = Integer.parseInt(br.readLine());
            int[][] cheese = new int[n][n];
            copy = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int taste = 1; taste <= 100; taste++) {
                // 먹기
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (cheese[i][j] == taste) cheese[i][j] = 0;
                        copy[i][j] = cheese[i][j];
                    }
                }

                // 덩어리 개수 세기
                int count = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (copy[i][j] <= 0) continue;
                        count--;
                        dfs(count, i, j);
                    }
                }
                max = Math.max(max, count * (-1));
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void dfs(int count, int row, int column) {
        copy[row][column] = count;

        for (int[] d : direction) {
            int nextRow = row + d[0];
            int nextColumn = column + d[1];
            if (isOut(nextRow, nextColumn)) continue;
            if (copy[nextRow][nextColumn] <= 0) continue;
            dfs(count, nextRow, nextColumn);
        }
    }

    private static boolean isOut(int row, int column) {
        if (row < 0 || row >= n) return true;
        if (column < 0 || column >= n) return true;
        return false;
    }
}
