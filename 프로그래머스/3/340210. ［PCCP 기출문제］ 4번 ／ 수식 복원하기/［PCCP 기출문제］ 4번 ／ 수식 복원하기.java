import java.util.*;

class Solution {
    private static final int FALSE = -1;
    private static final int TRUE = 1;
    
    public String[] solution(String[] expressions) {
        int[] isPossible = new int[10]; // 0: 미정, -1: false, 1: true
        int xCount = 0;
        
        for(int i=0; i<expressions.length; i++) {
            String expression = expressions[i];
            String[] elements = expression.split(" ");
            int first = Integer.parseInt(elements[0]);
            int second = Integer.parseInt(elements[2]);
            
            int max = Math.max((Math.max(first/10, first%10)), (Math.max(second/10, second%10)));
            for(int j=2; j<=max; j++) {
                isPossible[j] = FALSE;
            }
            for(int j=max+1; j<10; j++) {
                if(isPossible[j] == FALSE) continue;
                isPossible[j] = TRUE;
            }
            
            if(elements[4].equals("X")) {
                xCount++;
                continue;
            }
            
            int third = Integer.parseInt(elements[4]);
            
            for(int j=2; j<10; j++) {
                if(isPossible[j] != TRUE) continue;
                
                int result = 0;
                if(elements[1].equals("+")) {
                    result = plus(first, second, j);
                } else {
                    result = minus(first, second, j);
                }
                
                if(result != third) {
                    isPossible[j] = FALSE;
                }
            }
        }
        
        List<Integer> possibles = new ArrayList<>();
        for(int i=2; i<10; i++) {
            if(isPossible[i] == TRUE) {
                possibles.add(i);
            }
        }
        
        String[] answer = new String[xCount];
        int idx = 0;
        for(int i=0; i<expressions.length; i++) {
            String expression = expressions[i];
            String[] elements = expression.split(" ");
            
            if(!elements[4].equals("X")) continue;
            
            answer[idx++] = calculate(elements, possibles);
        }
        
        return answer;
    }
    
    private String calculate(String[] elements, List<Integer> possibles) {
        Set<Integer> results = new HashSet<Integer>();
        int first = Integer.parseInt(elements[0]);
        int second = Integer.parseInt(elements[2]);
        
        for(Integer p : possibles) {
            int result = 0;
            if(elements[1].equals("+")) {
                result = plus(first, second, p);
            } else {
                result = minus(first, second, p);
            }
            results.add(result);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(first).append(" ").append(elements[1]).append(" ").append(second).append(" = ");
        if(results.size() == 1) {
            sb.append(results.stream().findFirst().orElse(null));
        } else {
            sb.append("?");
        }
        
        return sb.toString();
    }
    
    private int plus(int first, int second, int base) {
        int result = 0;
        
        // 1의 자리 계산
        int firstOne = first % 10 % base;
        int secondOne = second % 10 % base;
        result += (firstOne + secondOne) % base;
        int carry = ((firstOne + secondOne) / base);

        // 10의 자리 계산
        int firstTen = first / 10 % base;
        int secondTen = second / 10 % base;
        result += 10 * ((firstTen + secondTen + carry) % base);
        result += 100 * ((firstTen + secondTen + carry) / base);
        
        return result;
    }
    
    private int minus(int first, int second, int base) {
        int result = 0;
        
        // 10의 자리 계산
        int firstTen = first / 10 % base;
        int secondTen = second / 10 % base;
        result += 10 * (firstTen - secondTen);
        
        // 1의 자리 계산
        int firstOne = first % 10 % base;
        int secondOne = second % 10 % base;
        if(firstOne >= secondOne) {
            result += firstOne - secondOne;
        } else {
            result -= 10;
            result += base - (secondOne - firstOne);
        }
        
        return result;
    }
}