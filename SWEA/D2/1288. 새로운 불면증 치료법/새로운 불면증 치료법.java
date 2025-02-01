import java.util.*;
import java.io.*;

class Solution
{
    private static final int FLAG = (1 << 10) - 1;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int flag = 0;
            final int n = Integer.parseInt(br.readLine());
            int curN = 0;
            while (flag != FLAG) {
                curN += n;
                String nStr = Integer.valueOf(curN).toString();
                for (int i = 0; i < nStr.length(); i++) {
                    int digit = nStr.charAt(i) - '0';
                    flag = flag | (1 << digit);
                }
            }
            sb.append("#").append(test_case).append(" ").append(curN).append("\n");
        }
        System.out.println(sb.toString());
    }
}