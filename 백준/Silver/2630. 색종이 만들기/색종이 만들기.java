import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {    
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        Solution solution = new Solution(N, board);
        solution.solution();
        bw.write(solution.white + "\n" + solution.blue + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}

class Solution {
    private final int N;
    private final int[][] board;
    private final LinkedList<Point> points = new LinkedList<Point>();
    int white = 0;
    int blue = 0;
    
    public Solution(int N, int[][] board) {
        this.N = N;
        this.board = board;
        points.add(new Point(0, 0, N));
    }
    
    public void solution() {
        loop: while(!points.isEmpty()) {
            Point p = points.poll();
            int color = board[p.row][p.column];
            for(int i = p.row; i < p.row + p.size; i++) {
                for(int j = p.column; j < p.column + p.size; j++) {    
                    if(color != board[i][j]) {
                        int newSize = p.size / 2;
                        points.add(new Point(p.row, p.column, newSize));
                        points.add(new Point(p.row + newSize, p.column, newSize));
                        points.add(new Point(p.row, p.column + newSize, newSize));
                        points.add(new Point(p.row + newSize, p.column + newSize, newSize));
                        continue loop;
                    }
                }
            }
            if(color == 0) white++;
            else blue++;
        }
    }
}

class Point {
    int row, column, size;
    public Point(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
    }
}