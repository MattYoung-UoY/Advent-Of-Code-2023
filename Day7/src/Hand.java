import java.util.HashMap;

public abstract class Hand implements Comparable<Hand>{

	private String hand;
	private int bid, category;
	
	abstract int getCategory(HashMap<Character, Integer> cards);
	public abstract int compareTo(Hand otherHand);
	
	public Hand(String hand, int bid){
		if(hand.length() != 5) throw new IllegalArgumentException("Hands must be of length 5! Hand: \"" + hand + "\" has length " + hand.length());
		this.hand = hand;
		this.bid = bid;
		this.category = getCategory(getCardCount(hand));;
	}
	
	HashMap<Character, Integer> getCardCount(String hand){
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

	public int getCategory() {
		return category;
	}
	
}
