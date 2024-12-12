import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    private static int n;
    private static int m;
    private static int[][] board;
    private static final LinkedList<Point> queue = new LinkedList<>();
    private static final int RIPE = 1;
    private static final int UNRIPE = 0;
    private static final int EMPTY = -1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer mn = new StringTokenizer(br.readLine());
        m = Integer.parseInt(mn.nextToken());
        n = Integer.parseInt(mn.nextToken());
        
        board = new int[n][m];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == RIPE) queue.add(new Point(i, j));
            }
        }
        
        int result;
        if(isAlreadyRipe()) result = 0;
        else {
            result = bfs();
            if(isImpossible()) result = -1;
        }
        
        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
    
    private static int bfs() {
        int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        int days = 0;
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            
            while(qSize-- > 0) {
                Point cur = queue.poll();
                for(int[] d: direction) {
                    Point next = new Point(cur.row + d[0], cur.column + d[1]);
                    if(isOutOrBlocked(next)) continue;
                    board[next.row][next.column] = 1;
                    queue.add(next);
                }
            }
            
            if(!queue.isEmpty()) days++;
        }
        return days;
    }
    
    private static boolean isAlreadyRipe() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] != RIPE) return false;
            }
        }
        return true;
    }
    
    private static boolean isImpossible() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == UNRIPE) return true;
            }
        }
        return false;
    }
    
    private static boolean isOutOrBlocked(Point p) {
        if(p.row < 0 || p.row >= n) return true;
        if(p.column < 0 || p.column >= m) return true;
        if(board[p.row][p.column] != UNRIPE) return true;
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