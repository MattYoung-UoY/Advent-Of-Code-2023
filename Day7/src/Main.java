import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

		ArrayList<Hand> hands = new ArrayList<>();
		
		for(String hand: input) {
			hands.add(new Hand(hand.split(" ")[0], Integer.parseInt(hand.split(" ")[1])));
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

	private static class Hand implements Comparable<Hand>{

		private String hand;
		private int bid, category;
		
		public Hand(String hand, int bid) {
			if(hand.length() != 5) throw new IllegalArgumentException("Hands must be of length 5! Hand: \"" + hand + "\" has length " + hand.length());
			this.hand = hand;
			this.category = getCategory(getCardCount(hand));
			this.bid = bid;
		}
		
		/* 	-ve - h1 < h2
		 *  0   - h1 == h2
		 *  +ve - h1 > h2
		 */ 
		@Override
		public int compareTo(Hand otherHand) {
			
			if(this.category > otherHand.category) {
				return 1;
			}else if(this.category < otherHand.category) {
				return -1;
			}else {
				String cardOrder = "23456789TJQKA";
				for(int i = 0; i < 5; i++) {
					if(cardOrder.indexOf(this.getHand().charAt(i)) > cardOrder.indexOf(otherHand.getHand().charAt(i))) {
						return 1;
					}else if(cardOrder.indexOf(this.getHand().charAt(i)) < cardOrder.indexOf(otherHand.getHand().charAt(i))) {
						return -1;
					}
				}
			}
			return 0;
		}
		
		/*	6 - Five of a kind
		 *  5 - Four of a kind
		 *  4 - Full House
		 *  3 - Three of a kind
		 *  2 - Two Pair
		 *  1 - One Pair
		 *  0 - High Card
		 */
		private int getCategory(HashMap<Character, Integer> cards) {
			//Five of a kind
			if(cards.values().size() == 1) {
				return 6;
			}else if(cards.values().size() == 2) {
				//Four of a kind
				if(cards.values().contains(4)) {
					return 5;
				//Full House
				}else{
					return 4;
				}
			}else if(cards.values().size() == 3) {
				//3, 1, 1 - Three of a kind
				if(cards.values().contains(3)) {
					return 3;
				//2, 2, 1 - Two Pair
				}else {
					return 2;
				}
			//1, 1, 1, 2 - One Pair
			}else if(cards.values().size() == 4) {
				return 1;
			//High Card
			}else {
				return 0;
			}
		}
		
		private HashMap<Character, Integer> getCardCount(String hand){
			HashMap<Character, Integer> count = new HashMap<>();
			for(char c: hand.toCharArray()) {
				if(count.containsKey(c)) {
					count.put(c, count.get(c) + 1);
				}else {
					count.put(c, 1);
				}
			}
			return count;
		}
		
		public String getHand() {
			return hand;
		}
		
		public int getBid() {
			return bid;
		}
		
	}
	
}
