import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Main {
	static int N, M, min;
	static List<Point> homes;
	static List<Point> chickens;
	static int[][] distances;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		homes = new ArrayList<>();
		chickens = new ArrayList<>();
		min = 2000;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) {
					homes.add(new Point(i, j));
				} else if (input == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}

		visited = new boolean[chickens.size()];
		distances = new int[homes.size()][chickens.size()];
		for (int i = 0; i < homes.size(); i++) {
			for (int j = 0; j < chickens.size(); j++) {
				distances[i][j] = getDistance(homes.get(i), chickens.get(j));
			}
		}

		comb(0, 0);
		System.out.println(min);
	}

	static void comb(int cnt, int start) {
		if (cnt == M) {
			int sum = 0;
			for(int i=0; i<homes.size(); i++) {
				int distance = 200;
				for(int j=0; j<chickens.size(); j++) {
					if(visited[j]) {
						distance = Math.min(distance, distances[i][j]);
					}
				}
				sum += distance;
			}
			min = Math.min(min, sum);
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			visited[i] = true;
			comb(cnt + 1, i + 1);
			visited[i] = false;
		}
	}

	static int getDistance(Point home, Point chicken) {
		return Math.abs(home.row - chicken.row) + Math.abs(home.column - chicken.column);
	}

	static class Point {
		int row, column;

		public Point(int row, int column) {
			this.row = row;
			this.column = column;
		}
	}
}
