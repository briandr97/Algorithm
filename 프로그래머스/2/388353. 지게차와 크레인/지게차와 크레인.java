import java.util.*;

class Solution {
    static int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    
    public int solution(String[] storage, String[] requests) {
        char[][] board = new char[storage.length + 2][storage[0].length() + 2];
        for(int i=0; i<storage.length; i++) {
            for(int j=0; j<storage[i].length(); j++) {
                board[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        
        for(int i=0; i<requests.length; i++) {
            String request = requests[i];
            char target = request.charAt(0);
            
            if(request.length() > 1) { // 크레인
                for(int j=1; j<board.length - 1; j++) {
                    for(int k=1; k<board[j].length - 1; k++) {
                        if(board[j][k] == target) {
                            board[j][k] = Character.MIN_VALUE;
                        }
                    }
                }
            } else { // 지게차
                List<Point> points = new ArrayList<Point>();
                Queue<Point> queue = new ArrayDeque<Point>();
                boolean[][] visited = new boolean[board.length][board[0].length];
                
                queue.offer(new Point(0,0));
                visited[0][0] = true;
                
                while(!queue.isEmpty()) {
                    Point cur = queue.poll();
                    
                    for(int[] d: directions) {
                        int nr = cur.r + d[0];
                        int nc = cur.c + d[1]; 
                        if(nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length) continue;
                        if(visited[nr][nc]) continue;
                        visited[nr][nc] = true;
                        if(board[nr][nc] == target) {
                            points.add(new Point(nr, nc));
                        }
                        if(board[nr][nc] == Character.MIN_VALUE) {
                            queue.offer(new Point(nr, nc));
                        }
                    }
                }
                
                for(Point p: points) {
                    board[p.r][p.c] = Character.MIN_VALUE;
                }
            }
        }
        
        int answer = 0;
        for(int i=1; i<board.length - 1; i++) {
            for(int j=1; j<board[i].length - 1; j++) {
                if(board[i][j] != Character.MIN_VALUE) answer++;
            }
        }
        return answer;
    }
}

class Point {
    int r, c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}