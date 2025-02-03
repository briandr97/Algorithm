import java.util.*;
import java.io.*;
import java.util.stream.*;

class Solution {
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        for(int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#").append(test_case).append(" ");

            LinkedList<Integer> codes = new LinkedList<>();
            int codeN = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while(codeN-- > 0) codes.add(Integer.parseInt(st.nextToken()));
            int opN = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            while(opN-- > 0) {
                String op = st.nextToken();
                if(op.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    while (y-- > 0) codes.add(x++, Integer.parseInt(st.nextToken()));
                } else if (op.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    while (y-- > 0) codes.remove(x);
                } else if(op.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    while (y-- > 0) codes.add(Integer.parseInt(st.nextToken()));
                } else {
                    throw new IllegalArgumentException("잘못됨 " + op + "가 들어옴");
                }
            }
            codes.stream().limit(10).forEach(code -> sb.append(code).append(" "));
            sb.append("\n");
        }
        System.out.println(sb);
    }
}