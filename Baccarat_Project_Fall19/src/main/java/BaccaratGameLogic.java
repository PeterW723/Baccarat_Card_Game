import java.util.ArrayList;

public class BaccaratGameLogic {
	
	//takes two arrayList(the hands) and gets total on both
	//the biggest sum wins
	public String whoWon(ArrayList<Card>hand1, ArrayList<Card>hand2) {
		
		String Winner = "";
		int playerHand = handTotal(hand1);
		int bankerHand = handTotal(hand2);
		
		if(playerHand == bankerHand) {
			Winner = "Draw";
		}
		else if (playerHand > bankerHand) {
			Winner = "Player";
		}
		else if (playerHand < bankerHand) {
			Winner = "Banker";
		}
		
		return Winner;
	}
	
	
	//Takes a hand array and iterates through it to find the total sum
	public int handTotal(ArrayList<Card>hand) {
		
		int handTotal = 0;
		int i = 0;
		
		//take element in Arraylist hand and add them
		while(i < hand.size()) {
			handTotal += hand.get(i).getValue();
			
			//subtract if val>10
			if(hand.get(i).getValue() == 10) {
				handTotal = handTotal - 10;
			}
			else if(hand.get(i).getValue() == 11) {
				handTotal = handTotal - 11;
			}
			else if (hand.get(i).getValue() == 12) {
				handTotal = handTotal - 12;
			}
			else if(hand.get(i).getValue() == 13) {
				handTotal = handTotal - 13;
			}
			
			
			i++;
		}
		
		//if hand > 9 then we have to just return the second number
		if (handTotal > 9) {
			handTotal = handTotal % 10;
		}
		
		
		return handTotal;
	}
	
	//logic for deciding if banker draws
	public boolean evaluateBankerDraw(ArrayList<Card>hand, Card playerCard) {
		
		
		int totalHand = handTotal(hand);
		boolean result = false;
		
		//"Base Case" if player didnt draw
		if(totalHand <=5 && playerCard == null ) {
			result = true;
		}
		
		
		int playerCardVal = playerCard.getValue();
		
		//dealer always draw if total is 2 or less
		if(totalHand <=2) {
			result = true;
		}
		//if banker total is 3 and play card isnt 8 banker draws
		else if(totalHand == 3 && playerCardVal != 8) {
			result = true;
		}
		//if banker total is 4 and play card is between 1 and 8 banker draws

		else if(totalHand == 4 && (playerCardVal > 1 && playerCardVal < 8)) {
			result = true;
		}
		//if banker total is 5 and play card between 3 and 8  banker draws

		else if(totalHand == 5 && (playerCardVal > 3 && playerCardVal < 8)) {
			result = true;
		}
		//if banker total is 6 and play total between 5 and 8 banker draws

		else if(totalHand == 6 && (playerCardVal > 5 && playerCardVal < 8)) {
			result = true;
		}
		
		
		
		return result;
	}
	
	//if player hand total is less than 6 should return true
	public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		
		int totalHand = handTotal(hand);
		boolean result = false;
		
		
		if(totalHand <= 5) {
			result = true;
		}
		
		
		return result;
	}
	
}

