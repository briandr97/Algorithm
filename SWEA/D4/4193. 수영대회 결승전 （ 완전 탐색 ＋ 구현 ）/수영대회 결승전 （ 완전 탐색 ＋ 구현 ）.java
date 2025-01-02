import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;
 
class Solution {
    private static int n;
    private static int[][] direction = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    private static int startRow, startColumn;
    private static int endRow, endColumn;
    private static int time;
    private static int[][] board;
    private static boolean[][] visited;
 
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++) {
            // 입력
            time = 0;
            n = sc.nextInt();
            board = new int[n][n];
            visited = new boolean[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            startRow = sc.nextInt();
            startColumn = sc.nextInt();
            endRow = sc.nextInt();
            endColumn = sc.nextInt();
 
            // 구현
            LinkedList<Point> nears = new LinkedList<Point>();
            boolean[][] isInNears = new boolean[n][n];
            nears.add(new Point(startRow, startColumn));
            visited[startRow][startColumn] = true;
            int result = -1;
 
            loop: while(!nears.isEmpty()) {
                int qSize = nears.size();
                while(qSize-- > 0) {
                    Point near = nears.poll();
                    isInNears[near.row][near.column] = false;
                    if(isEndPoint(near)) {
                        result = time;
                        break loop;
                    }
 
                    for(int[] d: direction) {
                        Point nextPoint = new Point(near.row + d[0], near.column + d[1]);
 
                        if(isOutOrVisited(nextPoint)) continue;
                        if(board[nextPoint.row][nextPoint.column] == 1) continue;
                        if(board[nextPoint.row][nextPoint.column] == 2 && !isPaused()) {
                            if(!isInNears[near.row][near.column]) {
                                nears.add(near);
                                isInNears[near.row][near.column] = true;
                            }
                            continue;
                        }
 
                        if(!isInNears[nextPoint.row][nextPoint.column]) {
                            nears.add(nextPoint);
                            isInNears[nextPoint.row][nextPoint.column] = true;
                        }
                        visited[nextPoint.row][nextPoint.column] = true;
                    }
                }
                time++;
            }
 
            System.out.println("#" + test_case + " " + result);
        }
    }
 
    private static boolean isPaused() {
        if((time-2)%3 == 0) return true;
        return false;
    }
 
    private static boolean isOutOrVisited(Point p) {
        if(p.row < 0 || p.row >= n) return true;
        if(p.column < 0 || p.column >= n) return true;
        if(visited[p.row][p.column]) return true;
        return false;
    }
 
    private static boolean isEndPoint(Point p) {
        if(p.row == endRow && p.column == endColumn) return true;
        return false;
    }
}
 
class Point {
    int row, column;
 
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}