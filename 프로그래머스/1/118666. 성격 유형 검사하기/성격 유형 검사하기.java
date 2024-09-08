import java.util.*;

class Solution {
    private static HashMap<String, Integer> mbti = new HashMap<String, Integer>();
    
    public String solution(String[] survey, int[] choices) {
        initMbti();
        calculate(survey, choices);
        return getResult();
    }

    private static void initMbti() {
        mbti.put("RT", 0);
        mbti.put("CF", 0);
        mbti.put("JM", 0);
        mbti.put("AN", 0);
    }
    
    private static void calculate(String[] survey, int[] choices) {
        for(int i=0; i<survey.length; i++) {
            String s = survey[i];
            int choice = choices[i] - 4;
            
            if(!mbti.containsKey(s)) {
                s = new StringBuilder(s).reverse().toString();
                choice = (-1)*choice;
            }
            mbti.put(s, mbti.get(s) + choice);
        }
    }
    
    private static String getResult() {
        System.out.println(mbti);
        StringBuilder result = new StringBuilder();
        
        mbti.forEach((key, value) -> {
            if(value <= 0) result.append(key.charAt(0));
            else result.append(key.charAt(1));
        });
        
        return result.toString();
    }
}