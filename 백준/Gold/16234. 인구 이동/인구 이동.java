import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim()); 
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			map[i] = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		Solution s = new Solution(N, L, R, map);		
		System.out.println(s.solution());
	}

}

class Solution {
	int N;
	int L;
	int R;
	int[][] map;
	int[][] colored;
	ArrayList<LinkedList<Integer>> populations = new ArrayList<LinkedList<Integer>>();
	int[] average;
	int answer = 0;
	int[][] d = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	int label = 0;
	
	public Solution(int N, int L, int R, int[][] map) {
		this.N = N;
		this.L = L;
		this.R = R;
		this.map = map;
		colored = new int[N][N];
	}
	
	public int solution() {
		while(true) {
			color();
			getAverages();
			setAverages();
			if(isEnd()) break;
		}
		return answer;
	}
	
	private boolean isEnd() {
		for(LinkedList<Integer> list: populations) {
			if(list.size() != 1) {
				answer++;
				return false;
			}
		}
		return true;
	}
	
	private void setAverages() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = average[colored[i][j]];
			}
		}
	}
	
	private void getAverages() {
		average = new int[label];
		populations.clear();
		for(int i=0; i<label; i++) {
			populations.add(new LinkedList<Integer>());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				populations.get(colored[i][j]).add(map[i][j]);
			}
		}
		for(int i=0; i<populations.size(); i++) {
			int size = populations.get(i).size();
			int sum = 0;
			for(int e: populations.get(i)) {
				sum += e;
			}
			average[i] = sum/size;
		}
	}
	
	private void color() {
		label = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				colored[i][j] = -1;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(colored[i][j] == -1) {
					dfs(i, j, label++);
				}
			}
		}
	}
	
	private void dfs(int row, int column, int label) {
		if(colored[row][column] == label) return;
		if(colored[row][column] == -1) colored[row][column] = label;
		if(colored[row][column] != -1) {
			label = Math.min(label,  colored[row][column]);
		}
		for(int i=0; i<4; i++) {
			int nextR = row + d[i][0];
			int nextC = column + d[i][1];
			if(isOut(nextR, nextC)) continue;
			int gap = Math.abs(map[nextR][nextC] - map[row][column]); 
			if(gap >= L && gap <= R) {
				dfs(nextR, nextC, label);
			}
		}
	}
	
	private boolean isOut(int row, int column) {
		if(row < 0 || row >= N) return true;
		if(column < 0 || column >= N) return true;
		return false;
	}
}
