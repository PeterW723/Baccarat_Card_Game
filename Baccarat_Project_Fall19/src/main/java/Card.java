
public class Card {
	
	String suite; //Diamonds, Spades, Hearts, or Clubs
	int value; //1(A) 2 3 4 5 6 7 8 9 10 11(J) 12(Q) 13(K)
	
	
     /**
    * Parametrized Constructor used to set the values og the suite and the value of the card
    * @param theSuite
    * @param theValue
    */
	public Card(String theSuite, int theValue){
		this.suite = theSuite;
		this.value = theValue;
	}
	
	
    /**
    * This Method is used to return the value of the card
    * @return
    */
	public int getValue() {
		return this.value;
	}
	
    /**
    * This method is the getter to return the suite of the card
    * @return
    */
	public String getString( ) {
		return this.suite;
	}
	
}
