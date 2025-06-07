// 4시 57분
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
            // TRUE -> FALSE 가능, FALSE -> TRUE 불가능
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
    
//     private int plus(int first, int second, int base) {
//         int result = 0;
        
//         // 1의 자리 계산
//         int firstOne = first % 10 % base;
//         int secondOne = second % 10 % base;
//         result += (firstOne + secondOne) % base;
//         result += 10 * ((firstOne + secondOne) / base);

//         // 10의 자리 계산
//         int firstTen = first / 10 % base;
//         int secondTen = second / 10 % base;
//         result += 10 * ((firstTen + secondTen) % base);
//         result += 100 * ((firstTen + secondTen) / base);
        
//         return result;
//     }
    
    private int plus(int first, int second, int base) {
        int[] a = {first / 10, first % 10};
        int[] b = {second / 10, second % 10};
        int[] res = new int[3];

        int carry = 0;
        for(int i = 1; i >= 0; i--) {
            int sum = a[i] + b[i] + carry;
            res[i + 1] = sum % base;
            carry = sum / base;
        }
        res[0] = carry;
        return res[0] * 100 + res[1] * 10 + res[2];
    }

    
//     private int minus(int first, int second, int base) {
//         int result = 0;
        
//         // 10의 자리 계산
//         int firstTen = first / 10 % base;
//         int secondTen = second / 10 % base;
//         result += 10 * (firstTen - secondTen);
        
//         // 1의 자리 계산
//         int firstOne = first % 10 % base;
//         int secondOne = second % 10 % base;
//         if(firstOne >= secondOne) {
//             result += firstOne - secondOne;
//         } else {
//             result -= 10;
//             result += base - (secondOne - firstOne);
//         }
        
//         return result;
//     }
    
    private int minus(int first, int second, int base) {
        int[] a = {first / 10, first % 10};
        int[] b = {second / 10, second % 10};
        int[] res = new int[2];

        // 1의 자리
        int borrow = 0;
        int d = a[1] - b[1];
        if(d < 0) {
            d += base;
            borrow = 1;
        }
        res[1] = d;

        // 10의 자리
        d = a[0] - b[0] - borrow;
        if(d < 0) return -1;  // 음수가 되면 진법 상 불가능
        res[0] = d;

        return res[0] * 10 + res[1];
    }

}