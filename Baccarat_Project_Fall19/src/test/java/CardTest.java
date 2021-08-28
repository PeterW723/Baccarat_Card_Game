
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardTest {

    Card tester;

    @BeforeEach
    void init() {
        tester = new Card("Spades", 5);
    }

    @Test
    void initVal(){
        assertEquals(5,tester.getValue(), "Did not initialize correctly");
    }

    @Test
    void initSuite(){
        assertEquals("Spades", tester.getString(), "Did not Initialize correct suite");
    }

}