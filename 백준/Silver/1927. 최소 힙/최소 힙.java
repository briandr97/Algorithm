import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        while(N-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if(x != 0) {
                minHeap.add(x);
                continue;
            }
            if(minHeap.isEmpty()) {
                bw.write(0 + "\n");
            } else {
                bw.write(minHeap.poll() + "\n");
            }
        }
        bw.flush();
        bw.close(); 
        br.close();
    }
}