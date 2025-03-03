import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int test = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=test; tc++) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();

            String[] phoneNumbers = new String[n];
            for(int i=0; i<n; i++) {
                phoneNumbers[i] = br.readLine();
            }
            
            String result = "YES";
            for(String phoneNumber: phoneNumbers) {
                if(!trie.add(phoneNumber.toCharArray())) {
                    result = "NO";
                    break;
                }
            }
            
            sb.append(result).append("\n");
        }
        
        System.out.println(sb);
    }
}

class Trie {
    boolean isEnd = false;
    boolean hasChild = false;
    Trie[] children = new Trie[10];
    
    public boolean add(char[] nums) {
        Trie cur = this;
        for(char c: nums) {
            if(cur.isEnd) return false;
            
            int index = c - '0';
            if(cur.children[index] == null) {
                cur.children[index] = new Trie();
                cur.hasChild = true;
            }
            
            cur = cur.children[index];
        }
        cur.isEnd = true;
        if(cur.hasChild) return false;
        return true;
    }
}