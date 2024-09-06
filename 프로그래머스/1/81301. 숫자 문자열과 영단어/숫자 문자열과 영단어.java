import java.util.HashMap;

class Solution {
    HashMap<String, Integer> words = new HashMap<String, Integer>();
    char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
    
    private void initWords() {
        words.put("zero", 0);
        words.put("one", 1);
        words.put("two", 2);
        words.put("three", 3);
        words.put("four", 4);
        words.put("five", 5);
        words.put("six", 6);
        words.put("seven", 7);
        words.put("eight", 8);
        words.put("nine", 9);
    }
    
    private boolean contains(char[] numbers, char c) {
        for(int i=0; i<numbers.length; i++) {
            if(c == numbers[i]) {
                return true;
            }
        }
        return false;
    }
    
    public int solution(String s) {
        initWords();
        StringBuilder word = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        for(int i=0; i<s.length(); i++) {
            if(contains(numbers, s.charAt(i))) {
                answer.append(s.charAt(i));
                continue;
            }
                
            word.append(s.charAt(i));
            String wordString = word.toString();
            
            if(words.containsKey(wordString)) {
                answer.append(words.get(wordString));
                word = new StringBuilder();
            }
        }
        
        return Integer.parseInt(answer.toString());
    }
}