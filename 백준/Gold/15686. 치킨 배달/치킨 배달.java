import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] city = new int[N][N];
		for(int i=0; i<city.length; i++) {
			city[i] = Stream.of(br.readLine().trim().split(" " )).mapToInt(Integer::parseInt).toArray();
		}
		Solution s = new Solution(city, N, M);
		System.out.println(s.solution());
	}
}

class Solution {
	Point[] chickens;
	Point[] houses;
	int[][] city;
	int[][] dist;
	int N;
	int M;
	int min = Integer.MAX_VALUE;
	boolean visited[];
	
	public Solution(int[][] city, int N, int M) {
		this.city = city;
		this.N  = N;
		this.M = M;
	}
	
	public int solution() {
		init();
		initDistance();
		combination(0, 0);
		return min;
	}
	
	private void combination(int r, int start) {
		if(r == M) {
			int totalMin = 0;
			for(int i=0; i<houses.length; i++) {
				int curMin = Integer.MAX_VALUE;
				for(int j=0; j<chickens.length; j++) {
					if(visited[j]) curMin = Math.min(curMin, dist[i][j]);
				}
				totalMin += curMin;
			}
			min = Math.min(min, totalMin);
		}
		
		for(int i=start; i<chickens.length; i++) {
			visited[i] = true;
			combination(r+1, i+1);
			visited[i] = false;
		}
	}
	
	private void initDistance() {
		for(int i=0; i<houses.length; i++) {
			for(int j=0; j<chickens.length; j++) {
				dist[i][j] = getDistance(i, j);
			}
		}
	}
	
	private int getDistance(int idxH, int idxC) {
		Point h = houses[idxH];
		Point c = chickens[idxC];
		return h.getDistance(c);
	}
	
	private void init() {
		int houseCount = 0;
		int chickenCount = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int e = city[i][j];
				if(e == 1) houseCount++;
				if(e == 2) chickenCount++;
			}
		}
		chickens = new Point[chickenCount];
		houses = new Point[houseCount];
		dist = new int[houseCount][chickenCount];
		visited = new boolean[chickenCount];
		
		int idxC = 0;
		int idxH = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int e = city[i][j];
				if(e == 1) {
					houses[idxH] = new Point(i, j);
					idxH++;
				}
				if(e == 2) {
					chickens[idxC] = new Point(i, j);
					idxC++;
				}
			}
		}
	}
}

class Point {
	int row;
	int column;
	
	public Point(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getDistance(Point other) {
		return Math.abs(row - other.row) + Math.abs(column - other.column);
	}
}
