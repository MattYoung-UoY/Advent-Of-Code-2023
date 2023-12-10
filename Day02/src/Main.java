import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		//Load input
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		//Create List of Games - Constructor to parse list of cubes?
		ArrayList<Game> games = new ArrayList<Game>();
		for(String game: input) {
			String[] parts = game.split(":");
			parts[0] = parts[0].replaceAll("Game ", "");
			parts[1] = parts[1].trim();
			games.add(new Game(Integer.parseInt(parts[0]), parts[1]));
		}
		
		//Check each game if it is possible given dice available - func bool possible (CubeSet cubesAvail)
		CubeSet cubesAvailable = new CubeSet(12, 13, 14);
		int total = 0;
		
		for(Game game: games) {
			if(game.isPossible(cubesAvailable)) {
				total += game.getID();
			}
		}
		
		System.out.println("Part 1 - " + total);
		
		int totalPower = games.stream().collect(Collectors.summingInt(Game::getPower));
		System.out.println("Part 2 - " + totalPower);
		
	}
	
	static class Game{
		private int id, power;
		private ArrayList<CubeSet> handsShown;
		
		public Game(int id, String gameResults) {
			ArrayList<CubeSet> handsShown = new ArrayList<CubeSet>();
			
			String[] results = gameResults.split(";");
			for(String result: results) {
				int r = 0, g = 0, b = 0;
				String[] cubes = result.trim().split(",");
				for(String cube: cubes) {
					String[] parts = cube.trim().split(" ");
					switch(parts[1]) {
					case "red":
						r += Integer.parseInt(parts[0]);
						break;
					case "green":
						g += Integer.parseInt(parts[0]);
						break;
					case "blue":
						b += Integer.parseInt(parts[0]);
						break;
					}
				}
				handsShown.add(new CubeSet(r, g, b));
			}

			this.id = id;
			this.handsShown = handsShown;
			
			int minR = 0, minG = 0, minB = 0;
			power = 0;
			
			for(CubeSet hand: handsShown) {
				minR = Math.max(minR, hand.getR());
				minG = Math.max(minG, hand.getG());
				minB = Math.max(minB, hand.getB());
			}
			
			power = minR * minG * minB;
			
		}
		
		public boolean isPossible(CubeSet cubesAvail) {
			for(CubeSet hand: handsShown) {
				if(hand.getR() > cubesAvail.getR() ||
						hand.getG() > cubesAvail.getG() ||
						hand.getB() > cubesAvail.getB()) {
					//System.out.println("Game " + id + " with hand " + hand + " not possible for " + cubesAvail);
					return false;
				}
			}
			return true;
		}
		
		public int getID() {
			return id;
		}
		
		public int getPower() {
			return power;
		}
	}
	
	static class CubeSet{
		private int r, g, b;

		public CubeSet(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}

		public int getR() {
			return r;
		}

		public int getG() {
			return g;
		}

		public int getB() {
			return b;
		}
		
		public String toString() {
			return "(r:" + r + ", g:" + g + ", b:" + b + ")";
		}
	}
	
}
