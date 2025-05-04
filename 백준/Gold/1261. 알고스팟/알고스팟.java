import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        boolean[][] board = new boolean[N][M];
        
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                board[i][j] = (input.charAt(j) - '0') == 1;
            }
        }
        
        int[][] crashCount = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                crashCount[i][j] = Integer.MAX_VALUE;
            }
        }
        
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0));
        crashCount[0][0] = 0;
        
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            int curCount = crashCount[cur.r][cur.c];
            
            if(cur.r == N - 1 && cur.c == M - 1) break;
            
            for(int[] d: direction) {
                Point next = cur.move(d[0], d[1]);
                if(isOut(next.r, next.c)) continue;
                
                if(board[next.r][next.c]) {
                    if(crashCount[next.r][next.c] <= curCount + 1) continue;
                    crashCount[next.r][next.c] = curCount + 1;
                    queue.add(next);
                } else {
                    if(crashCount[next.r][next.c] <= curCount) continue;
                    crashCount[next.r][next.c] = curCount;
                    queue.addFirst(next);
                }
            }
        }
        
        System.out.println(crashCount[N-1][M-1]);
    }
    
    private static boolean isOut(int r, int c) {
        return r<0 || r>=N || c<0 || c>=M;
    }
}

class Point {
    int r, c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    public Point move(int dr, int dc) {
        return new Point(r + dr, c + dc);
    }
}