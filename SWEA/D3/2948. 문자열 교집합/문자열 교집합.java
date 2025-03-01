import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int test = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=test; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int count1 = Integer.parseInt(st.nextToken());
            int count2 = Integer.parseInt(st.nextToken());
            
            HashSet<String> set = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<count1; i++) {
                set.add(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<count2; i++) {
                set.add(st.nextToken());
            }
            
            int result = count1 + count2 - set.size();
            sb.append(result).append("\n");
        }
        
        System.out.println(sb);
    }
}