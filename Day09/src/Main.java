import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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

		// Part 1
		
		int result = 0;
		for(String inp: input) {
			int[] sequence = Arrays.stream(inp.split(" ")).mapToInt(Integer::parseInt).toArray();
			result += nextInSequence(sequence);
		}
		System.out.println("Part 1 - " + result);
		
		// Part 2
		
		result = 0;
		for(String inp: input) {
			int[] sequence = Arrays.stream(inp.split(" ")).mapToInt(Integer::parseInt).toArray();
			result += previousInSequence(sequence);
		}
		System.out.println("Part 2 - " + result);
		
	}

	private static int previousInSequence(int[] seq) {
		ArrayList<Integer> diff = new ArrayList<>();
		for(int i = 1; i < seq.length; i++) diff.add(seq[i]-seq[i-1]);
		boolean allZero = true;
		for(int d: diff) {
			if(d != 0) {
				allZero = false;
				break;
			}
		}
		if(allZero) {
			return seq[0];
		}else {
			int valToAdd = previousInSequence(diff.stream().mapToInt(i->i).toArray());
			return seq[0] - valToAdd;
		}
	}
	
	private static int nextInSequence(int[] seq) {
		ArrayList<Integer> diff = new ArrayList<>();
		for(int i = 1; i < seq.length; i++) diff.add(seq[i]-seq[i-1]);
		boolean allZero = true;
		for(int d: diff) {
			if(d != 0) {
				allZero = false;
				break;
			}
		}
		if(allZero) {
			return seq[seq.length-1];
		}else {
			int valToAdd = nextInSequence(diff.stream().mapToInt(i->i).toArray());
			return seq[seq.length-1] + valToAdd;
		}
	}
	
}
