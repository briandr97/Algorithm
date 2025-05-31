import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        int[] nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(nums);
        int left = 0;
        int right = N - 1;
        int sum = Integer.MAX_VALUE;
        int leftAns = -1000000000;
        int rightAns = 1000000000;
        
        while(left != right) {
            int candidate = Math.abs(nums[left] + nums[right]);
            if(candidate < sum) {
                sum = candidate;
                leftAns = nums[left];
                rightAns = nums[right];
            }
            
            if(Math.abs(nums[left]) > Math.abs(nums[right])) {
                left++;
            } else {
                right--;
            }
        }
        
        sb.append(leftAns).append(" ").append(rightAns);
        System.out.println(sb);
    }
}