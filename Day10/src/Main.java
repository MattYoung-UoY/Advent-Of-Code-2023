import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {

		// Load input
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length(); j++) {
				if(input[i].charAt(j) == 'S') {
					int loopLen = getLoopLength(input, j, i);
					System.out.println("Part 1 - " + (loopLen / 2));
				}
			}
		}
		
	}
	
	private static int getLoopLength(String[] maze, int x, int y) {
		//return traverseMaze(maze, x, y, x, y, x, y);
		return traverseMaze(maze, x, y);
	}
	
	private static int traverseMaze(String[] maze, int x, int y) {
		int prevX = x, prevY = y;
		int nextMove = getNextSpace(maze, x, y, prevX, prevY);
		int moves = 0;
		while(true) {
			prevX = x;
			prevY = y;
			switch(nextMove) {
			case 0:
				if(maze[y-1].charAt(x) == 'S') return moves + 1;
				y--;
				break;
			case 1:
				if(maze[y].charAt(x+1) == 'S') return moves + 1;
				x++;
				break;
			case 2:
				if(maze[y+1].charAt(x) == 'S') return moves + 1;
				y++;
				break;
			case 3:
				if(maze[y].charAt(x-1) == 'S') return moves + 1;
				x--;
				break;
			}
			nextMove = getNextSpace(maze, x, y, prevX, prevY);
			moves++;
		}
	}
	
//	private static int traverseMaze(String[] maze, int x, int y, int prevX, int prevY, int startX, int startY) {
//		int nextMove = getNextSpace(maze, x, y, prevX, prevY);
////		switch(maze[y].charAt(x)) {
////		case 'S':
////		case '|':
////		case '-':
////		case 'L':
////		case 'J':
////		case '7':
////		case 'F':
////			//return makeMove(nextMove, maze, x, y, prevX, prevY, startX, startY) + 1;
//			switch(nextMove) {
//			case 0:
//				if(maze[y-1].charAt(x) == 'S') return 1;
//				return traverseMaze(maze, x, y-1, x, y, startX, startY) + 1;
//			case 1:
//				if(maze[y].charAt(x+1) == 'S') return 1;
//				return traverseMaze(maze, x+1, y, x, y, startX, startY) + 1;
//			case 2:
//				if(maze[y+1].charAt(x) == 'S') return 1;
//				return traverseMaze(maze, x, y+1, x, y, startX, startY) + 1;
//			case 3:
//				if(maze[y].charAt(x-1) == 'S') return 1;
//				return traverseMaze(maze, x-1, y, x, y, startX, startY) + 1;
//			}
//			System.err.println("Something went wrong =( " + nextMove);
//			System.exit(-1);
//			return -1;
////		}
////		return -1;
//	}

//	private static int makeMove(int nextMove, String[] maze, int x, int y, int prevX, int prevY, int startX, int startY) {
//		
//	}
	
	/*
	 * 0 - Move Up
	 * 1 - Move Right
	 * 2 - Move Down
	 * 3 - Move Left
	 */
	private static int getNextSpace(String[] maze, int x, int y, int prevX, int prevY) {
		char curSpace = maze[y].charAt(x);
		
		//Check Top
		if(y > 0 && (y-1) != prevY) {
			char top = maze[y-1].charAt(x);
			if(top == '|' || top == '7' || top == 'F' || top == 'S') {
				if(curSpace == '|' || curSpace == 'L' || curSpace == 'J' || curSpace == 'S') {
					return 0;
				}
			}
		}
		if(y < maze.length - 1 && (y+1) != prevY) {
			char bottom = maze[y+1].charAt(x);
			if(bottom == '|' ||bottom == 'L' ||bottom == 'J' ||bottom == 'S') {
				if(curSpace == '|' || curSpace == 'F' || curSpace == '7' || curSpace == 'S') {
					return 2;
				}
			}
		}
		if(x > 0 && (x-1) != prevX) {
			char left = maze[y].charAt(x-1);
			if(left == '-' ||left == 'L' ||left == 'F' ||left == 'S') {
				if(curSpace == '-' || curSpace == 'J' || curSpace == '7' || curSpace == 'S') {
					return 3;
				}
			}
		}
		if(x < maze[0].length() - 1 && (x+1) != prevX) {
			char right = maze[y].charAt(x+1);
			if(right == '-' ||right == 'J' ||right == '7' ||right == 'S') {
				if(curSpace == '-' || curSpace == 'F' || curSpace == 'L' || curSpace == 'S') {
					return 1;
				}
			}
		}
		return -1;
	}
	
}
