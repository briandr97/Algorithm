import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        Time[] times = new Time[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(times);
        
        int count = 1;
        Time last = times[0];
        for(int i=1; i<n; i++) {
            if(times[i].start < last.end) {
                if(times[i].end < last.end) last = times[i];
                else continue;
            } else {
                count++;
                last = times[i];
            }
            
        }

        bw.write(count + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}

class Time implements Comparable<Time> {
    public final int start, end;
    
    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    public int compareTo(Time other) {
        if(start < other.start) return -1;
        else if(start > other.start) return 1;
        else {
            if(end < other.end) return -1;
            else if(end > other.end) return 1;
            else return 0;
        }
    }
}