import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		// Load input
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		long[] seeds = Arrays.stream(input[0].substring(7).split(" ")).mapToLong(Long::parseLong).toArray();

		AoCMap[] maps = new AoCMap[7];
		int mapNum = 0;
		ArrayList<String> currentMap = new ArrayList<String>();
		
		for(int line = 2; line < input.length; line++) {
			if(input[line].equals("")) {
				maps[mapNum] = new AoCMap(currentMap.toArray(new String[0]));
				mapNum++;
				currentMap.clear();
			}else if(Character.isDigit(input[line].charAt(0))){
				currentMap.add(input[line]);
			}
		}
		maps[mapNum] = new AoCMap(currentMap.toArray(new String[0]));

		//Part 1
				
		long minResult = -1;
		
		for(long seed: seeds) {
			long result = seed;
			for(AoCMap map: maps) result = map.applyMap(result);
			minResult = minResult == -1 ? result : Math.min(minResult, result);
		}
		
		System.out.println("Part 1 - " + minResult);
		
		//Part 2
		
		for(long i = 0; i < Long.MAX_VALUE; i++) {
			long result = i;
			for(int map = maps.length - 1; map >= 0; map--) {
				result = maps[map].invMap(result);
			}
			if(isSeed(result, seeds)) {
				System.out.println("Part 2 - " + i);
				break;
			}
		}
		
	}

	private static boolean isSeedPt1(long seed, long[] seeds) {
		return Arrays.stream(seeds).anyMatch(x -> x == seed);
	}
	
	private static boolean isSeed(long seed, long[] seeds) {
		for(int pair = 0; pair < seeds.length/2; pair += 2) {
			if(seed >= seeds[pair] && seed < seeds[pair] + seeds[pair + 1]) return true;
		}
		return false;
	}
	
	static class AoCMap{
		
		private String[] maps;
		
		public AoCMap(String[] maps) {
			this.maps = maps;
		}
		
		public long applyMap(long input) {
			
			for(String map: maps) {
				long[] parts = Arrays.stream(map.split(" ")).mapToLong(Long::parseLong).toArray();
				long source = parts[1];
				long dest = parts[0];
				long range = parts[2];
				
				if(input >= source && input < (source + range)) {
					long offset = input - source;
					return dest + offset;
				}
				
			}
			
			return input;
		}
		
		public long invMap(long output) {
			for(String map: maps) {
				long[] parts = Arrays.stream(map.split(" ")).mapToLong(Long::parseLong).toArray();
				long source = parts[1];
				long dest = parts[0];
				long range = parts[2];
				
				if(output >= dest && output < (dest + range)) {
					long offset = output - dest;
					return source + offset;
				}
				
			}
			
			return output;
		}
		
		public String toString() {
			return Arrays.toString(maps);
		}
		
	}
	
}
