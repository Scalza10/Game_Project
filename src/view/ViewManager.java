package view;

/**
 * Required libraries for the objects used in this programs section.
 * This program section handles the scene or window where the program will run.
 * It controls things like the position of the buttons, the background and some other aesthetics.
 */

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.stage.Stage;
import model.CreditsLabel;
import model.GameButton;
import model.Subscene;
import view.GameViewManager;

/**
 * Window class that possess the attributes and method of the scene
 */

public class ViewManager {

	private static final int WIDTH = 1024;	// variable to standardize the width of the window
	private static final int HEIGHT = 768;	// variable to standardize the height of the window
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private Subscene creditsWindow;
	private Subscene scoresWindow;
	private Subscene playWindow;
	
	private final static int MENU_BUTTON_START_X = 400;		// variables to standardize the starting locations of buttons
	private final static int MENU_BUTTON_START_Y = 150;
	
	List<GameButton> menuButtons;
	/**
	   * This method initializes the window with all the buttons and characteristics.
	   */
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
		changeCursorLook();
		createNewWindows();
		
	}
	
	private void createNewWindows() {
		creditsWindow = new Subscene();
		mainPane.getChildren().add(creditsWindow);
		
		scoresWindow = new Subscene();
		mainPane.getChildren().add(scoresWindow);
		
		playWindow = new Subscene();
		mainPane.getChildren().add(playWindow);
		
	}
	/**
	   * Method to call the mainStage object.
	   * @return the mainStage object
	   */
	
	public Stage getMainStage() {
		return mainStage;
	}
	/**
	   * This method changes the appearance of the cursor for an image uploaded by the creator.
	*/
	
	private void changeCursorLook() {
		Image imageCursor = new Image("/modelos/cursorSword_silver.png");  //pass in the image path
		mainPane.setCursor(new ImageCursor(imageCursor));
	}
	
	/**
	   *This method adds the button to a list and then changes its Y coordinates so it is below the previous button.
	   *Also it adds them to the mainPane which is where everything is in the window.
	*/
	
	private void addMenuButton(GameButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY((MENU_BUTTON_START_Y) + menuButtons.size() * 135);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	/**
	   * Method to start all buttons at once
	   */
	
	private void createButtons() {
		createStartButton();
		createScoresButton();
		createCreditsButton();
		createExitButton();
	}
	
	/**
	   * Specialized methods to start each button.
	   * each button is created then inserted in an array of button objects.
	   * The string in quotations marks indicates the text that the button will contain.
	   */
	
	private void createStartButton() {
		GameButton startButton = new GameButton("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				GameViewManager gameManager = new GameViewManager();
				gameManager.createNewGame(mainStage);
				
				
				
			}
		});
	}
	
	private void createScoresButton() {
		GameButton scoresButton = new GameButton("Scores");
		addMenuButton(scoresButton);
		
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				scoresWindow.moveNewWindow();
				createLeaveButton(scoresWindow);
				
				//Try to implement a high score system.
				/*CreditsLabel label4 = new CreditsLabel(gameManager.getHighScore());
				label4.setLayoutX(100);*/
				
				
			}
		});
		
		
	}
	
	private void createCreditsButton() {
		GameButton creditButton = new GameButton("Credits");
		addMenuButton(creditButton);
		
		creditButton.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			   * This part generates the labels for the credits scene
			   */
			@Override
			public void handle(ActionEvent event) {
				creditsWindow.moveNewWindow();
				createLeaveButton(creditsWindow);
				CreditsLabel label1 = new CreditsLabel("Created by Sebastian Calzadilla");
				label1.setLayoutX(100);
				CreditsLabel label2 = new CreditsLabel("Class COP-3331-161   Term: SU1");
				label2.setLayoutX(100);
				label2.setLayoutY(label1.getLayoutY()+50);
				CreditsLabel label3 = new CreditsLabel("6/24/2020");
				label3.setLayoutX(200);
				label3.setLayoutY(label2.getLayoutY()+50);
				
				creditsWindow.getPane().getChildren().add(label1);
				creditsWindow.getPane().getChildren().add(label2);
				creditsWindow.getPane().getChildren().add(label3);
				
				//name, course number, term and date  COP-3331-161
				
			}
		});
	}
	
	/**
	   * This Method creates a special kind of button
	   * This button is created in each new subscene and makes the subscene go away.
	   * In other words, activates the effect again
	   */
	private void createLeaveButton(Subscene subscene) {
		GameButton leaveButton = new GameButton("Exit Window");
		leaveButton.setLayoutY(20);
		leaveButton.setLayoutX(20);
		subscene.getPane().getChildren().add(leaveButton);
		
		leaveButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				subscene.moveNewWindow();
				
				
			}
		});
	}
	
	/**
	   * This final button exits the program
	   */
	private void createExitButton() {
		GameButton exitButton = new GameButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
		});
	}
	
	/**
	* Method that sets the background of the window to a images inserted by the user.
	*/
	
	private void createBackground() {
		Image backgroundImage = new Image("/modelos/ui_background.jpg", 780, 1024,false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT , null);
		mainPane.setBackground(new Background(background));
	}
	
	
}
