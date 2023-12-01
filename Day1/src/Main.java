import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		// -- Part 1

		int total = 0;

		for (String line : input) {
			int first = -1, last = -1;
			for (char c : line.toCharArray()) {
				if (Character.isDigit(c)) {
					last = Character.getNumericValue(c);
					if (first == -1)
						first = last;
				}
			}
			total += Integer.parseInt("" + first + last);
		}

		System.out.println("Part 1: " + total);

		// -- Part 2

		total = 0;

//		HashMap<String, Integer> words = new HashMap<>();
		
		String[] words = {"oneight", "twone", "threeight", "fiveight", "sevenine", "eightwo", "eighthree", "nineight", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		int[] numbers = {18, 21, 38, 58, 79, 82, 83, 98, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//		words.put("oneight", 18);
//		words.put("twone", 21);
//		words.put("threeight", 38);
//		words.put("fiveight", 58);
//		words.put("sevenine", 79);
//		words.put("eightwo", 82);
//		words.put("eighthree", 83);
//		words.put("nineight", 98);
//		//Bodge Ends Here
//		words.put("one", 1);
//		words.put("two", 2);
//		words.put("three", 3);
//		words.put("four", 4);
//		words.put("five", 5);
//		words.put("six", 6);
//		words.put("seven", 7);
//		words.put("eight", 8);
//		words.put("nine", 9);

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < words.length; j++) {
				input[i] = input[i].replaceAll(words[j], String.valueOf(numbers[j]));
			}
		}

		for (String line : input) {
			int first = -1, last = -1;
			for (char c : line.toCharArray()) {
				if (Character.isDigit(c)) {
					last = Character.getNumericValue(c);
					if (first == -1)
						first = last;
				}
			}
			total += Integer.parseInt("" + first + last);
		}

		System.out.println("Part 2: " + total);
		
	}

}
