import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
	static List<List<Integer>> toMe;
	static List<Set<Integer>> totalToMe;
	static List<List<Integer>> fromMe;
	static List<Set<Integer>> totalFromMe;
	static boolean[] toVisited;
	static boolean[] fromVisited;
	static int[] connected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		init(n);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			fromMe.get(a).add(b);
			toMe.get(b).add(a);
		}

		for (int i = 1; i <= n; i++) {
			calculateTotalToMe(i);
			int to = totalToMe.get(i).size();
			calculateTotalFromMe(i);
			int from = totalFromMe.get(i).size();
			connected[i] = to + from;
		}

		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (connected[i] == n - 1) {
				count++;
			}
		}

		System.out.println(count);
	}

	static void calculateTotalToMe(int idx) {
		if (toVisited[idx])
			return;

		for (int child : toMe.get(idx)) {
			if (!toVisited[child]) {
				calculateTotalToMe(child);
			}
			totalToMe.get(idx).add(child);
			totalToMe.get(idx).addAll(totalToMe.get(child));
		}
		toVisited[idx] = true;
	}

	static void calculateTotalFromMe(int idx) {
		if (fromVisited[idx])
			return;

		for (int parent : fromMe.get(idx)) {
			if (!fromVisited[parent]) {
				calculateTotalFromMe(parent);
			}
			totalFromMe.get(idx).add(parent);
			totalFromMe.get(idx).addAll(totalFromMe.get(parent));
		}
		fromVisited[idx] = true;
	}

	static void init(int n) {
		toMe = new ArrayList<>();
		fromMe = new ArrayList<>();
		totalToMe = new ArrayList<>();
		totalFromMe = new ArrayList<>();
		toVisited = new boolean[n + 1];
		fromVisited = new boolean[n + 1];
		connected = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			toMe.add(new ArrayList<>());
			fromMe.add(new ArrayList<>());
			totalFromMe.add(new HashSet<>());
			totalToMe.add(new HashSet<>());
		}
	}
}
