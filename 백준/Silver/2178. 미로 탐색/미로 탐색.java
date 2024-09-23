import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());
        int[][] map = new int[row+1][column+1];
        
        for(int r=1; r<=row; r++) {
            int[] input = Stream.of(("0" + br.readLine().trim()).split("")).mapToInt(Integer::parseInt).toArray();
            map[r] = input;
        }
        
        Graph g = new Graph(map);
        System.out.println(g.bfs());
    }
}

class Graph {
    int[][] map;
    int row;
    int column;
    Point[] direction = {new Point(1,0), new Point(-1,0), new Point(0,1), new Point(0,-1)};
    
    public Graph(int[][] map) {
        this.map = map;
        row = map.length;
        column = map[0].length;
    }
    
    public int bfs() {
        Queue<Point> q = new LinkedList<Point>();
        boolean[][] visited = new boolean[row][column];
        
        q.add(new Point(1,1));
        visited[1][1] = true;
        int level = 1;
        
        while(!q.isEmpty()) {
            level++;
            int qSize = q.size();
            for(int i=0; i<qSize; i++) {
                Point point = q.poll();
                for(Point d: direction) {
                    Point next = point.plus(d);
                    if(!isInIndex(next.row, next.column)) continue;
                    if(map[next.row][next.column]==0) continue;
                    if(visited[next.row][next.column]) continue;
                    
                    if(next.row == row-1 && next.column==column-1) return level;
                    q.add(next);
                    visited[next.row][next.column] = true;
                }   
            }
        }
        
        return -1;
    }
    
    private boolean isInIndex(int nextRow, int nextColumn) {
        if(nextRow < 1 || nextRow >= row) return false;
        if(nextColumn < 1 || nextColumn >= column) return false;
        return true;
    }
}

class Point {
    int row;
    int column;
    
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public Point plus(Point other) {
        return new Point(row + other.row, column + other.column);
    }
    
    public String toString() {
      return new StringBuilder().append("Point(row: " + row + ", column: " + column + ")").toString();
    }
}