import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    static int n;
    static int m;
    static int count;
    static int[] impossibles;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int test = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=test; tc++) {
            sb.append("#").append(tc).append(" ");
            
            count = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            impossibles = new int[n + 1];
            
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                impossibles[a] |= 1 << b;
                impossibles[b] |= 1 << a;
            }
            
            subset(0, 0);
            sb.append(count).append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void subset(int cnt, int binaryVisited) {
        if(cnt == n) {
          count++;
          return;
        }
        
        if((binaryVisited & impossibles[cnt + 1]) == 0) {
            subset(cnt + 1, binaryVisited + (1 << (cnt + 1)));
        }
        subset(cnt + 1, binaryVisited);
    }
}