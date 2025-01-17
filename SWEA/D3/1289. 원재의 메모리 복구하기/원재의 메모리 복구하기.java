import java.util.*;
import java.io.*;
import java.util.stream.*;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for(int tc=1; tc<=T; tc++) {
            int[] nums = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            int sum = 0;
            int lastNum = 0;
            for(int n: nums) {
                if(n == lastNum) continue;
                if(lastNum == 1) lastNum = 0; 
                else lastNum = 1;
                sum++;
            }
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
         
        System.out.print(sb.toString());
    }
}