import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringTokenizer robotSt = new StringTokenizer(br.readLine().trim());
		Robot r = new Robot(
				Integer.parseInt(robotSt.nextToken()), 
				Integer.parseInt(robotSt.nextToken()),
				Integer.parseInt(robotSt.nextToken())
			);
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			map[i] = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		Solution s = new Solution(N, M, r, map);
		System.out.println(s.solution());
	}
}

class Solution {
	int N;
	int M;
	Robot robot;
	int[][] map;
	int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	int answer;
	
	public Solution(int N, int M, Robot robot, int[][] map) {
		this.N = N;
		this.M = M;
		this.robot = robot;
		this.map = map;
	}
	
	public int solution() {
		while(true) {
			int row = robot.getRow();
			int column = robot.getColumn();
			if(map[row][column]==1) break;
			if(map[row][column]==0) {
				answer++;
				map[row][column] = 2;
			}
			
			boolean isNearBy = false;
			for(int[]d : direction) {
				int nextRow = row + d[0];
				int nextColumn = column + d[1];
				if(isOut(nextRow, nextColumn)) continue;
				if(map[row + d[0]][column + d[1]]==0) isNearBy = true;
			}
			if(isNearBy) {
				robot.rotate();
				robot.move();
				if(isOut(robot.getRow(), robot.getColumn())) {
					robot.back();
					continue;
				}
				if(map[robot.getRow()][robot.getColumn()]!=0) robot.back();
			} else {
				robot.back();
			}
		}
		return answer;
	}
	
	private boolean isOut(int row, int column) {
		if(row<0 || row>=N) return true;
		if(column<0 || column>=M) return true;
		return false;
	}
	
	private void dfs() {
		int row = robot.getRow();
		int column = robot.getColumn();
		if(map[row][column]==0) answer++;

		
		for(int[]d : direction) {
			if(map[row + d[0]][column + d[1]]==0) {
				robot.rotate();
				robot.move();
				if(map[robot.getRow()][robot.getColumn()]==0) {
					dfs();
					break;
				} else {
					robot.back();
				}
			}
		}
	}
}

class Robot {
	private int row;
	private int column;
	private int direction;

	public Robot(int row, int column, int direction) {
		this.row = row;
		this.column = column;
		this.direction = direction;
	}

	public void move() {
		if (direction == 0)
			row--;
		else if (direction == 1)
			column++;
		else if (direction == 2)
			row++;
		else if (direction == 3)
			column--;
	}

	public void back() {
		if (direction == 0)
			row++;
		else if (direction == 1)
			column--;
		else if (direction == 2)
			row--;
		else if (direction == 3)
			column++;
	}

	public void rotate() {
		// 0 -> 3, 1 -> 0, 2 -> 1, 3 -> 2
		direction--;
		if (direction < 0)
			direction += 4;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getDirection() {
		return direction;
	}
}
