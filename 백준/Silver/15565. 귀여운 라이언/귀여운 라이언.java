import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    
    // 투포인터로 풀기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        final int LION = 1;
        final int APEACH = 2; 
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dolls = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        int left = 0;
        int right = 0;
        int count = 0;
        int min = 1000001;
        
        while(right < n) {
            if(dolls[right] == LION) count++;
            
            while(count >= k) {
                min = Math.min(min, right - left + 1);
                if(dolls[left++] == LION) count--;
            }
            
            right++;
        }
        
        if(min == 1000001) min = -1;
        System.out.println(min);
    }
}