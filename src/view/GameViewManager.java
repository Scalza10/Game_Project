package view;

// Requiered libraries for the program to run
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.PointsInfoETC;

// Definition of the class
public class GameViewManager {
	//Setting up multiple variables for standarized use.
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	
	private Stage menuStage;
	private ImageView player;
	
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private AnimationTimer gameTimer;
	
	private GridPane gridPane1;
	private GridPane gridPane2;
	private final static String GAME_BACKGROUND = "/modelos/darkPurple.png";
	
	private final static String COAL_IMAGE = "/modelos/ore_coal.png";
	private final static String DIAMOND_IMAGE = "/modelos/ore_diamond.png";
	private final static String GOLD_IMAGE = "/modelos/ore_gold.png";
	
	private ImageView[] coalOres; // coal falling objects
	private ImageView[] goldOres; // gold falling objects
	private ImageView[] diamondOres; // diamond falling objects
	Random randomPositionGenerator; //Position of the falling objects
	
	private PointsInfoETC pointsLabel;
	private ImageView[] playerLifes;	//Images for lives
	private int playerLife = 2;			//Number of lives
	private int points;
	private String highScore = "";
	
	// Area of effect of each game object
	
	private final static int GOLD_RADIUS = 17;
	private final static int DIAMOND_RADIUS = 16;
	private final static int COAL_RADIUS = 20;
	private final static int PLAYER_RADIUS = 27;
	
	//Constructor
	public GameViewManager() {
		initialize();
		initializeListener();
		randomPositionGenerator = new Random();
	}
	
	//Initialization of the game pane, scene, and stage.
	private void initialize() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
	}
	
	//Creating listeners to activate actions according to keys pressed.
	private void initializeListener() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = true;
					
				}
			
			else if (event.getCode() == KeyCode.RIGHT){
				isRightKeyPressed = true;
			}
				
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
			
				}
		
				else if(event.getCode() == KeyCode.RIGHT){
					isRightKeyPressed = false;
		
				}
		
			}
		});
	}
	
	//Creating new game window and hiding the menu one. Initializing all the variables
	
	public void createNewGame(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createGameBackground();
		createPlayer();
		createOres();
		createGameLoop();
		gameStage.show();
	}
	
	//Method to create objects: gold, diamond, and coal ores
	private void createOres() {
		playerLife = 2;
		
		//This section creates the Points label
		pointsLabel = new PointsInfoETC("POINTS : 00");
		pointsLabel.setLayoutX(400);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		
		//This section creates the player lives and display them into the window
		playerLifes = new ImageView[3];
		for(int i = 0; i< playerLifes.length; i++) {
			playerLifes[i] = new ImageView("/modelos/pick_diamond.png");
			playerLifes[i].setLayoutX(340 +(i*75));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
		}
		
		coalOres = new ImageView[3];
		goldOres = new ImageView[3];
		diamondOres = new ImageView[3];
		
		for(int counter = 0; counter <coalOres.length; counter++) {
			coalOres[counter] = new ImageView(COAL_IMAGE);
			setElementPosition(coalOres[counter]);
			gamePane.getChildren().add(coalOres[counter]);
		}
		
		for(int counter = 0; counter <goldOres.length; counter++) {
			goldOres[counter] = new ImageView(GOLD_IMAGE);
			setElementPosition(goldOres[counter]);
			gamePane.getChildren().add(goldOres[counter]);
		}
		
		for(int counter = 0; counter <diamondOres.length; counter++) {
			diamondOres[counter] = new ImageView(DIAMOND_IMAGE);
			setElementPosition(diamondOres[counter]);
			gamePane.getChildren().add(diamondOres[counter]);
		}
		
	}
	
	private void setElementPosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(370));
		image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
	}
	
	private void moveElemets() {
		for(int counter = 0; counter< coalOres.length; counter++) {
			coalOres[counter].setLayoutY(coalOres[counter].getLayoutY() + 7);
			coalOres[counter].setRotate(coalOres[counter].getRotate() + 4);
		}
		
		for(int counter = 0; counter< goldOres.length; counter++) {
			goldOres[counter].setLayoutY(goldOres[counter].getLayoutY() + 7);
			goldOres[counter].setRotate(goldOres[counter].getRotate() + 4);
		}
		
		for(int counter = 0; counter< diamondOres.length; counter++) {
			diamondOres[counter].setLayoutY(diamondOres[counter].getLayoutY() + 7);
			diamondOres[counter].setRotate(diamondOres[counter].getRotate() + 4);
		}
	}
	// method to check if the elements left the screen and restarts them
	private void checkIfElementLeftScreen() {
		for(int counter = 0; counter < coalOres.length; counter++) {
			if(coalOres[counter].getLayoutY() > 900) {
				setElementPosition(coalOres[counter]);
			}	
		}
		
		for(int counter = 0; counter < goldOres.length; counter++) {
			if(goldOres[counter].getLayoutY() > 900) {
				setElementPosition(goldOres[counter]);
			}	
		}
		
		for(int counter = 0; counter < diamondOres.length; counter++) {
			if(diamondOres[counter].getLayoutY() > 900) {
				setElementPosition(diamondOres[counter]);
			}	
		}
	}
	
	
	//Initializes the player 
	private void createPlayer() {
		player = new ImageView("/modelos/minecart.png");
		player.setLayoutX(GAME_WIDTH/2);
		player.setLayoutY(GAME_HEIGHT - 110);
		gamePane.getChildren().add(player);
	}
	
	//Game loop that constantly checks for collisions, the movement of the background, movement of player and if elements lef the screen.
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				moveBackground();
				moveElemets();
				checkIfElementLeftScreen();
				collisionLogic();
				movePlayer();
			
		}
		
	};
	
	gameTimer.start();
	
	}
	
	//Method to move the player based on key movement and boundaries.
	
	private void movePlayer() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(player.getLayoutX() > -20) {
				player.setLayoutX(player.getLayoutX() - 4);
			}
		}
		
		if(isRightKeyPressed && !isLeftKeyPressed) {
			if(player.getLayoutX() < 490) {
				player.setLayoutX(player.getLayoutX() + 4);
			}
		
		}
		
		if(!isRightKeyPressed && !isLeftKeyPressed) {
			
		}
		
		if(isRightKeyPressed && isLeftKeyPressed) {
			
		}


	}
	
	//method that initializes the background.
	private void createGameBackground() {
		gridPane1= new GridPane();
		gridPane2= new GridPane();
		
		for( int counter = 0; counter < 12; counter++) {
			ImageView gamebackgroundImage1 = new ImageView(GAME_BACKGROUND);
			ImageView gamebackgroundImage2 = new ImageView(GAME_BACKGROUND);
			GridPane.setConstraints(gamebackgroundImage1, counter % 3, counter / 3);
			GridPane.setConstraints(gamebackgroundImage2, counter % 3, counter / 3);
			gridPane1.getChildren().add(gamebackgroundImage1);
			gridPane2.getChildren().add(gamebackgroundImage2);
		}
		gridPane1.setLayoutY(0);
		gridPane2.setLayoutY(-1024);
		
		gamePane.getChildren().addAll(gridPane1, gridPane2);
	}
	
	//Method to move the background and creates a nice effect
	private void moveBackground() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);
		
		if(gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		
		if(gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}
	
	//Method that performs operation based on what the player hits during the game.
	private void collisionLogic() {
		for(int i = 0; i < goldOres.length; i++) {
		if( PLAYER_RADIUS + GOLD_RADIUS > calculateDistance(player.getLayoutX() + 49, goldOres[i].getLayoutX() + 15, player.getLayoutY() +37, goldOres[i].getLayoutY()+15)) {
			setElementPosition(goldOres[i]);
			
			points++;
			String textToSet = "Points : ";
			if(points<10) {
				textToSet = textToSet + "0";
			}
			
			pointsLabel.setText(textToSet + points);
			}
	}
		//for loop that adds points to the player
		
		for(int i = 0; i < diamondOres.length; i++) {
			if( PLAYER_RADIUS + DIAMOND_RADIUS > calculateDistance(player.getLayoutX() + 49, diamondOres[i].getLayoutX() + 15, player.getLayoutY() +37, diamondOres[i].getLayoutY()+15)) {
				setElementPosition(goldOres[i]);
				
				points += 5;
				String textToSet = "Points : ";
				if(points<10) {
					textToSet = textToSet + "0";
				}
				
				pointsLabel.setText(textToSet + points);}
		}
		//this loop reduces the lives of the player 
		for(int i = 0; i <coalOres.length; i++) {
			if(PLAYER_RADIUS + COAL_RADIUS > calculateDistance(player.getLayoutX() + 49, coalOres[i].getLayoutX() + 20, player.getLayoutY()+ 37, coalOres[i].getLayoutY() + 20)) {
			removeLife();
			setElementPosition(coalOres[i]);}
		}
	}
	
	//Method to reduce lives if a coal ore is hit
	
	private void removeLife() {
		gamePane.getChildren().remove(playerLifes[playerLife]);
		playerLife--;
		if(playerLife < 0) {
			 
			gameStage.close();
			highScore = String.valueOf(points);
			gameTimer.stop();
			menuStage.show();
			
		}
	}
	/**
	   * Method to calculate the distance between an object and the player.
	   * @return the Distance
	   */
	private double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	//Trying to set up a highscore system to save the highest score.
	
	/*public String getHighScore() {
		FileReader readFile = null;
		BufferedReader reader = null;
		try {
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		catch (Exception e) {
			return "0";
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void CheckScore() {
		if(points > Integer.parseInt(highScore)) {
			String name =JOptionPane.showInputDialog("You set a new highscore. What is your name?");
			highScore = name + ":" + points;
			
			File scoreFile = new File("highscore.dat");
			if (!scoreFile.exists()) {
				try {
					scoreFile.createNewFile();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			
			FileWriter writeFile =null;
			BufferedWriter writer =null;
			
			try {
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highScore);
			}
			catch(Exception e) {
				//errors
			}
			finally {
				try {
					if(writer != null) {
						writer.close();
					}
				}
			}
		
			
			
		}
	}*/
	

}
