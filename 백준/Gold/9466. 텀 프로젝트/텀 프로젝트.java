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
  boolean[] visited;
  boolean[] finished;
  int count = 0;
  
  public Dfs(int[] choices) {
    this.choices = choices;
    this.visited = new boolean[choices.length];
    this.finished = new boolean[choices.length];
  }
  
  public int getResult() {
    for(int i=1; i<choices.length; i++) {
      if(visited[i]) continue;
      dfs(i);
    }
    return choices.length - 1 - count;
  }
  
  private void dfs(int choice) {
    visited[choice] = true;
    int next = choices[choice];
    if(visited[next]) {
        if(!finished[next]) {
            for(int temp=next; temp!=choice; temp=choices[temp]) {
                count++;
            }
            count++;
        }
    } else {
        dfs(next);
    }
    finished[choice] = true;
  }
}