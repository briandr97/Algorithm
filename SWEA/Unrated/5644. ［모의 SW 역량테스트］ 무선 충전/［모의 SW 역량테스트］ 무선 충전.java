import java.util.*;
import java.io.*;

public class Solution {
	static int timeLimit, bcCount;
	static BatteryCharger[] bcs;
	static Person A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int test = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			timeLimit = Integer.parseInt(st.nextToken());
			bcCount = Integer.parseInt(st.nextToken());

			Direction[] aDirections = new Direction[timeLimit];
			Direction[] bDirections = new Direction[timeLimit];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < timeLimit; i++) {
				aDirections[i] = Direction.getDirectionByOrdinal(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < timeLimit; i++) {
				bDirections[i] = Direction.getDirectionByOrdinal(Integer.parseInt(st.nextToken()));
			}

			bcs = new BatteryCharger[bcCount];
			for (int i = 0; i < bcCount; i++) {
				st = new StringTokenizer(br.readLine());
				bcs[i] = new BatteryCharger(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			final int N = 10;
			A = new Person(1, 1);
			B = new Person(N, N);

			int sum = 0;
			for (int i = 0; i <= timeLimit; i++) {

				int max = 0;
				for (int aIdx = 0; aIdx < bcCount; aIdx++) {
					for (int bIdx = 0; bIdx < bcCount; bIdx++) {
						max = Math.max(max, getPower(aIdx, bIdx));
					}
				}

				sum += max;
				
				if(i == timeLimit) break;
				A.move(aDirections[i]);
				B.move(bDirections[i]);
			}

			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

	private static int getPower(int aIdx, int bIdx) {
		int aPower = bcs[aIdx].power;
		int bPower = bcs[bIdx].power;

		boolean isAIn = bcs[aIdx].isInBC(A.x, A.y);
		boolean isBIn = bcs[bIdx].isInBC(B.x, B.y);

		if (isAIn && isBIn && aIdx == bIdx) {
			return aPower;
		}
		if (!isAIn) {
			aPower = 0;
		}
		if (!isBIn) {
			bPower = 0;
		}

		return aPower + bPower;
	}
}

enum Direction {
	STAY(0, 0), UP(-1, 0), RIGHT(0, 1), DOWN(1, 0), LEFT(0, -1),;

	public final int dx, dy;

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	static Direction getDirectionByOrdinal(int num) {
		return Direction.values()[num];
	}
}

class BatteryCharger {
	final int x, y, range, power;

	public BatteryCharger(int y, int x, int range, int power) {
		this.x = x;
		this.y = y;
		this.range = range;
		this.power = power;
	}

	public boolean isInBC(int x, int y) {
		return getDistance(this.x, this.y, x, y) <= range;
	}

	private int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}

class Person {
	int x, y;

	public Person(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(Direction d) {
		int nx = x + d.dx;
		int ny = y + d.dy;
		if (nx < 1 || nx > 10 || ny < 1 || ny > 10)
			return;
		x = nx;
		y = ny;
	}
}