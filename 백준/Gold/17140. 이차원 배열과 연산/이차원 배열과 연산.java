import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      
      int row = 4;
      int column = 4;
      
      int[][] board = new int[row][column];
      
      for(int i=1; i<row; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=1; j<column; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      for(int i=0; i<=100; i++) {
        if(r < row && c < column) {
          if(board[r][c] == k) {
            System.out.println(i);
            return;
          }
        }

        if(row >= column) {
           board = sortRow(board);
        } else {
          board = sortColumn(board);
        }
        board = limitBoard(board);
        
        row = board.length;
        column = board[0].length;
      }
      
      System.out.println(-1);
  }
  
  private static int[][] limitBoard(int[][] board) {
    int r = board.length;
    int c = board[0].length;
    
    int[][] newBoard;
    if(r > 101 && c > 101) {
      newBoard = new int[101][101];
    } else if(r > 101) {
      newBoard = new int[101][c];
    } else if(c > 101) {
      newBoard = new int[r][101];
    } else {
      return board;
    }
    for(int i=1; i<newBoard.length; i++) {
      for(int j=1; j<newBoard[0].length; j++) {
        newBoard[i][j] = board[i][j];
      }
    }
    
    return newBoard;
  }
  
  private static int[][] sortColumn(int[][] board) {
    int r = board.length;
    int c = board[0].length;
    
    ArrayList<ArrayDeque<Node>> newBoard = new ArrayList<>();
    int newRow = 0;
    int newColumn = c;
    
    for(int i=1; i<c; i++) {
      // 개수 세기
      int[] arr = new int[101];
      for(int j=1; j<r; j++) {
        arr[board[j][i]]++;
      }
      
      // 개수 노드로 변환
      ArrayDeque<Node> nodes = new ArrayDeque<>();
      for(int idx=1; idx<101; idx++) {
        int count = arr[idx];
        if(count == 0) continue;
        nodes.add(new Node(idx, count));
      }
      
      newRow = Math.max(newRow, nodes.size());
      List<Node> list = new ArrayList<>(nodes);
      Collections.sort(list);
      nodes.clear();
      nodes.addAll(list);
      newBoard.add(nodes);
    }
    
    newRow = newRow * 2 + 1;
    board = new int[newRow][newColumn];
    for(int i=1; i<newColumn; i++) {
      ArrayDeque<Node> q = newBoard.get(i - 1);
      for(int j=1; j<newRow; j+=2) {
        if(q.isEmpty()) continue;
        Node node = q.poll();
        board[j][i] = node.value;
        board[j+1][i] = node.count;
      }
    }
    
    return board;
  }
  
  private static int[][] sortRow(int[][] board) {
    int r = board.length;
    int c = board[0].length;
    
    ArrayList<ArrayDeque<Node>> newBoard = new ArrayList<>();
    int newRow = r;
    int newColumn = 0;
    
    // 행마다 정렬
    for(int i=1; i<r; i++) {
      // 개수 세기
      int[] arr = new int[101];
      for(int j=1; j<c; j++) {
        arr[board[i][j]]++;
      }
      
      // 개수 노드로 변환
      ArrayDeque<Node> nodes = new ArrayDeque<>();
      for(int idx=1; idx<101; idx++) {
        int count = arr[idx];
        if(count == 0) continue;
        nodes.add(new Node(idx, count));
      }
      
      newColumn = Math.max(newColumn, nodes.size());
      List<Node> list = new ArrayList<>(nodes);
      Collections.sort(list);
      nodes.clear();
      nodes.addAll(list);
      newBoard.add(nodes);
    }
    
    newColumn = newColumn * 2 + 1;
    board = new int[newRow][newColumn];
    for(int i=1; i<newRow; i++) {
      ArrayDeque<Node> q = newBoard.get(i - 1);
      for(int j=1; j<newColumn; j+=2) {
        if(q.isEmpty()) continue;
        Node node = q.poll();
        board[i][j] = node.value;
        board[i][j+1] = node.count;
      }
    }
    
    return board;
  }
}

class Node implements Comparable<Node> {
  final int value;
  final int count;
  
  public Node(int value, int count) {
    this.value = value;
    this.count = count;
  }
  
  @Override
  public int compareTo(Node other) {
    if(count == other.count) {
      return value - other.value;
    }
    return count - other.count;
  }
}