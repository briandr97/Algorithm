import java.io.*;
import java.util.*;

public class Solution {
    private static int SIZE = 700;
    private static boolean[][] visited;
    private static ArrayDeque<Cell> cells;
    private static PriorityQueue<Cell> breedingCells;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            visited = new boolean[SIZE][SIZE];
            cells = new ArrayDeque<>();
            breedingCells = new PriorityQueue<>();

            int startRow = (SIZE - N) / 2;
            int startColumn = (SIZE - M) / 2;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int row = i + startRow;
                    int column = j + startColumn;
                    int strong = Integer.parseInt(st.nextToken());

                    if (strong == 0) continue;
                    cells.offer(new Cell(row, column, strong));
                    visited[row][column] = true;
                }
            }

            while (K-- > 0) {
                int cellSize = cells.size();

                // 모든 세포의 시간이 흐른다.
                for (int i = 0; i < cellSize; i++) {
                    Cell cell = cells.poll();
                    State state = cell.passTime();

                    if (cell.isBreeding()) {
                        for (Direction d : Direction.values()) {
                            Cell nextCell = cell.copy(d);
                            if (visited[nextCell.getRow()][nextCell.getColumn()]) continue;
                            breedingCells.offer(nextCell);
                        }
                    }

                    if (state == State.DEAD) continue;
                    cells.offer(cell);
                }

                // 번식 후보 세포들 중 정말 번식될 세포만 고른다.
                while (!breedingCells.isEmpty()) {
                    Cell cell = breedingCells.poll();
                    if (visited[cell.getRow()][cell.getColumn()]) continue;

                    cells.offer(cell);
                    visited[cell.getRow()][cell.getColumn()] = true;
                }
            }

            sb.append(cells.size()).append("\n");
        }

        System.out.println(sb);
    }

}

class Cell implements Comparable<Cell> {
    private final int row, column, strong;
    private int time;
    private State state;

    public Cell(int row, int column, int strong, int time, State state) {
        this.row = row;
        this.column = column;
        this.strong = strong;
        this.time = time;
        this.state = state;
    }

    public Cell(int row, int column, int strong) {
        this(row, column, strong, 0, State.ALIVE);
    }

    public Cell copy(Direction d) {
        return new Cell(row + d.dr, column + d.dc, strong, 0, State.ALIVE);
    }

    public State passTime() { // 살아있으면 true 반환, 죽으면 false 반환
        if (++time < strong) return state;

        time = 0;
        if (state == State.ACTIVE) state = State.DEAD;
        else state = State.ACTIVE;
        return state;
    }

    public boolean isBreeding() {
        if (state == State.ACTIVE && time == 1) {
            return true;
        }
        if (state == State.DEAD && strong == 1) {
            return true;
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int compareTo(Cell other) {
        return other.strong - strong;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", strong=" + strong +
                ", time=" + time +
                ", state=" + state +
                '}';
    }
}

enum State {
    ALIVE,
    ACTIVE,
    DEAD;
}

enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    public final int dr, dc;

    private Direction(int dr, int dc) {
        this.dr = dr;
        this.dc = dc;
    }
}