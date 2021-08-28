import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class BaccaratGame extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}
	
	
	MenuBar menuBar;
	double orgSceneX, orgSceneY,orgTranslateX, orgTranslateY;
	double offsetX,offsetY,newTranslateX, newTranslateY;
	
	private ArrayList<Card> playerHand; //array list to which will take from deck to form player's hand
	private ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;
	double currentBet = 0;
	double totalWinnings; //track total winnings
	
	String theBet = ""; //Track who the player bet on
	String winner = ""; //track winner

	//START of GUI Code
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Let's Play Baccarat!!!");
		
		
		//textfields
		TextField currBet = new TextField("Please enter a bid"); //CurrentBet
		TextField totalWon = new TextField(""); //track double totalWinnings
		TextField pTotal = new TextField(""); //Player hand Total
		TextField bTotal = new TextField(""); //Banker hand total
		TextArea genOutput = new TextArea(""); //used for general outputs like "Player Won"
		
		//make the buttons
		Button play = new Button("Play");
		Button player = new Button("Player");
		Button banker = new Button("Banker");
		Button tie = new Button("Tie");
		
		//creates labels for text display
		//Label bid = new Label("Bid");
		Label winn = new Label("Total Winnings");
		Label playerLabel = new Label("Player Hand Total");
		Label bankerLabel = new Label("Banker Hand Total");
		
		//menu stuff
		menuBar = new MenuBar();
		Menu menuOne = new Menu("Options");
		MenuItem one = new MenuItem("Fresh Start");
		MenuItem two = new MenuItem("Exit");
		menuOne.getItems().addAll(one,two);
		menuBar.getMenus().addAll(menuOne);
	
		//takes input image for player hand
		Image pic = new Image("/JPEG/Blank.jpg");
		Image pic1 = new Image("/JPEG/Blank.jpg");
		Image pic2 = new Image("/JPEG/Blank.jpg");
		//take input image for banker hand
		Image pic3 = new Image("/JPEG/Blank.jpg");
		Image pic4 = new Image("/JPEG/Blank.jpg");
		Image pic5 = new Image("/JPEG/Blank.jpg");
		
		//image display for player hand
		ImageView v1 = new ImageView(pic);
		ImageView v2 = new ImageView(pic1);
		ImageView v3 = new ImageView(pic2);
		//image display for banker hand
		ImageView v4 = new ImageView(pic3);
		ImageView v5 = new ImageView(pic4);
		ImageView v6 = new ImageView(pic5);
		
		
		//initizile the dealer and hand totals
		int playerHandTotal = 0;
		int bankerHandTotal = 0;
		theDealer = new BaccaratDealer();
		
	
		//disable buttons for the start
		player.setDisable(true);
		banker.setDisable(true);
		tie.setDisable(true);
		currBet.setDisable(true);
		
		//play button to start game and reset deck between rounds
		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				currentBet = 0;
				currBet.setDisable(false);
				
				player.setDisable(false);
				banker.setDisable(false);
				tie.setDisable(false);
				
				currBet.setText("");
				pTotal.setText("");
				bTotal.setText("");
				genOutput.setText("");
				
				
				theDealer.generateDeck();
				theDealer.shuffleDeck();
				playerHand = theDealer.dealHand();
				bankerHand = theDealer.dealHand();
				
				
				gameLogic = new BaccaratGameLogic();
				int playerHandTotal = gameLogic.handTotal(playerHand);
				int bankerHandTotal = gameLogic.handTotal(bankerHand);
				winner = gameLogic.whoWon(playerHand, bankerHand);
				
				
			}
		});	

		

		 //resets everything
		one.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				
				totalWinnings = 0;
				totalWon.setText("");
				
				currentBet = 0;
				currBet.setText("");
				
				pTotal.setText("");
				bTotal.setText("");
				genOutput.setText("");
				
				player.setDisable(true);
				banker.setDisable(true);
				tie.setDisable(true);
				currBet.setDisable(true);
				
				v1.setImage( new Image("/JPEG/Blank.jpg"));
				v2.setImage( new Image("/JPEG/Blank.jpg"));
				v3.setImage( new Image("/JPEG/Blank.jpg"));
				v4.setImage( new Image("/JPEG/Blank.jpg"));
				v5.setImage( new Image("/JPEG/Blank.jpg"));
				v6.setImage( new Image("/JPEG/Blank.jpg"));
				
				
			}
		});
		
		 //menu option to exit game
		two.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.exit(0);
				
			}
		});
		
		
		//player action button
		player.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				//take bid 
				double bidd = Integer.parseInt(currBet.getText());
				currentBet = bidd;
				
				//track player's bet
				theBet = "Player";
				
				//set the third cards in hand back to blank if they were dealt last round
				v3.setImage( new Image("/JPEG/Blank.jpg"));
				v6.setImage( new Image("/JPEG/Blank.jpg"));

				
				//check if there is a valid bid
				if(currentBet != 0 ) {
					
				//display hand with images in GUI
				v1.setImage( new Image("/JPEG/"+playerHand.get(0).getValue()+playerHand.get(0).getString()+".jpg"));
				v2.setImage( new Image("/JPEG/"+playerHand.get(1).getValue()+playerHand.get(1).getString()+".jpg"));
				v4.setImage( new Image("/JPEG/"+bankerHand.get(0).getValue()+bankerHand.get(0).getString()+".jpg"));
				v5.setImage( new Image("/JPEG/"+bankerHand.get(1).getValue()+bankerHand.get(1).getString()+".jpg"));
				
				//disable other bet buttons
				banker.setDisable(true);
				tie.setDisable(true);
				
				//display handtotal
				pTotal.setText("" + gameLogic.handTotal(playerHand));
				bTotal.setText("" + gameLogic.handTotal(bankerHand));
				
				//make a null card
				Card drawCard = null;
				
				//if evaluatePD returns true delays for 2 seconds and deal 3rd card
				if(gameLogic.evaluatePlayerDraw(playerHand)) {
					drawCard = theDealer.drawOne();
					playerHand.add(drawCard);
					final int playerHandTotal = gameLogic.handTotal(playerHand);
					
					PauseTransition delay = new PauseTransition(Duration.millis(2000));
					
					
					delay.setOnFinished(handle -> {
						v3.setImage( new Image("/JPEG/"+playerHand.get(2).getValue()+playerHand.get(2).getString()+".jpg"));
						pTotal.appendText("->" + playerHandTotal);
					});
					delay.play();
					
					
					;
					//if evaluateBD returns true delays for 2 seconds and deal 3rd card for banker

					if(gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(2))) {
						bankerHand.add(drawCard);
						final int bankerHandTotal = gameLogic.handTotal(bankerHand);
						
						delay.setOnFinished(handle -> {
							v6.setImage( new Image("/JPEG/"+bankerHand.get(2).getValue()+bankerHand.get(2).getString()+".jpg"));
							bTotal.appendText("->" + bankerHandTotal);
						});
						delay.play();
						
					}
				}
				PauseTransition delay = new PauseTransition(Duration.millis(3000));
				
				double winnings = evaluateWinnings();
				
				//after hands have been dealt and declares winner and updates total winning // 3 second delay
				delay.setOnFinished((handle) -> {
					totalWon.setText("" + winnings);
					genOutput.setText(winner+" wins! \r\n You bet " + theBet + "\r\n You " + winOrLose() + " $ " +  currentBet);
					});
				delay.play();
				
				
				}
				
				else {
					currBet.setText("Please enter a bid!!!!");
				}
				;
				
				
				

			}
		});	//////////////////////////////////////////////////////////////end Player Button
		
		
		//Banker action button
		banker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//take bid 
				double bidd = Integer.parseInt(currBet.getText());
				currentBet = bidd;
				
				int firstCardVal = playerHand.get(0).getValue();
				String firstCardSuite = playerHand.get(0).getString();
				theBet = "Banker";
			
				v3.setImage( new Image("/JPEG/Blank.jpg"));
				v6.setImage( new Image("/JPEG/Blank.jpg"));
				
				//check if there is a valid bid
				if(currentBet != 0 ) {
				v1.setImage( new Image("/JPEG/"+firstCardVal+firstCardSuite+".jpg"));
				v2.setImage( new Image("/JPEG/"+playerHand.get(1).getValue()+playerHand.get(1).getString()+".jpg"));
				v4.setImage( new Image("/JPEG/"+bankerHand.get(0).getValue()+bankerHand.get(0).getString()+".jpg"));
				v5.setImage( new Image("/JPEG/"+bankerHand.get(1).getValue()+bankerHand.get(1).getString()+".jpg"));
				player.setDisable(true);
				tie.setDisable(true);
				pTotal.setText("" + gameLogic.handTotal(playerHand));
				bTotal.setText("" + gameLogic.handTotal(bankerHand));
				
				//make a null card
				Card drawCard = null;
				
				//if evaluatePD returns true delays for 2 seconds and deal 3rd card
				if(gameLogic.evaluatePlayerDraw(playerHand)) {
					drawCard = theDealer.drawOne();
					playerHand.add(drawCard);
					final int playerHandTotal = gameLogic.handTotal(playerHand);
					PauseTransition delay = new PauseTransition(Duration.millis(2000));
					
					delay.setOnFinished(handle -> {
						v3.setImage( new Image("/JPEG/"+playerHand.get(2).getValue()+playerHand.get(2).getString()+".jpg"));
						pTotal.appendText("->" + playerHandTotal);
					});
					delay.play();
					
					
					;
					
					if(gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(2))) {
						bankerHand.add(drawCard);
						final int bankerHandTotal = gameLogic.handTotal(bankerHand);
						
						//after hands have been dealt and declares winner and updates total winning // 2 second delay
						delay.setOnFinished(handle -> {
							v6.setImage( new Image("/JPEG/"+bankerHand.get(2).getValue()+bankerHand.get(2).getString()+".jpg"));
							bTotal.appendText("->" + bankerHandTotal);
						});
						delay.play();
						
					}
				}
				PauseTransition delay = new PauseTransition(Duration.millis(3000));
				
				double winnings = evaluateWinnings();

				delay.setOnFinished((handle) -> {
					totalWon.setText("" + winnings);
					genOutput.setText(winner+" wins! \r\n You bet " + theBet + "\r\n You " + winOrLose() + " $ " +  currentBet);
					});
				delay.play();
				
				
				}
				else {
					currBet.setText("Please enter a bid!!!!");
				}
				;
				
				
				

			}
		});	///////////////////////////////////////////////////////////////end Banker Button
		
		tie.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				//take bid 
				double bidd = Integer.parseInt(currBet.getText());
				currentBet = bidd;
				
				int firstCardVal = playerHand.get(0).getValue();
				String firstCardSuite = playerHand.get(0).getString();
				theBet = "Tie";
			
				v3.setImage( new Image("/JPEG/Blank.jpg"));
				v6.setImage( new Image("/JPEG/Blank.jpg"));
				
				//check if there is a valid bid
				if(currentBet != 0 ) {
				v1.setImage( new Image("/JPEG/"+firstCardVal+firstCardSuite+".jpg"));
				v2.setImage( new Image("/JPEG/"+playerHand.get(1).getValue()+playerHand.get(1).getString()+".jpg"));
				v4.setImage( new Image("/JPEG/"+bankerHand.get(0).getValue()+bankerHand.get(0).getString()+".jpg"));
				v5.setImage( new Image("/JPEG/"+bankerHand.get(1).getValue()+bankerHand.get(1).getString()+".jpg"));
				
				//disable other bet buttons
				player.setDisable(true);
				banker.setDisable(true);
				
				pTotal.setText("" + gameLogic.handTotal(playerHand));
				bTotal.setText("" + gameLogic.handTotal(bankerHand));
				
				Card drawCard = null;
				if(gameLogic.evaluatePlayerDraw(playerHand)) {
					drawCard = theDealer.drawOne();
					playerHand.add(drawCard);
					final int playerHandTotal = gameLogic.handTotal(playerHand);
					PauseTransition delay = new PauseTransition(Duration.millis(2000));
					
					delay.setOnFinished(handle -> {
						v3.setImage( new Image("/JPEG/"+playerHand.get(2).getValue()+playerHand.get(2).getString()+".jpg"));
						pTotal.appendText("->" + playerHandTotal);
					});
					delay.play();
					
					
					;
					
					if(gameLogic.evaluateBankerDraw(bankerHand, playerHand.get(2))) {
						bankerHand.add(drawCard);
						final int bankerHandTotal = gameLogic.handTotal(bankerHand);
						
						delay.setOnFinished(handle -> {
							v6.setImage( new Image("/JPEG/"+bankerHand.get(2).getValue()+bankerHand.get(2).getString()+".jpg"));
							bTotal.appendText("->" + bankerHandTotal);
						});
						delay.play();
						
					}
				}
				PauseTransition delay = new PauseTransition(Duration.millis(3000));
				
				double winnings = evaluateWinnings();

				delay.setOnFinished((handle) -> {
					totalWon.setText("" + winnings);
					genOutput.setText(winner+" wins! \r\n You bet " + theBet + "\r\n You " + winOrLose() + " $ " +  currentBet);
					});
				delay.play();
				
				
				}
				else {
					currBet.setText("Please enter a bid!!!!");
				}
				;
				
				
				

			}
		});	//////////////////////////////////////////////////////////end Tie Button
		
		//resize certain GUI elements
		currBet.setMaxWidth(150);
		play.setPrefWidth(150);
		genOutput.setPrefHeight(70);
		
		//resize card images to fit nicely into borderpane
		v1.setFitWidth(150);
		v1.setFitHeight(150);
		v1.setPreserveRatio(true);
		v2.setFitWidth(150);
		v2.setFitHeight(150);
		v2.setPreserveRatio(true);
		v3.setFitWidth(150);
		v3.setFitHeight(150);
		v3.setPreserveRatio(true);
		v4.setFitWidth(150);
		v4.setFitHeight(150);
		v4.setPreserveRatio(true);
		v5.setFitWidth(150);
		v5.setFitHeight(150);
		v5.setPreserveRatio(true);
		v6.setFitWidth(150);
		v6.setFitHeight(150);
		v6.setPreserveRatio(true);
		

		
		//initizile gridpane and borderpane
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		
		//create Hbox for displaying player hand
		HBox playerBox = new HBox(v1, v2, v3);
		playerBox.setAlignment(Pos.CENTER); 
		playerBox.setPadding(new Insets(5, 5, 5, 25)); 
		
		//create Hbox for displaying banker hand
		HBox bankerBox = new HBox(v4,v5, v6);
		bankerBox.setAlignment(Pos.CENTER); 
		bankerBox.setPadding(new Insets(5, 25, 5, 5)); 
		
		//Hbox for player, banker and tie buttons
		HBox buttonBox = new HBox(player, banker, tie);
		
		
		
		//might need //delete when project done
		//ColumnConstraints column1 = new ColumnConstraints();
		//column1.setPercentWidth(50);
		//gp.getColumnConstraints().add(column1);
		//gp.setGridLinesVisible(true);
		//gp.setPadding(new Insets(5, 0, 5, 0)); 
		
		
		//changes grid to have correct sizing
		gp.setMinSize(1200, 900);
	    gp.setVgap(4); 
	    gp.setHgap(12);
	    gp.setAlignment(Pos.CENTER); 
		
	    //change color of grid and borderpane background
	    gp.setStyle("-fx-background-color: green");
	    bp.setStyle("-fx-background-color: green");
	    
	    //add text fields to grid
	    gp.add(currBet, 24, 90);
	    gp.add(totalWon, 50, 175);
	    gp.add(pTotal, 0, 100);
	    gp.add(bTotal, 50, 100);
	    gp.add(genOutput , 24, 174);
	    
	    //add labels to grid
	    //CHANGE FONT
	    gp.add(playerLabel,0, 99 );
	    gp.add(bankerLabel,50, 99 );
	    //gp.add(bid, 21,  90);
	    gp.add(winn, 50,  174);
	    
	    //add buttons to grid
	    gp.add(play, 24, 88);
	    gp.add(buttonBox,24, 92);

	    //change alignment to please my OCD
	    GridPane.setHalignment(play, HPos.CENTER);
	    buttonBox.setAlignment(Pos.CENTER);
	    GridPane.setHalignment(currBet, HPos.CENTER);
	    GridPane.setValignment(winn,VPos.BOTTOM);
	    
	    //add menu, grid, and player/banker hand to borderpane
	    bp.setTop(menuBar);
	    bp.setCenter(gp);
	    bp.setLeft(playerBox);
	    bp.setRight(bankerBox);
	    
		 
		Scene scene = new Scene(bp );
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();
	} /////////////END GUI CODE
	
	
	//prints win or lose depending on the player's bet
	public String winOrLose() {
		String winOrLose = "";
		if (theBet == "Player" && winner == "Player") {
			winOrLose = "win";
		}
		else if (theBet == "Player" && winner != "Player") {
			winOrLose = "lose";
		}
		else if (theBet == "Banker" && winner != "Banker") {
			winOrLose = "lose";
		}
		else if (theBet == "Banker" && winner == "Banker") {
			winOrLose = "win";
		}
		else if (theBet == "Tie" && winner != "Tie") {
			winOrLose = "lose";
		}
		else if (theBet == "Tie" && winner == "Tie") {
			winOrLose = "win";
		}
		return winOrLose;
	}
	
	//update total winnings based on player's bet and winner
	public double evaluateWinnings() {
		
		if (theBet == "Player" && winner == "Player") {
			totalWinnings = totalWinnings + currentBet;
		}
		else if (theBet == "Player" && winner != "Player") {
			totalWinnings = totalWinnings - currentBet;
		}
		else if (theBet == "Banker" && winner != "Banker") {
			totalWinnings = totalWinnings - currentBet;
		}
		else if (theBet == "Banker" && winner == "Banker") {
			totalWinnings = totalWinnings + currentBet;
		}
		else if (theBet == "Tie" && winner != "Tie") {
			totalWinnings = totalWinnings - currentBet;
		}
		else if (theBet == "Tie" && winner == "Tie") {
			totalWinnings = totalWinnings + currentBet;
		}
		return totalWinnings;
	}
	

}
