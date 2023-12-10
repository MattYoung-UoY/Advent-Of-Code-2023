import java.util.HashMap;

public class RegularHand extends Hand{
	
	public RegularHand(String hand, int bid) {
		super(hand, bid);
	}
	
	/* 	-ve - h1 < h2
	 *  0   - h1 == h2
	 *  +ve - h1 > h2
	 */ 
	@Override
	public int compareTo(Hand otherHand) {
		
		if(getCategory() > otherHand.getCategory()) {
			return 1;
		}else if(getCategory() < otherHand.getCategory()) {
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
	@Override
	int getCategory(HashMap<Character, Integer> cards) {
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
	
}
