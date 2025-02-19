import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    static int[] input;
    static int[] nums;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      
      int t = Integer.parseInt(br.readLine());
      for(int tc=1; tc<=t; tc++) {
          sb.append("#").append(tc).append(" ");
          input = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
          nums = new int[input.length];
          visited = new boolean[input.length];
          
          sb.append(dfs(0)).append("\n");
      }
      System.out.println(sb);
    }
    
    static boolean dfs(int depth) {
        if(depth == nums.length) {
            if(isBabyGin()) return true;
        }
        
        for(int i=0; i<nums.length; i++) {
            if(visited[i]) continue;
            nums[depth] = input[i];
            visited[i] = true;
            if(dfs(depth + 1)) return true;
            visited[i] = false;
        }
        
        return false;
    }
    
    static boolean isBabyGin() {
        int[] first = Arrays.copyOf(nums, 3);
        int[] second = Arrays.copyOfRange(nums, 3, 6);
        
        boolean f = isRun(first) || isTriplet(first);
        boolean s = isRun(second) || isTriplet(second);
        
        return f && s;
    }
    
    static boolean isRun(int[] nums) {
        int last = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(last != nums[i]) return false;
        }
        return true;
    }
    
    static boolean isTriplet(int[] nums) {
        int last = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(nums[i] - last != 1) return false;
            last = nums[i];
        }
        return true;
    }
}