import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    private static HashMap<Integer, Integer> memoization = new HashMap<Integer, Integer>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int repeat = getInt(br);
        while(repeat-- > 0) {
            int num = getInt(br);
            System.out.println(dp(num));
        }
    }
    
    private static int dp(int num) {
        if(num<0) return 0;
        if(num==0) return 1;
        return getFromMapOrCallDp(num-1) + getFromMapOrCallDp(num-2) + getFromMapOrCallDp(num-3);
    }
    
    private static int getFromMapOrCallDp(int num) {
        Integer value = memoization.get(num);
        return (value != null) ? value : dp(num);
    }
    
    private static int getInt(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine().trim());
    }
}