import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for(int tc=1; tc<=10; tc++) {
            sb.append("#").append(tc).append(" ");
            
            br.readLine();
            ArrayDeque<Integer> pw = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<8; i++) {
                pw.add(Integer.parseInt(st.nextToken()));
            }
            
            int cycleFlag = 1;
            while(true) {
                int num = pw.poll() - cycleFlag++;
                if(num < 0) num = 0;
                pw.add(num);
                
                if(cycleFlag > 5) cycleFlag = 1;
                if(num <= 0) break;
            }
            
            while(!pw.isEmpty()) {
                sb.append(pw.poll()).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}