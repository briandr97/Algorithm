import java.util.*;
import java.io.*;
 
class Solution
{
    private static int min = 200000;
    private static int n, height;
    private static int[] clerks;
     
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++) {
            // 입력
            n = sc.nextInt();
            height = sc.nextInt();
            min = 200000;
            clerks = new int[n];
            for(int i=0; i<n; i++) {
                clerks[i] = sc.nextInt();
            }
             
            // 구현
            dfs(0, 0);
             
            // 출력
            System.out.println("#" + test_case + " " + min);
        }
    }
     
    private static void dfs(int idx, int sum) {
        if(sum >= height) {
            min = Math.min(min, sum - height);
            return;
        }
        for(int i=idx; i<n; i++) {
            dfs(i + 1, sum + clerks[i]);
        }
    }
}