import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    static int max;
    static int ingredientCount;
    static int calorieLimit;
    static int[] scores;
    static int[] calories; 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int test = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=test; tc++) {
            sb.append("#").append(tc).append(" ");
            
            st = new StringTokenizer(br.readLine());
            ingredientCount = Integer.parseInt(st.nextToken());
            calorieLimit = Integer.parseInt(st.nextToken());
            max = 0;
            
            scores = new int[ingredientCount];
            calories = new int[ingredientCount];
            for(int i=0; i<ingredientCount; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }
            
            comb(0, 0, 0);
            sb.append(max).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void comb(int start, int scoreSum, int calorieSum) {
        if(calorieSum > calorieLimit) return;
        max = Math.max(max, scoreSum);
        
        for(int i=start; i<ingredientCount; i++) {
            comb(i + 1, scoreSum + scores[i], calorieSum + calories[i]);
        }
    }
}