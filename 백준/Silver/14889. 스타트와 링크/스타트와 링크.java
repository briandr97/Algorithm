import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int teamSize = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[teamSize][teamSize];
		for(int i=0; i<teamSize; i++) {
			map[i] = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		Solution s = new Solution(teamSize, map);
		System.out.println(s.solution());
		br.close();
	}
}

class Solution {
	int[][] map;
	int teamSize;
	boolean[] team;
	Integer minGap = Integer.MAX_VALUE;
	
	public Solution(int teamSize, int[][] map) {
		this.teamSize = teamSize;
		this.map = map;
		team = new boolean[teamSize];
	}
	
	public int solution() {
		combination(0, 0);
		return minGap;
	}
	
	private void combination(int depth, int start) {
		if(depth==teamSize/2) {
			minGap = Math.min(minGap, getTeamScore());
			return;
		}

		for(int i=start; i<teamSize; i++) {
			team[i] = true;
			combination(depth+1, i+1);
			team[i] = false;
		}
	}
	
	private int getTeamScore() {
		int score1 = 0;
		int score2 = 0;
		for(int i=0; i<teamSize; i++) {
			for(int j=0; j<teamSize; j++) {
				if(i==j) continue;
				if(team[i] && team[j]) score1 += map[i][j];
				if(!team[i] && !team[j]) score2 += map[i][j];
			}
		}
		return Math.abs(score1 - score2);
	}
		
}