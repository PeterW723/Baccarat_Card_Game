import static org.junit.jupiter.api.Assertions.*;

import com.sun.prism.shader.Solid_ImagePattern_Loader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.awt.geom.AreaOp;

import java.util.ArrayList;

class DealerTest {
   BaccaratDealer t;
   @BeforeEach
   void init(){
      t = new BaccaratDealer();
      t.generateDeck();
   }

   @Test
   void genDeck(){
      assertEquals(52,t.deck.size(),"Incorrect Deck generated");
   }

   @Test
   void genDeck2(){
      assertTrue(t.deck.contains(t.deck.get(0)));
   }

   @Test
   void sizeUpdate(){
      int before = t.deckSize();
      t.dealHand();
      assertEquals(before-2, t.deckSize());
   }

   @Test
   void dealHand(){
      ArrayList<Card> temp = new ArrayList<>();
      temp = t.dealHand();
      assertEquals(2,temp.size());
   }

   @Test
   void testDeckSize(){
      assertEquals(52, t.deckSize());
   }

   @Test
   void testDrawOne(){
      t.drawOne();
      assertEquals(51, t.deckSize());
   }
}