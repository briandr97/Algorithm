import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] str = br.readLine().toCharArray();
        char[] pattern = br.readLine().toCharArray();
        
        int sLength = str.length;
        int pLength = pattern.length;
        
        // 테이블 만들기
        int[] lps = new int[pLength];
        for(int i=1, j=0; i<pLength; i++) { // i는 접미사 인덱스, j는 중복된 영역의 길이이자 현재 바라봐야할 접두사의 인덱스이다.
            while(j > 0 && pattern[i] != pattern[j]) {
                j = lps[j - 1];
            }
            
            if(pattern[i] == pattern[j]) {
                lps[i] = ++j;
            }
        }
        
        // 개수 구하기
        int count = 0;
        StringBuilder sb = new StringBuilder();
        
        for(int i=0, j=0; i<sLength; i++) {
            while(j > 0 && str[i] != pattern[j]) {
                j = lps[j - 1];
            }
            
            if(str[i] == pattern[j]) {
                j++;
                
                if(j == pLength) {
                    count++;
                    sb.append(i - j + 2).append(" ");
                    j = lps[j - 1];
                }
            }
        }
        
        System.out.println(count);
        System.out.println(sb);
    }
}