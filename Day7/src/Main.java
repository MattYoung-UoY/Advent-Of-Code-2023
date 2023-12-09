import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

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

		ArrayList<RegularHand> hands = new ArrayList<>();
		
		for(String hand: input) {
			hands.add(new RegularHand(hand.split(" ")[0], Integer.parseInt(hand.split(" ")[1])));
		}

		Collections.sort(hands);
		
		int total = 0;
		
		for(int i = 0; i < hands.size(); i++) {
			total += (i+1) * hands.get(i).getBid();
		}
		
		System.out.println("Part 1 - " + total);
		
		//For part 2 - rewrite compareTo to use new ordering, and rewrite getCategory to consider jokers.
		// ie. n jokers, now consider hand with 5-n cards, and given each scenario for cardCount dict, return appropriate category.
		//Maybe new Hand class? JokerHand?
		
	}

	
	
}
