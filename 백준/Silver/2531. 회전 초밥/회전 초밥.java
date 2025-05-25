import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sorts = new int[N];
        for (int i = 0; i < N; i++) {
            sorts[i] = Integer.parseInt(br.readLine());
        }

        Windows windows = new Windows(d);
        for (int i = 0; i < k; i++) {
            windows.add(sorts[i]);
        }

        int max = 0;
        for (int i = k; i < N + k; i++) {
            int current = windows.kindCount;
            if (!windows.contain(c)) current++;
            max = Math.max(max, current);

            windows.addAndPop(sorts[i % N]);
        }

        System.out.println(max);
    }
}

class Windows {
    private int size;
    private int[] counts;
    private Queue<Integer> windows;
    int kindCount = 0;

    public Windows(int d) {
        size = d + 1;
        counts = new int[size];
        windows = new ArrayDeque<>();
    }

    public void add(int value) {
        windows.offer(value);
        if (counts[value]++ == 0) {
            kindCount++;
        }
    }

    public int addAndPop(int value) {
        add(value);
        int element = windows.poll();
        if (--counts[element] == 0) {
            kindCount--;
        }
        return kindCount;
    }

    public boolean contain(int value) {
        return counts[value] > 0;
    }
}
