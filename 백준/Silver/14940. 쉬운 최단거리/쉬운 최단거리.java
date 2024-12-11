import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    private static final int BLOCK = 0;
    private static final int PATH = 1;
    private static final int START = 2;
    
    private static int width, height;
    private static int[][] board;
    private static int[][] result;
    private static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        
        board = new int[width][height];
        result = new int[width][height];
        visited = new boolean[width][height];
        for(int i=0; i<width; i++) {
            board[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        
        bfs();
        fill();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                sb.append(result[i][j]);
                if(j == height-1) sb.append("\n");
                else sb.append(" ");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
    
    private static void bfs() {
        int[][] direction = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        LinkedList<Point> queue = new LinkedList<Point>();
        queue.add(getStartPoint());
        
        int distance = 0;
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            while(qSize-- > 0) {
                Point q = queue.poll();    
                int value = board[q.row][q.column];
                
                if(value == PATH) result[q.row][q.column] = distance;
                else if(value == START) result[q.row][q.column] = 0;
                else if(value == BLOCK) {
                    result[q.row][q.column] = 0;
                    continue;
                }
                
                for(int[] d: direction) {
                    int nextRow = q.row + d[0];
                    int nextColumn = q.column + d[1];
                    if(isOutOrVisited(nextRow, nextColumn)) continue;
                    queue.add(new Point(nextRow, nextColumn));
                    visited[nextRow][nextColumn] = true;
                }
            }
            distance++;
        }
    }
    
    private static void fill() {
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                if(visited[i][j]) continue;
                if(board[i][j] == BLOCK) result[i][j] = 0;
                if(board[i][j] == PATH) result[i][j] = -1;
            }
        }
    }
    
    private static Point getStartPoint() {
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                if(board[i][j] == START) {
                    return new Point(i, j);
                }
            }
        }
        throw new IllegalStateException();
    }
    
    private static boolean isOutOrVisited(int row, int column) {
        if(row < 0 || row >= width) return true;
        if(column < 0 || column >= height) return true;
        if(visited[row][column]) return true;
        return false;
    }
}

class Point {
    final int row, column;
    
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
}