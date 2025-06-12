import java.util.*;

class Solution {
    
    public String solution(String number, int k) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        int restK = k;
        for(char c: number.toCharArray()) {
            while(restK > 0 && !stack.isEmpty() && c > stack.getLast()) {
                stack.removeLast();
                restK--;
            }
            stack.offer(c);
        }
        
        while(restK > 0) {
            stack.removeLast();
            restK--;
        }
        
        StringBuilder answer = new StringBuilder();
        for(char c: stack) {
            answer.append(c);
        }
        
        return answer.toString();
    }
}