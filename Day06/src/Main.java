import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

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

		int[] times = Arrays.stream(input[0].replaceAll(" +", " ").substring(input[0].indexOf(':') + 1).trim().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] records = Arrays.stream(input[1].replaceAll(" +", " ").substring(input[1].indexOf(':') + 1).trim().split(" ")).mapToInt(Integer::parseInt).toArray();

		int result = 1;
		
		for(int i = 0; i < times.length; i++) {
			System.out.println(waysToBreakRecord(times[i], records[i]));
			result *= waysToBreakRecord(times[i], records[i]);
		}
		
		System.out.println("Part 1 - " + result);
		
		String timePt2 = "";
		String recordPt2 = "";

		for(int time: times) timePt2 = timePt2 + time;
		for(int record: records) recordPt2 = recordPt2 + record;
		
		System.out.println("Part 2 - " + waysToBreakRecord(Long.parseLong(timePt2), Long.parseLong(recordPt2)));
		
	}

	private static long waysToBreakRecord(int t, int r) {
		return waysToBreakRecord((long) t, (long) t);
	}
	
	private static long waysToBreakRecord(long t, long r) {
		return (long) (Math.ceil(((t+Math.sqrt((t*t)-(4*r)))/2)-1)
				- Math.floor(((t-Math.sqrt((t*t)-(4*r)))/2)+1)
				+ 1);
	}
	
}
