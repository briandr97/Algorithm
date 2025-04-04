import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] courses;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        courses = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            courses[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(binarySearch());
    }
    
    private static int binarySearch() {
        int start = 1;
        int end = 1000000000;
        int answer = -1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(isPossible(mid)) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return answer;
    }
    
    private static boolean isPossible(int capacity) {
        int count = 1;
        int sum = 0;
        
        for(int i=0; i<N; i++) {
            if(count > M) return false;
            if(courses[i] > capacity) {
                return false;
            }
            
            sum += courses[i];
            if(sum > capacity) {
                count++;
                sum = courses[i];
            }
        }
        
        return M >= count;
    }
}