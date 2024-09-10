import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int repeat = Integer.parseInt(br.readLine().trim());
      for(int i=0; i<repeat; i++) {
        int studentCount = Integer.parseInt(br.readLine().trim());
        int[] choices = Stream.of(("0 " + br.readLine().trim()).split(" "))
                              .mapToInt(Integer::parseInt)
                              .toArray();
        Dfs dfs = new Dfs(choices);
        System.out.println(dfs.getResult());
      }
  }
}

class Dfs {
  int[] choices;
  int[] visited;
  Stack<Integer> stack = new Stack<>();
  int answer = 0;
  
  public Dfs(int[] choices) {
    this.choices = choices;
    this.visited = new int[choices.length];
  }
  
  public int getResult() {
    for(int i=1; i<choices.length; i++) {
      if(visited[i] != 0) continue;
      dfs(i);
    }
    return answer;
  }
  
  private void dfs(int choice) {
    visited[choice] = 1;
    stack.push(choice);
    
    int next = choices[choice];
    switch(visited[next]) {
      case 0: 
        dfs(next);
        break;
      
      case 1:
        while(true) {
          int last = stack.pop();
          visited[last] = 2;
          if(last == next) break;
        };
        
      case 2:
        while(!stack.empty()) {
          int last = stack.pop();
          visited[last] = 2;
          answer++;
        };
        break;
        
      default:
        break;
    }
  }
}