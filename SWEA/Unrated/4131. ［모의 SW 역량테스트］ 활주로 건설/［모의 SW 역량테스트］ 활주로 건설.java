import java.io.*;
import java.util.*;
 
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        final int HTL = -1; // High To Low
        final int LTH = 1; // Low To High
 
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
 
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
 
            int[][] board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            int answer = 0;
 
            // 가로줄 활주로 개수 세기
            loop: for (int i = 0; i < N; i++) {
                int flag = LTH; // flag는 HTL과 LTH 두 가지가 있다. 기본적으로 HTL은 해결해야할 값이라고 인지하면 좋다. (물론 LTH도 해결해야하지만 디폴트값의 역할도 한다)
                int consecutiveHeights = 0;
                for (int j = 0; j < N; j++) {
                    if (j == 0) { // 첫 번째 원소는 할거 없어서 넘어감. 이거 안해주면 밑에 last에서 인덱스 오류남
                        consecutiveHeights++;
                        continue;
                    }
 
                    int cur = board[i][j];
                    int last = board[i][j - 1];
 
                    if (cur != last) { // 현재 높이가 이전 높이와 다르면 
                        if (flag == HTL) continue loop; // 높이가 달라졌는데 이전 HTL flag가 여전히 해결되지 않았다면 활주로 불가능
                        if (Math.abs(cur - last) > 1) continue loop; // 높이 차가 1보다 크면 경사로를 건설할 수 없으므로 활주로 불가능
                        if (cur > last) {
                            if (consecutiveHeights < X) continue loop; // 현재가 이전보다 커졌는데, 이전의 연속된 높이 개수가 활주로 길이보다 작다면 활주로 건설 불가능
                        } else { // 현재가 이전보다 작다면 flag 변경
                            flag = HTL;
                        }
                        consecutiveHeights = 1; // 높이가 바뀌었으니 초기화
                    } else { // 현재 높이가 이전 높이와 같으면
                        consecutiveHeights++; // 연속된 높이 개수 증가
                        if (flag == HTL) { // 이전 높이 변화가 높은 곳에서 낮은 곳으로 내려온 상태였다면
                            if (consecutiveHeights >= X) { // 연속된 높이 개수가 X를 채웠다면 경사로 건설
                                flag = LTH; // 경사로를 건설했으므로 연속된 높이 개수를 0으로 하고 flag도 초기화. 
                                consecutiveHeights = 0; // 같은 공간에 두 개의 경사로가 설치되지 않도록 연속된 경사로 개수를 0으로 초기화 해줘야한다.
                            }
                        }
                    }
                }
                if (flag == LTH) {
                    answer++;
                }
            }
 
            // 세로줄 활주로 개수 세기
            loop: for (int i = 0; i < N; i++) {
                int flag = LTH;
                int consecutiveHeights = 0;
                for (int j = 0; j < N; j++) {
                    if (j == 0) {
                        consecutiveHeights++;
                        continue;
                    }
 
                    int cur = board[j][i];
                    int last = board[j - 1][i];
 
                    if (cur != last) {
                        if (flag == HTL) continue loop;
                        if (Math.abs(cur - last) > 1) continue loop;
                        if (cur > last) {
                            if (consecutiveHeights < X) continue loop;
                        } else {
                            flag = HTL;
                        }
                        consecutiveHeights = 1;
                    } else {
                        consecutiveHeights++;
                        if (flag == HTL) {
                            if (consecutiveHeights >= X) {
                                flag = LTH;
                                consecutiveHeights = 0;
                            }
                        }
                    }
                }
                if (flag == LTH) {
                    answer++;
                }
            }
 
            sb.append(answer).append("\n");
        }
 
        System.out.println(sb);
    }
}