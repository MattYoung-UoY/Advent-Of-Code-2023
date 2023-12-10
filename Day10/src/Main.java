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

	}

}
