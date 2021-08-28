import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameLogicTest {
	
	BaccaratGameLogic gameLogic;
	
	@BeforeEach
	void init() {
		gameLogic = new BaccaratGameLogic(); 	
	}
	
	
    @Test
    void testInit() {
        gameLogic = new BaccaratGameLogic();
        assertEquals("BaccaratGameLogic", gameLogic.getClass().getName(), "Failed to correctly initialize game logic");
    }
	
	//testing banker win
	@Test
	void testWhoWon() {
		
		ArrayList<Card> Phand = new ArrayList<>();
		ArrayList<Card> Bhand = new ArrayList<>();
		
        Phand.add(new Card("Diamonds", 1));
        Phand.add(new Card("Diamonds", 10));
        
        Bhand.add(new Card("Clubs", 8));
        Bhand.add(new Card("Spades", 10));
		
		
		String winner = gameLogic.whoWon(Phand, Bhand);
		
		assertEquals("Banker", winner);
	}
	
	//testing tie
	@Test
	void testWhoWon1() {
		
		ArrayList<Card> Phand = new ArrayList<>();
		ArrayList<Card> Bhand = new ArrayList<>();
		
        Phand.add(new Card("Diamonds", 8));
        Phand.add(new Card("Diamonds", 10));
        
        Bhand.add(new Card("Clubs", 8));
        Bhand.add(new Card("Spades", 10));
		
		
		String winner = gameLogic.whoWon(Phand, Bhand);
		
		
		
		assertEquals("Draw", winner);
	}
	
	//testing player win
	@Test
	void testWhoWon2() {
		
		ArrayList<Card> Phand = new ArrayList<>();
		ArrayList<Card> Bhand = new ArrayList<>();
		
        Phand.add(new Card("Diamonds", 8));
        Phand.add(new Card("Diamonds", 10));
        
        Bhand.add(new Card("Clubs", 1));
        Bhand.add(new Card("Spades", 10));
		
		
		String winner = gameLogic.whoWon(Phand, Bhand);
		
		
		
		assertEquals("Player", winner);
	}
	
	//In Baccarat 9 + 9 = 18, remove the 1 so we get 8
	@Test
	void testHandTotal() {
		
		ArrayList<Card> Bhand = new ArrayList<>();
		
		
        Bhand.add(new Card("Diamonds", 9));
        Bhand.add(new Card("Spades", 9));
        
		int handTotal = gameLogic.handTotal(Bhand);
		
		assertEquals(8, handTotal);
		
		
		
	}
	
	//In Baccarat 12 + 1 will equal 1
	@Test
	void testHandTotal1() {
		
		ArrayList<Card> Bhand = new ArrayList<>();
		
		
        Bhand.add(new Card("Spades", 1));
        Bhand.add(new Card("Spades", 12));
        
		int handTotal = gameLogic.handTotal(Bhand);
		
		assertEquals(1, handTotal);
		
		
	}
	
	//banker hand total is 1 so he should draw so true is returned
	@Test
	void testEvaluateBankerDraw() {
		
        ArrayList<Card> hand = new ArrayList<>();
        
        Card playerCard = new Card("Spades", 8);
        
        hand.add(new Card("Spades", 1));
        hand.add(new Card("Spades", 10));
        
        assertTrue(gameLogic.evaluateBankerDraw(hand, playerCard));
	}
	
	//should return false cause banker hand total is 8 and he doesnt draw
	@Test
	void testEvaluateBankerDraw1() {
		
		ArrayList<Card> hand = new ArrayList<>();
        
        Card playerCard = new Card("Spades", 8);
        
        hand.add(new Card("Spades", 10));
        hand.add(new Card("Spades", 8));
        
        assertFalse(gameLogic.evaluateBankerDraw(hand, playerCard));
        
	}
	
	//testing player draw
	@Test
	void testEvaluatePlayerDraw() {
		
		ArrayList<Card> hand = new ArrayList<>();
        
        hand.add(new Card("Diamond", 10));
        hand.add(new Card("Spades", 10));
        
        assertTrue(gameLogic.evaluatePlayerDraw(hand));
        
	}
	
	//testing player draw
	@Test
	void testEvaluatePlayerDraw1() {
		ArrayList<Card> hand = new ArrayList<>();
         
        hand.add(new Card("Spades", 10));
        hand.add(new Card("Spades", 8));
        
        assertFalse(gameLogic.evaluatePlayerDraw(hand));
	}
	
	
	
	
	
}
