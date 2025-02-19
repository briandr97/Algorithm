import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
    
    // 슬라이딩 윈도우로 풀기
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        final int LION = 1;
        final int APEACH = 2; 
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dolls = new int[n];
        int lionCount = 0;
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            if(Integer.parseInt(st.nextToken()) == LION) dolls[lionCount++] = i;
        }
        
        int min = 1000001;
        for(int i=k-1; i<lionCount; i++) {
            int gap = dolls[i] - dolls[i - k + 1] + 1;
            min = Math.min(min, gap);
        }
        
        if(min == 1000001) min = -1;
        System.out.println(min);
    }
}