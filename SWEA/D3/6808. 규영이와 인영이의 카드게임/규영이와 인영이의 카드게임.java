import java.util.*;
import java.util.stream.*;
import java.io.*;

public class Solution {
	static int[] myCards;
	static int[] opponentDeck;
	static int[] opponentCards;
	static boolean[] visited;
	static int winCount;
	static int loseCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=test; tc++) {
			sb.append("#").append(tc).append(" ");
			myCards = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			opponentDeck = IntStream.rangeClosed(1, 18)
					.filter(e -> Arrays.stream(myCards).noneMatch(i -> i == e))
					.toArray();
			opponentCards = new int[9];
			visited = new boolean[9];
			
			winCount = 0;
			loseCount = 0;
			
			perm(0);
			sb.append(winCount).append(" ").append(loseCount).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void perm(int depth) {
		if(depth == 9) {
			generateGameResult();
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			opponentCards[depth] = opponentDeck[i];
			perm(depth + 1);
			visited[i] = false;
		}
	}
	
	static void generateGameResult() {
		int me = 0;
		int opponent = 0;
		for(int i=0; i<9; i++) {
			if(myCards[i] > opponentCards[i]) {
				me += (myCards[i] + opponentCards[i]);
			} else {
				opponent += (myCards[i] + opponentCards[i]);
			}
		}
		
		if(me > opponent) winCount++;
		else if(opponent > me) loseCount++;
	}
}
