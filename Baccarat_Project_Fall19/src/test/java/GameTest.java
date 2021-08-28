import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameTest extends Application {
	
	
BaccaratGame game;

		@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
			
	}
		
	//test inilization of BaccaratGame
    @Test
    void testInit() {
    	BaccaratGame game = new BaccaratGame();
        assertEquals("BaccaratGame", game.getClass().getName(), "Failed to correctly initialize GUI");
    }

    
    //test if EvaulateWinnings is working correctly
	@Test
	void testEvalWinning() {
		
		game = new BaccaratGame();
		
		game.theBet = "Player";
		game.winner = "Player";
		game.totalWinnings = 0;
		game.currentBet = 100;
		
		double ev = game.evaluateWinnings();
		
		
		assertEquals(100, ev, "Winnings should be $100");
	}
	
	//this should lose so the winnings should be negatives
	@Test
	void testEvalWinning1() {
		
		game = new BaccaratGame();
		
		game.theBet = "Banker";
		game.winner = "Player";
		game.totalWinnings = 0;
		game.currentBet = 500;
		
		double ev = game.evaluateWinnings();
		
		
		assertEquals(-500, ev, "Winnings should be $100");
	}
	
	//test winOrLose function should return a win for player and player
	@Test
	void testWinOrLose() {
		
		game = new BaccaratGame();
		
		game.theBet = "Player";
		game.winner = "Player";
		
		String ev = game.winOrLose();
		
		
		assertEquals("win", ev, "Should be wine");
	}
	
	//return string should lose
	@Test
	void testWinOrLose1() {
		
		game = new BaccaratGame();
		
		game.theBet = "Tie";
		game.winner = "Player";
		
		String ev = game.winOrLose();
		
		
		assertEquals("lose", ev, "Should be lose");
	}
	
}
