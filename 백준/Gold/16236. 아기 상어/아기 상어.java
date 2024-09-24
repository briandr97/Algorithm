import java.util.*;
import java.io.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mapSize = Integer.parseInt(br.readLine().trim());
        int[][] map = new int[mapSize][mapSize];
        
        for(int i=0; i<mapSize; i++) {
            int[] input = Stream.of(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[i] = input;
        }
        
        Graph g = new Graph(map);
        System.out.println(g.solution()); 
    }
}

class Graph {
    private final int[][] map;
    private final int mapSize;
    private Shark shark;
    private final static int SHARK_SIZE = 9;
    
    public Graph(int[][] map) {
        this.map = map;
        mapSize = map.length;
        shark = createShark();
    }
    
    private Shark createShark() {
        for(int i=0; i<mapSize; i++) {
            for(int j=0; j<mapSize; j++) {
                if(map[i][j]==SHARK_SIZE) {
                    return new Shark(new Point(i, j));
                }
            }
        }
        throw new IllegalStateException();
    }
    
    public int solution() {
        int answer = 0;
        while(true) {
            Fish f = bfs();
            if(f == null) break;
            
            answer += f.distance;
            map[shark.getRow()][shark.getColumn()] = 0;
            shark = shark.eat(f);
            map[shark.getRow()][shark.getColumn()] = 0;
        }
        return answer;
    }
    
    private Fish bfs() {
        Queue<Point> q = new LinkedList<Point>();
        boolean[][] visited = new boolean[mapSize][mapSize];
        int[][] direction = {{1,0},{-1,0},{0,1},{0,-1}};
        ArrayList<Fish> fishes = new ArrayList<Fish>();
        int level = 0;
        
        q.add(shark.point);
        visited[shark.getRow()][shark.getColumn()] = true;
        
        while(!q.isEmpty() && fishes.isEmpty()) {
            int qSize = q.size();
            level++;
            
            while(qSize-- > 0) {
                Point cur = q.poll();
                for(int[] d: direction) {
                    Point next = cur.plus(d[0], d[1]);
                    if(isOut(next.row, next.column)) continue;
                    if(visited[next.row][next.column]) continue;
                    
                    int nextValue = map[next.row][next.column];
                    if(!shark.canMove(nextValue)) continue;
                    
                    visited[next.row][next.column] = true;
                    if(nextValue != 0 && shark.canEat(nextValue)) fishes.add(new Fish(next, nextValue));
                    else q.add(next);
                    
                }    
            }
        }
        
        for(Fish f: fishes) {
            f.distance = level;
        }
        
        Fish nearFish = null;
        for(Fish f: fishes) {
            if(nearFish == null) nearFish = f;
            else if(f.isNearerThan(nearFish)) nearFish = f;
        }
        
        return nearFish;
    }
    
    private boolean isOut(int row, int column) {
        if(row < 0 || column < 0) return true;
        if(row >= mapSize || column >= mapSize) return true;
        return false;
    }
}

class Fish {
    final Point point;
    final int size;
    int distance = 0;
    
    public Fish(Point point, int size) {
        this.point = point;
        this.size = size;
    }
    
    public boolean isNearerThan(Fish other) {
        if(point.row < other.point.row) return true;
        else if(point.row > other.point.row) return false;
        else if(point.column < other.point.column) return true;
        else if(point.column > other.point.column) return false;
        else throw new IllegalStateException("fish: " + this + ", other: " + other);
    }
    
    public String toString() {
        return new StringBuilder()
            .append("Fish(point: ").append(point)
            .append(", size: ").append(size)
            .append(", distance: ").append(distance)
            .append(")").toString();
    }
}

class Point {
    final int row;
    final int column;
    
    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public Point plus(Point other) {
        return new Point(row + other.row, column + other.column);
    }
    
    public Point plus(int row, int column) {
        return new Point(this.row + row, this.column + column);
    }
    
    public String toString() {
        return new StringBuilder()
            .append("Point(row: ").append(row)
            .append(", column: ").append(column)
            .append(")").toString();
    }
}

class Shark {
    final Point point;
    final int size;
    final int fishCount;
    
    public Shark(Point point) {
        this.point = point;
        this.size = 2;
        this.fishCount = 0;
    }
    
    public Shark(Point point, int size, int fishCount) {
        this.point = point;
        this.size = size;
        this.fishCount = fishCount;
    }
    
    public int getRow() {
        return point.row;
    }
    
    public int getColumn() {
        return point.column;
    }
    
    public boolean canMove(int fishSize) {
        if(size >= fishSize) return true;
        return false;
    }
    
    public boolean canEat(int fishSize) {
        if(size > fishSize) return true;
        return false;
    }
    
    public Shark eat(Fish fish) {
        int newSize = size;
        int newFishCount = fishCount + 1;
        
        if(newFishCount == size) {
            newSize++;
            newFishCount=0;
        }
        
        return new Shark(fish.point, newSize, newFishCount);
    }
}