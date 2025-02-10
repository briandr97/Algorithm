import java.util.*;
import java.io.*;

public class Main {
    private static int[][] board;
    private static int row;
    private static int column;
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      // 입력
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      
      board = new int[101][101];
      row = 4;
      column = 4;
      
      for(int i=1; i<row; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=1; j<column; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      // 구현
      int time = -1;
      for(int i=0; i<101; i++) {
        if(board[r][c] == k) {
          time = i;
          break;
        }
        if(row >= column) sortRows();
        else sortColumns();
      }
      
      System.out.println(time);
  }
  
  private static void sortRows() {
    int max = 0; // 행별로 정렬한 후 변경된 열의 개수
    for(int i=1; i<row; i++) {
       max = Math.max(max, sortRow(i));
    }
    column = max;
  }
  
  private static int sortRow(int rowIdx) {
    HashMap<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Pair> queue = new PriorityQueue<>();
    
    for(int i=1; i<column; i++) { // key:숫자, value:개수로 map에 저장
    if(board[rowIdx][i] == 0) continue;
      map.compute(board[rowIdx][i], (key, value) -> (value == null) ? 1 : value + 1);
    }
    map.forEach((key, value) -> queue.add(new Pair(key, value)));
    
    Arrays.fill(board[rowIdx], 0); // 정렬한 값으로 채우기 전에 0으로 초기화
    
    int idx = 1;
    while(!queue.isEmpty()) { // 우선순위 큐에서 작은 순서대로 뽑아서 배열에 입력
      if(idx >= 101) break;
      Pair p = queue.poll();
      board[rowIdx][idx++] = p.value;
      board[rowIdx][idx++] = p.count;
    }
    return idx; // 새로운 column (배열을 101개씩 초기화했기 때문에 몇개가 저장되어있는지 유지하기 위함)
  }
  
  private static void sortColumns() {
    int max = 0; // 열별로 정렬한 후 변경된 행의 개수
    for(int i=1; i<column; i++) {
      max = Math.max(max, sortColumn(i));
    }
    row = max;
  }
  
  private static int sortColumn(int columnIdx) {
    HashMap<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Pair> queue = new PriorityQueue<>();
    
    for(int i=1; i<row; i++) { // key:숫자, value:개수로 map에 저장
      if(board[i][columnIdx] == 0) continue;
      map.compute(board[i][columnIdx], (key, value) -> (value == null) ? 1 : value + 1);
    }
    map.forEach((key, value) -> queue.add(new Pair(key, value)));
    
    for(int i=0; i<row; i++) { // 정렬한 값으로 채우기 전에 0으로 초기화
      board[i][columnIdx] = 0;
    }
    
    int idx = 1;
    while(!queue.isEmpty()) { // 우선순위 큐에서 작은 순서대로 뽑아서 배열에 입력
      if(idx >= 101) break;
      Pair p = queue.poll();
      board[idx++][columnIdx] = p.value;
      board[idx++][columnIdx] = p.count;
    }
    return idx; // 새로운 row (배열을 101개씩 초기화했기 때문에 몇개가 저장되어있는지 유지하기 위함)
  }
}

class Pair implements Comparable<Pair> {
  public int value;
  public int count;
  
  public Pair(int value, int count) {
    this.value = value;
    this.count = count;
  }
  
  @Override
  public int compareTo(Pair other) {
    if(count == other.count) return value - other.value;
    return count - other.count;
  }
}