import java.io.*;
import java.util.*;

class Solution {
    static int N, W, H, max, bombCount;
    static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            max = 0;

            int[][] board = new int[H][W];
            bombCount = 0;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] != 0) bombCount++;
                }
            }

            shootingPerm(0, 0, board);
            int answer = bombCount - max;
            if(answer < 0) answer = 0;
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void shootingPerm(int cnt, int explodedSum, int[][] board) {
        if (cnt == N || bombCount == explodedSum) {
            max = Math.max(max, explodedSum);
            return;
        }

        for (int c = 0; c < W; c++) {
            int r = -1;
            for (int i = 0; i < H; i++) {
                if (board[i][c] != 0) {
                    r = i;
                    break;
                }
            }
            if(r == -1) continue;

            int[][] newBoard = copy(board);
            shootingPerm(cnt + 1, explodedSum + dfs(r, c, newBoard), arrangeBoard(newBoard));
        }
    }

    private static int dfs(int r, int c, int[][] board) {
        int power = board[r][c];
        board[r][c] = 0;
        int crashedCount = 1;

        for (int[] d : direction) {
            for (int distance = 1; distance < power; distance++) {
                int nr = r + d[0] * distance;
                int nc = c + d[1] * distance;
                if (isOut(nr, nc)) break;
                if (board[nr][nc] == 0) continue;

                crashedCount += dfs(nr, nc, board);
            }
        }

        return crashedCount;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }

    private static int[][] arrangeBoard(int[][] board) {
        for (int j = 0; j < W; j++) {
            int r = H - 1;
            for (int i = H - 1; i >= 0; i--) {
                if (board[i][j] != 0) {
                    board[r][j] = board[i][j];
                    if (r != i) {
                        board[i][j] = 0;
                    }
                    r--;
                }
            }
        }

        return board;
    }

    private static int[][] copy(int[][] board) {
        int[][] newBoard = new int[H][W];
        for (int i = 0; i < H; i++) {
            newBoard[i] = Arrays.copyOf(board[i], W);
        }

        return newBoard;
    }
}