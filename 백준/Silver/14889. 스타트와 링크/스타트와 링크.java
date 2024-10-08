import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] ability;
    static boolean[] teamSelected;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];
        teamSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideTeams(0, 0);
        System.out.println(minDifference);
    }

    // 팀을 나누는 조합을 구하는 메소드
    static void divideTeams(int idx, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        for (int i = idx; i < N; i++) {
            if (!teamSelected[i]) {
                teamSelected[i] = true;
                divideTeams(i + 1, count + 1);
                teamSelected[i] = false;
            }
        }
    }

    // 두 팀 간의 능력치 차이를 계산하는 메소드
    static void calculateDifference() {
        List<Integer> team1 = new ArrayList<>();
        List<Integer> team2 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (teamSelected[i]) {
                team1.add(i);
            } else {
                team2.add(i);
            }
        }

        int team1Score = 0, team2Score = 0;

        // 팀 1의 능력치 계산
        for (int i = 0; i < team1.size(); i++) {
            for (int j = i + 1; j < team1.size(); j++) {
                int player1 = team1.get(i);
                int player2 = team1.get(j);
                team1Score += ability[player1][player2] + ability[player2][player1];
            }
        }

        // 팀 2의 능력치 계산
        for (int i = 0; i < team2.size(); i++) {
            for (int j = i + 1; j < team2.size(); j++) {
                int player1 = team2.get(i);
                int player2 = team2.get(j);
                team2Score += ability[player1][player2] + ability[player2][player1];
            }
        }

        int difference = Math.abs(team1Score - team2Score);
        minDifference = Math.min(minDifference, difference);
    }
}