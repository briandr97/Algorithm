import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine().trim();
        String next = num;
        int count = 0;
        
        do {
            count++;
            int nextInt = Integer.parseInt(next);
            int sum = nextInt/10 + nextInt%10;
            StringBuilder sb = new StringBuilder();
            if(nextInt%10 != 0) {
                sb.append(nextInt%10);
            }
            sb.append(sum%10);
            next = sb.toString();
        } while(!next.equals(num));
        
        System.out.println(count);
    }
}