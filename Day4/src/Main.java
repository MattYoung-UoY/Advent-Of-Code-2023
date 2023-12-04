import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

		ArrayList<Card> cards = new ArrayList<Card>();
		Arrays.asList(input).stream().forEach(line -> cards.add(new Card(line)));
		int totalPoints = cards.stream().mapToInt(card -> (int) (card.matches() == 0 ? 0 : Math.pow(2, card.matches() - 1))).sum();
		System.out.println("Part 1 - " + totalPoints);
		
		long[] copies = new long[cards.size()];
		for(int i = 0; i < copies.length; i++) copies[i] = 1;
		for(int currentCard = 1; currentCard <= cards.size(); currentCard++) {
			int result = getCardByID(currentCard, cards).matches();
			System.out.println(currentCard + " has " + result + " matches");
			for(int i = 1; i <= result; i++) {
				if(currentCard + i - 1 < copies.length) {
					copies[currentCard + i - 1] += copies[currentCard - 1];
				}
			}
			System.out.println(Arrays.toString(copies));
		}
		
		System.out.println(Arrays.toString(copies));
		
		System.out.println("Part 2 - " + Arrays.stream(copies).sum());
		
	}

	private static Card getCardByID(int id, List<Card> cards) {
		for(Card card: cards) if(card.getID() == id) return card;
		return null;
	}
	
	static class Card{
		
		private int id;
		private ArrayList<Integer> numbers = new ArrayList<Integer>();
		private ArrayList<Integer> winningNumbers = new ArrayList<Integer>();
		
		public Card(String input) {
			id = Integer.parseInt(input.substring(4, input.indexOf(':')).trim());
			
			String temp = input.substring(input.indexOf(':')+2);
			String[] parts = temp.split("\\|");
			numbers = getNumbers(parts[1]);
			winningNumbers = getNumbers(parts[0]);
		}
		
		private ArrayList<Integer> getNumbers(String input){
			String[] nums = input.trim().replaceAll("  ", " ").split(" ");
			return (ArrayList<Integer>) Arrays.stream(nums).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		}
		
		public int matches() {
			int matches = 0;
			for(int num: numbers) if(winningNumbers.contains(num)) matches++;
			return matches;
		}
		
		private int getID() {
			return id;
		}
		
	}
	
}
