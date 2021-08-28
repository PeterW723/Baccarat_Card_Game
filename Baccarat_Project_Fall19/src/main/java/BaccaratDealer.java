import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class BaccaratDealer {
    ArrayList<Card> deck ;
    String[] suites = {"Spades", "Hearts", "Clubs", "Diamonds"};
    int deckSize = 52;
    
    
    /**
     * This method is used to Generate a deck of 52 cards
     */
    public void generateDeck(){
    	deck = new ArrayList<Card>();
    	
        for(int i=0;i<4;i++){
            for(int j=1;j<14;j++){
            	deck.add(new Card(suites[i], j));
          
            }
        }
    }
    
    /**
     * This method is used to Draw 2 cards when called
     * @returns an ArrayList containing the two cards that are drawn by the method
     */
    public ArrayList<Card> dealHand(){
    	
        ArrayList<Card> result = new ArrayList<>();
        
        result.add(drawOne());
        result.add(drawOne());

        return result;
       
    }
    
    
    /**
     * This card is used to Draw a single card when called
     * @return a Card That is drawn
     */
    public Card drawOne(){

    	deckSize--;
    	return deck.remove(0);
    }

    
    /**
     * This method is used to create a new deck of cards and then shuffles the new cards
     */
    public void shuffleDeck(){
        ArrayList<Card> newDeck = new ArrayList<>();
        for(int i=0;i<4;i++){
            for(int j=1;j<14;j++){
                newDeck.add(new Card(suites[i], j));
            }
        }
        deck = newDeck;
        deckSize = 52;
        Collections.shuffle(deck);
    }

    
    /**
     * This method calculates the number of cards at any moment in the deck
     * @returns deck size
     */
    public int deckSize(){
      return deckSize;
    }

}
