import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        boolean[] isMac = new boolean[V + 1];
        boolean[] isStar = new boolean[V + 1];
        int[] mDists = new int[V + 1];
        int[] sDists = new int[V + 1];

        @SuppressWarnings("unchecked")
        List<Edge>[] adj = (ArrayList<Edge>[]) new ArrayList[V + 1];
        for(int i=1; i<=V; i++) {
            adj[i] = new ArrayList<Edge>();
            mDists[i] = Integer.MAX_VALUE;
            sDists[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(to, dist));
            adj[to].add(new Edge(from, dist));
        }

        PriorityQueue<Edge> mHeap = new PriorityQueue<>();
        PriorityQueue<Edge> sHeap = new PriorityQueue<>();

        int mLimit = getShop(br, mHeap, isMac);
        int sLimit = getShop(br, sHeap, isStar);

        dijkstra(adj, mHeap, mDists, isMac, mLimit);
        dijkstra(adj, sHeap, sDists, isStar, sLimit);

        int min = Integer.MAX_VALUE;
        for(int i=1; i<=V; i++) {
            if(mDists[i] == Integer.MAX_VALUE) continue;
            if(sDists[i] == Integer.MAX_VALUE) continue;
            min = Math.min(min, mDists[i] + sDists[i]);
        }

        if(min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }

    private static int getShop(BufferedReader br, PriorityQueue<Edge> heap, boolean[] isShop) throws IOException { // limit를 반환
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<count; i++) {
            int shop = Integer.parseInt(st.nextToken());
            isShop[shop] = true;
            heap.offer(new Edge(shop, 0));
        }

        return limit;
    }

    private static void dijkstra(List<Edge>[] adj, PriorityQueue<Edge> heap, int[] dists, boolean[] isShop, int limit) {
        while(!heap.isEmpty()) {
            Edge cur = heap.poll();
            if(dists[cur.to] < cur.dist) continue;

            for(Edge e: adj[cur.to]) {
                int next = e.to;
                if(isShop[next]) continue;

                int nextDist = cur.dist + e.dist;
                if(nextDist > limit) continue;
                if(dists[next] < nextDist) continue;

                dists[next] = nextDist;
                heap.add(new Edge(next, nextDist));
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int to, dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge other) {
            if(dist == other.dist) return Integer.compare(to, other.to);
            return Integer.compare(dist, other.dist);
        }
    }
}
