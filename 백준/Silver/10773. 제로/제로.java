import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<Integer>();
        
        int repeat = getInt(br);
        while(repeat-- > 0) {
            int input = getInt(br);
            if(input==0) {
                removeLast(stack);
                continue;
            }
            stack.add(input);
        }
        
        int answer = 0;
        for(int e: stack) {
            answer += e;
        }
        System.out.println(answer);
    }
    
    public static void removeLast(Stack<Integer> stack) {
        stack.remove(stack.size()-1);
    }
    
    public static int getInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}