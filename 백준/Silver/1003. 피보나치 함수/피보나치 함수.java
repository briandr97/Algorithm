import java.util.*;
import java.io.*;

class Main {
    private static int[] zero = new int[41];
    private static int[] one = new int[41];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int repeat = Integer.parseInt(br.readLine().trim());
        init();
        
        while(repeat-- > 0) {
            int num = Integer.parseInt(br.readLine().trim());
            for(int i=2; i<=num; i++) {
                zero[i] = zero[i-1] + zero[i-2];
                one[i] = one[i-1] + one[i-2];
            }
            bw.write(zero[num] + " " + one[num] + "\n");
        }
        
        bw.flush();
        bw.close();
    }
    
    private static void init() {
        zero[0] = 1;
        one[0] = 0;
        zero[1] = 0;
        one[1] = 1;
    }
}