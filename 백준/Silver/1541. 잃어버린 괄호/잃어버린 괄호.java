import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String input = br.readLine();
        
        StringBuilder buffer = new StringBuilder();
        int answer = 0;
        int minusValue = 0;
        char op = '+';
        
        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            
            if(c != '+' && c != '-') {
                buffer.append(c);
                continue;
            }
            
            if(op == '+') {
                answer += Integer.parseInt(buffer.toString());
                op = c;
            } else if(op == '-') {
                minusValue += Integer.parseInt(buffer.toString());
                if(c == '-') {
                    answer -= minusValue;
                    minusValue = 0;
                }
            }
            buffer = new StringBuilder();
        }
        
        if(op == '+') {
            answer += Integer.parseInt(buffer.toString());
        } else {
            minusValue += Integer.parseInt(buffer.toString());
            answer -= minusValue;
        }
        
        System.out.println(answer);
    }
}