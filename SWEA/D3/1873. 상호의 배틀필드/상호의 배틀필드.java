import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Solution {
	static int height, width;
	static Element[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int test = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			
			board = new Element[height][width];
			int tankRow = 0;
			int tankColumn = 0;
			for(int i=0; i<height; i++) {
				String str = br.readLine();
				for(int j=0; j<width; j++) {
					board[i][j] = Element.getElementByValue(str.charAt(j));
					if(board[i][j].isTank()) {
						tankRow = i;
						tankColumn = j;
					}
				}
			}
			
			int orderCount = Integer.parseInt(br.readLine());
			String orders = br.readLine();
			for(char c: orders.toCharArray()) {
				Order order = Order.getOrderByValue(c);
				if(order == Order.SHOOT) {
					shoot(tankRow, tankColumn);
					continue;
				}
				
				board[tankRow][tankColumn] = order.tank;
				int nextRow = tankRow + board[tankRow][tankColumn].dx;
				int nextColumn = tankColumn + board[tankRow][tankColumn].dy;
				
				if(nextRow < 0 || nextRow >= height || nextColumn < 0 || nextColumn >= width) continue;
				if(board[nextRow][nextColumn] == Element.GROUND) {
					board[nextRow][nextColumn] = board[tankRow][tankColumn];
					board[tankRow][tankColumn] = Element.GROUND;
					tankRow = nextRow;
					tankColumn = nextColumn;
				}
			}
			
			for(int i=0; i<height; i++) {
				for(int j=0; j<width; j++) {
					sb.append(board[i][j].value);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void shoot(int tankRow, int tankColumn) {
		int curRow = tankRow;
		int curColumn = tankColumn;
		
		Element tank = board[tankRow][tankColumn];
		while(!(curRow < 0 || curRow >= height || curColumn < 0 || curColumn >= width)) {
			if(board[curRow][curColumn] == Element.ROCK_WALL) {
				board[curRow][curColumn] = Element.GROUND;
				break;
			}
			if(board[curRow][curColumn] == Element.STEEL_WALL) {
				break;
			}
			
			curRow += tank.dx;
			curColumn += tank.dy;
		}
	}
}

enum Order {
	UP('U', Element.TANK_UP),
	DOWN('D', Element.TANK_DOWN),
	LEFT('L', Element.TANK_LEFT),
	RIGHT('R', Element.TANK_RIGHT),
	SHOOT('S', null),;
	
	public char value;
	public Element tank;
	
	private Order(char value, Element tank) {
		this.value = value;
		this.tank = tank;
	}
	
	public boolean isMoving() {
		return this != SHOOT;
	}
	
	public static Order getOrderByValue(char value) {
		return Stream.of(Order.values()).filter(o -> o.value == value).findFirst().get();
	}
}

enum Element {
	GROUND('.'),
	ROCK_WALL('*'),
	STEEL_WALL('#'),
	WATER('-'),
	TANK_UP('^', -1, 0),
	TANK_DOWN('v', 1, 0),
	TANK_LEFT('<', 0, -1),
	TANK_RIGHT('>', 0, 1),;
	
	public final char value;
	public final int dx, dy;
	
	Element(char value, int dx, int dy) {
		this.value = value;
		this.dx = dx;
		this.dy = dy;
	}
	
	Element(char value) {
		this.value = value;
		this.dx = 0;
		this.dy = 0;
	}
	
	public static Element getElementByValue(char value) {
		return Stream.of(Element.values()).filter(e -> e.value == value).findFirst().get();
	}
	
	public boolean isTank() {
		return (this == TANK_DOWN) || (this == TANK_LEFT) || (this == TANK_RIGHT) || (this == TANK_UP);
	}
}