import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            int K = Integer.parseInt(br.readLine());

            Wheel[] wheels = new Wheel[4];
            for (int i = 0; i < 4; i++) {
                int[] input = new int[Wheel.N];

                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < Wheel.N; j++) {
                    input[j] = Integer.parseInt(st.nextToken());
                }

                wheels[i] = new Wheel(input, i);
                if (i != 0) {
                    wheels[i - 1].setRightWheel(wheels[i]);
                    wheels[i].setLeftWheel(wheels[i - 1]);
                }
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int flag = Integer.parseInt(st.nextToken());
                wheels[idx].rotate(flag);
            }

            int[] scores = {1, 2, 4, 8};
            int answer = 0;
            for (int i = 0; i < 4; i++) {
                if (wheels[i].getTop() == Wheel.POLE_N) continue;
                answer += scores[i];
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}

class Wheel {
    public static final int N = 8;
    public static final int CLOCKWISE = 1;
    public static final int COUNTER_CLOCKWISE = -1;
    public static final int POLE_N = 0;
    public static final int POLE_S = 1;

    private static final int TOP = 0;
    private static final int LEFT = 6;
    private static final int RIGHT = 2;

    private final int[] ns;
    public final int idx;
    private Wheel leftWheel;
    private Wheel rightWheel;

    Wheel(int[] ns, int idx) {
        this.ns = ns;
        this.idx = idx;
    }

    public int getTop() {
        return ns[TOP];
    }

    private int getLeft() {
        return ns[LEFT];
    }

    private int getRight() {
        return ns[RIGHT];
    }

    public void setLeftWheel(Wheel wheel) {
        leftWheel = wheel;
    }

    public void setRightWheel(Wheel wheel) {
        rightWheel = wheel;
    }

    public void rotate(int flag) {
        checkLeft(flag);
        move(getCounterFlag(flag));
        checkRight(flag);
    }

    private void checkLeft(int flag) {
        if (leftWheel != null && getLeft() != leftWheel.getRight()) {
            leftWheel.checkLeft(getCounterFlag(flag));
        }

        move(flag);
    }

    private void checkRight(int flag) {
        if (rightWheel != null && getRight() != rightWheel.getLeft()) {
            rightWheel.checkRight(getCounterFlag(flag));
        }

        move(flag);
    }

    private void move(int flag) {
        if (flag == CLOCKWISE) {
            moveClockwise();
        } else if (flag == COUNTER_CLOCKWISE) {
            moveCounterClockwise();
        } else {
            throw new IllegalArgumentException("잘못된 flag: " + flag);
        }
    }

    private void moveClockwise() {
        int temp = ns[N - 1];
        for (int i = N - 1; i > 0; i--) {
            ns[i] = ns[i - 1];
        }
        ns[0] = temp;
    }

    private void moveCounterClockwise() {
        int temp = ns[0];
        for (int i = 0; i < N - 1; i++) {
            ns[i] = ns[i + 1];
        }
        ns[N - 1] = temp;
    }

    private int getCounterFlag(int flag) {
        if (flag == CLOCKWISE) {
            return COUNTER_CLOCKWISE;
        } else if (flag == COUNTER_CLOCKWISE) {
            return CLOCKWISE;
        } else {
            throw new IllegalArgumentException("잘못된 flag: " + flag);
        }
    }
}