import java.util.Scanner;
import java.util.*;
import java.util.stream.*;

class Main {
	static int[] parent;
	static boolean[] visited;

	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		List<ArrayList<Integer>> adj = IntStream.range(0, N+1).mapToObj(idx -> new ArrayList<Integer>()).collect(Collectors.toList());
		parent = new int[N+1];
		visited = new boolean[N+1];
		
		while(N-- > 1) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			adj.get(n1).add(n2);
			adj.get(n2).add(n1);
		}
		
		dfs(adj, 1);
		
		for(int i=2; i<parent.length; i++) {
			System.out.println(parent[i]);
		}
		sc.close();
	}

	private static void dfs(List<ArrayList<Integer>> adj, int cur) {
		visited[cur] = true;
		ArrayList<Integer> curAdj = adj.get(cur);
		for (int i = 0; i < curAdj.size(); i++) {
			int next = curAdj.get(i);
			if (visited[next]) continue;
			parent[next] = cur;
			dfs(adj, next);
		}
	}
}