import java.util.*;
import java.io.*;
import java.util.stream.*;
  
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
  
        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] nArr = new int[n];
            int[] mArr = new int[m];
              
            nArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            mArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  
            int result = 0;
            if(n > m) result = calculate(nArr, mArr);
            else result = calculate(mArr, nArr);
            System.out.println("#" + test_case + " " + result);
        }
          
        br.close();
    }
      
    private static int calculate(int[] large, int[] small) {
        int l = large.length;
        int s = small.length;
        int max = Integer.MIN_VALUE;
          
        for(int i=0; i<l-s+1; i++) {
            int sum = 0;
            for(int smallIdx=0; smallIdx<s; smallIdx++) {
                int largeIdx = i + smallIdx;
                sum += large[largeIdx] * small[smallIdx];
            }
            max = Math.max(max, sum);
        }
          
        return max;
    }
}