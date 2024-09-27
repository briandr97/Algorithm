import java.util.*;
import java.io.*;

class Main {
    private static String[] map = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        String cur = "";
        int answer = 0;
        for(int i=0; i<input.length(); i++) {
            cur += input.charAt(i);
            if(cur.length() < 2) continue;
            if((cur.length() == 3) && !has(cur)) {
                answer++;
                cur = cur.substring(1,3);
            }
            if(has(cur)) {
                answer++;
                cur = "";
            }
        }
        
        if(!cur.isEmpty()) {
            if(has(cur)) answer++;
            else answer += cur.length();
        }
        
        System.out.println(answer);
        br.close();
    }
    
    private static boolean has(String s) {
        for(String e: map) {
            if(e.equals(s)) return true;
        }
        return false;
    }
}

