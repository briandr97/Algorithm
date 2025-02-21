import java.util.*;
import java.io.*;
import java.util.stream.*;
 
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        for(int tc=1; tc<=10; tc++) {
            sb.append("#").append(tc).append(" ");
             
            int count = Integer.parseInt(br.readLine());
            String str = br.readLine();
             
            ArrayDeque<Character> stack = new ArrayDeque<>();
            for(int i=0; i<count; i++) {
                char c = str.charAt(i);
                if(!stack.isEmpty()) {
                    char top = stack.peekLast();
                    if(isMatched(top, c)) {
                      stack.removeLast();
                      continue;
                    }
                }
                stack.add(c);
            }
             
            if(stack.isEmpty()) sb.append(1);
            else sb.append(0);
            sb.append("\n");
        }
         
        System.out.println(sb);
    }
     
    static boolean isMatched(char c1, char c2) {
        switch(c1) {
          case '(': return c2 == ')';
          case '[': return c2 == ']';
          case '{': return c2 == '}';
          case '<': return c2 == '>';
          default: return false;
        }
    }
}