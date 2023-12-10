import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

		// Loop through each line
		// Loop through each character
		// If not a digit or a '.' then it is a symbol
		// Check adjacent spaces for numbers
		// If they exist, add them to a list, only if not already present in the list

		double startTime = System.currentTimeMillis();
		
		int total = 0;
		int totalGearRatios = 0;
		for (int y = 0; y < input.length; y++) {
			for (int x = 0; x < input[y].length(); x++) {
				if (!Character.isDigit(input[y].charAt(x)) && input[y].charAt(x) != '.') {

					boolean isGear = input[y].charAt(x) == '*';
					ArrayList<Integer> gearVals = new ArrayList<Integer>();

					// Top line
					if (y > 0) {
						ArrayList<Integer> parts = new ArrayList<Integer>();
						if (x > 0 && Character.isDigit(input[y - 1].charAt(x - 1))) {
							int val = Integer.parseInt(getNum(input[y - 1], x - 1, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						if (Character.isDigit(input[y - 1].charAt(x))) {
							int val = Integer.parseInt(getNum(input[y - 1], x, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						if (x < input[y - 1].length() - 1 && Character.isDigit(input[y - 1].charAt(x + 1))) {
							int val = Integer.parseInt(getNum(input[y - 1], x + 1, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						total += parts.stream().collect(Collectors.summingInt(Integer::intValue));
						parts.stream().forEach(i -> gearVals.add(i));
					}

					// Middle line
					{
						ArrayList<Integer> parts = new ArrayList<Integer>();
						if (x > 0 && Character.isDigit(input[y].charAt(x - 1))) {
							int val = Integer.parseInt(getNum(input[y], x - 1, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						if (x < input[y - 1].length() - 1 && Character.isDigit(input[y].charAt(x + 1))) {
							int val = Integer.parseInt(getNum(input[y], x + 1, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						total += parts.stream().collect(Collectors.summingInt(Integer::intValue));
						parts.stream().forEach(i -> gearVals.add(i));
					}

					// Bottom line
					if (y < input.length - 1) {
						ArrayList<Integer> parts = new ArrayList<Integer>();
						if (x > 0 && Character.isDigit(input[y + 1].charAt(x - 1))) {
							int val = Integer.parseInt(getNum(input[y + 1], x - 1, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						if (Character.isDigit(input[y + 1].charAt(x))) {
							int val = Integer.parseInt(getNum(input[y + 1], x, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						if (x < input[y - 1].length() - 1 && Character.isDigit(input[y + 1].charAt(x + 1))) {
							int val = Integer.parseInt(getNum(input[y + 1], x + 1, 0));
							if (!parts.contains(val))
								parts.add(val);
						}
						total += parts.stream().collect(Collectors.summingInt(Integer::intValue));
						parts.stream().forEach(i -> gearVals.add(i));
					}
					
					if(isGear && gearVals.size() == 2) {
						totalGearRatios += gearVals.stream().reduce(1, (a, b) -> a * b);
					}
					
				}
			}
		}
		System.out.println("Took " + (System.currentTimeMillis() - startTime) + "ms");
		System.out.println("Part 1 - " + total);
		System.out.println("Part 2 - " + totalGearRatios);

	}

	private static String getNum(String str, int x, int dir) {
		String res = "";

		if (Character.isDigit(str.charAt(x))) {
			res += str.charAt(x);
			if (x > 0 && (dir != 1)) {
				res = getNum(str, x - 1, -1) + res;
			}
			if (x < str.length() - 1 && (dir != -1)) {
				res += getNum(str, x + 1, 1);
			}
		}

		return res;
	}

}
