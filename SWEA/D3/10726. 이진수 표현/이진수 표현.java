import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int minFlag = 1 << (n - 1);
            if (m < minFlag) {
                sb.append("OFF").append("\n");
                continue;
            }

            int flag = (1 << n) - 1;
            if ((flag & m) == flag) sb.append("ON");
            else sb.append("OFF");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}