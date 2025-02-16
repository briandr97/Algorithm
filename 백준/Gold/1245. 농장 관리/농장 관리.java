import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
  static int n;
  static int m;
  static int[][] farm;
  static int[][] label;
  static boolean[][] visited;
  static int[][] direction = {{1,1}, {-1,-1}, {1,0}, {-1,0}, {0,1}, {0,-1}, {1,-1}, {-1,1}};
  static ArrayDeque<Point> stack = new ArrayDeque<>();
  static final int UN_SEARCHED = 0;
  static final int TOP = 2;
  static final int UNDER = -1;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      farm = new int[n][m];
      label = new int[n][m];
      for(int i=0; i<n; i++) {
          farm[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      }
      
      int sum = 0;
      for(int i=0; i<n; i++) {
          for(int j=0; j<m; j++) {
              if(label[i][j] != 0) continue;
              visited = new boolean[n][m];
              int curLabel = UN_SEARCHED;
              
              if(dfs(i, j)) {
                  sum++;
                  curLabel = TOP;   
              } else {
                  curLabel = UNDER;
              }
              
              while(!stack.isEmpty()) {
                  Point p = stack.poll();
                  label[p.row][p.column] = curLabel;
              }
          }
      }
      
      System.out.println(sum);
    }
    
    static boolean dfs(int row, int column) {
        visited[row][column] = true;
        stack.add(new Point(row, column));
        int height = farm[row][column];
        
        boolean isTop = true;
        for(int[] d: direction) {
            int nextRow = row + d[0];
            int nextColumn = column + d[1];
            if(isOut(nextRow, nextColumn)) continue;
            if(visited[nextRow][nextColumn]) continue;
            
            int nextHeight = farm[nextRow][nextColumn];
            
            if(nextHeight < height) label[nextRow][nextColumn] = UNDER;
            else if(nextHeight > height) isTop = false;
            else {
                if(label[nextRow][nextColumn] == UNDER) isTop = false;
                else {
                    if(!dfs(nextRow, nextColumn)) isTop = false;
                }
            }
        }
        
        return isTop;
    }
    
    static boolean isOut(int row, int column) {
        if(row < 0 || row >= n) return true;
        if(column < 0 || column >= m) return true;
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