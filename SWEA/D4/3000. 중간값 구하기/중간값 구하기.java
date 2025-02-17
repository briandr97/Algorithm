import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
    static PriorityQueue<Long> maxHeap;
    static PriorityQueue<Long> minHeap;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++) {
            sb.append("#").append(tc).append(" ");
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
            long sum = 0;
            
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long first = Long.parseLong(st.nextToken());
            minHeap.add(first);
            
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine()); 
                long n1 = Long.parseLong(st.nextToken());
                long n2 = Long.parseLong(st.nextToken());
                if(n1 > n2) {
                    minHeap.add(n1);
                    maxHeap.add(n2);
                } else {
                    minHeap.add(n2);
                    maxHeap.add(n1);
                }
                
                n1 = maxHeap.peek();
                n2 = minHeap.peek();
                
                if(n1 > n2) {
                    swap();
                }
                
                sum = (sum + minHeap.peek()) % 20171109;
            }
            
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
    
    static void swap() {
        long n1 = maxHeap.poll();
        long n2 = minHeap.poll();
        maxHeap.add(n2);
        minHeap.add(n1);
    }
}