import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    private static int range;
    private static int count;
    private static final LinkedList<Integer> num = new LinkedList<>();
    private static final ArrayList<String> result = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer input = new StringTokenizer(br.readLine());
        range = Integer.parseInt(input.nextToken());
        count = Integer.parseInt(input.nextToken());
        
        for(int i=1; i<=range; i++) {
            dfs(i);
            num.clear();
        }
        
        for(String s: result) {
            bw.write(s + "\n");
        }
        
        bw.flush();
        br.close();
        bw.close();
    }
    
    private static void dfs(int cur) {
        num.add(cur);
        if(num.size() == count) {
            StringBuilder sb = new StringBuilder();
            for(int e: num) {
                sb.append(e);
                sb.append(" ");
            }
            result.add(sb.toString());
            num.removeLast();
            return;
        }
        for(int i=cur+1; i<=range; i++) {
            dfs(i);
        }
        num.removeLast();
    }
}