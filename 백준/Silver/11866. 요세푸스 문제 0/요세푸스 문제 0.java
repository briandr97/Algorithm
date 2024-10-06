// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        LinkedList<Integer> answer = solution(n, k);
        
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(answer.poll());
        while(!answer.isEmpty()) {
            sb.append(", ").append(answer.poll());
        }
        sb.append(">");
        
        System.out.println(sb.toString());
        br.close();
    }
    
    private static LinkedList<Integer> solution(int n, int k) {
        LinkedList<Integer> list = IntStream.range(1, n+1).boxed().collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Integer> answer = new LinkedList<Integer>();
        while(n-- > 0) {
            for(int i=0; i<k-1; i++) {
                int e = list.poll();
                list.add(e);    
            }
            int e = list.poll();
            answer.add(e);
        }
        return answer;
    }
}