import java.util.HashMap;

public class JokerHand extends Hand{

	public JokerHand(String hand, int bid) {
		super(hand, bid);
	}

	@Override
	int getCategory(HashMap<Character, Integer> cards) {
		//To Implement
		return 0;
	}

	@Override
	public int compareTo(Hand otherHand) {
		if(getCategory() > otherHand.getCategory()) {
			return 1;
		}else if(getCategory() < otherHand.getCategory()) {
			return -1;
		}else {
			String cardOrder = "J23456789TQKA";
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

}
