import java.util.*;
import java.io.*;

class Solution {
    int[][] points, routes;
    
    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        this.routes = routes;
        Queue<Point>[] paths = (Queue<Point>[]) new Queue[routes.length];
        
        for(int i=0; i<routes.length; i++) {
            Queue<Point> path = getPath(i);
            paths[i] = path;
        }
        
        int completedRobots = 0;
        int answer = 0;
        while(completedRobots < routes.length) {
            int[][] visited = new int[101][101];
            for(int i=0; i<routes.length; i++) {
                Queue<Point> path = paths[i];
                if(path == null) continue;
                if(path.isEmpty()) {
                    completedRobots++;
                    paths[i] = null;
                    continue;
                }
                Point p = path.poll();
                if(visited[p.r][p.c] == 1) answer++;
                visited[p.r][p.c]++;
            }
        }
        
        return answer;
    }
    
    private Queue<Point> getPath(int routeIdx) {
        ArrayDeque<Point> path = new ArrayDeque<Point>();
        int[] route = routes[routeIdx];
        int[] start = points[route[0] - 1];
        Point cur = new Point(start[0], start[1]);
        path.offer(cur);
        
        for(int i=1; i<route.length; i++) {
            Point end = new Point(points[route[i] - 1][0], points[route[i] - 1][1]);
            while(cur.r != end.r) {
                if(cur.r < end.r) {
                    cur = cur.plus(1, 0);
                } else {
                    cur = cur.plus(-1, 0);
                }
                path.offer(cur);
            }
            while(cur.c != end.c) {
                if(cur.c < end.c) {
                    cur = cur.plus(0, 1);
                } else {
                    cur = cur.plus(0, -1);
                }
                path.offer(cur);
            }
        }
        
        return path;
    }
}

class Point {
    final int r, c;
    
    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    public Point plus(int r, int c) {
        return new Point(this.r + r, this.c + c);
    }
}