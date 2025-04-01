import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int[][] board;
    static List<Point> cores;
    static boolean[] visited;
    static int maxCount;
    static int minLength;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            maxCount = 0;
            minLength = Integer.MAX_VALUE;
            
            board = new int[N][N];
            cores = new ArrayList<>();
            visited = new boolean[N];
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if(board[i][j] == 1) {
                        if(i==0 || j==0 || i==N-1 || j==N-1) continue;
                        cores.add(new Point(i, j));
                    }
                }
            }
            
            comb(0, 0, 0);
            sb.append(minLength).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void comb(int idx, int length, int count) {
        if(idx == cores.size()) return;
        
        Point p = cores.get(idx);
        for(Direction d: Direction.values()) {
            int markedLength = markBoard(p, d);
            if(markedLength != -1) {
                setAnswer(count + 1, length + markedLength);
                comb(idx + 1, length + markedLength, count + 1);
                unmarkBoard(p, markedLength, d);
            }
        }
        comb(idx + 1, length, count);
    }
    
    static void setAnswer(int count, int length) {
        if(count > maxCount) {
            maxCount = count;
            minLength = length;
        } else if(count == maxCount) {
            if(length < minLength) {
                minLength = length;
            }
        }
    }
    
    static int markBoard(Point start, Direction d) {
        Point p = start.move(d);
        int length = 0;
        
        while(p.x >= 0 && p.x < N && p.y >= 0 && p.y < N) {
            if(board[p.x][p.y] == 1) {
                unmarkBoard(start, length, d);
                return -1;
            }
            
            board[p.x][p.y] = 1;
            length++;
            p = p.move(d);
        }
        return length;
    }
    
    static void unmarkBoard(Point start, int length, Direction d) {
        Point p = start.move(d);
        for(int i=0; i<length; i++) {
            board[p.x][p.y] = 0;
            p = p.move(d);
        }
    }
}

class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point move(Direction d) {
        return new Point(x + d.dx, y + d.dy);
    }
    
    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}

enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);
    
    public int dx, dy;
    
    private Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}