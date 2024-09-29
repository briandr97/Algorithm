import java.util.*;
import java.io.*;

class Main {
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[] nonSelf = new boolean[10001];
        
        for(int i=1; i<10000; i++) {
            int k = kapreka(i);
            if(k <= 10000) nonSelf[kapreka(i)] = true;
        }
        
        for(int i=1; i<=10000; i++) {
            if(!nonSelf[i]) bw.write(i + "\n");
        }
        
        bw.flush();
        bw.close();
    }
    
    static int kapreka(int n) {
        int answer = n;
        int cur = n;
        while(cur != 0) {
            answer += cur%10;
            cur /= 10;
        }
        return answer;
    }
}