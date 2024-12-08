import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] points = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sortedPoints = Arrays.copyOf(points, N);
        Arrays.sort(sortedPoints);
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int idx = 0;
        for(int i=0; i<N; i++) {
          if(i > 0 && sortedPoints[i] == sortedPoints[i - 1]) continue;
          map.put(sortedPoints[i], idx++);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
          sb.append(map.get(points[i]));
          if(i < N - 1) sb.append(" ");
        }
        sb.append("\n");
        
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}