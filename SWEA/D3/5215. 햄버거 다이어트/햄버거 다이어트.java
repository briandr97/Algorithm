import java.util.*;
import java.io.*;
 
class Solution {
    private static int n;
    private static int limit;
    private static int[][] ingredients;
    private static int max;
     
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());
            ingredients = new int[n][2];
            max = 0;
             
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
            }
             
            dfs(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.print(sb.toString());
    }
    private static void dfs(int score, int sum, int idx) {
        if(sum > limit) return;
        if(score > max) max = score;
 
        for(int i=idx; i<n; i++) {
            dfs(score + ingredients[i][0], sum + ingredients[i][1], i + 1);
        }
    }
}