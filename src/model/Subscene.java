package model;


import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

//Class that extends SubScene to give more functionalities.
public class Subscene extends SubScene {
	
	private final static String BACKGROUND_IMAGE = "/modelos/panel_blue.png";
	
	private boolean isHidden;
	
	//Constructor
	public Subscene () {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		
		//This portion generates the background of the new subscene
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane newWindow = (AnchorPane) this.getRoot();
		
		newWindow.setBackground(new Background(image));
		
		isHidden = true;
		
		setLayoutX(1024);
		setLayoutY(150);
	}

	//method to create an effect that moves the window
	public void moveNewWindow() {
		TranslateTransition effect = new TranslateTransition();
		effect.setDuration(Duration.seconds(0.3));
		effect.setNode(this);
		
		if(isHidden) {
			
		effect.setToX(-800);
		isHidden = false;
		}
		else {
			effect.setToX(0);
			isHidden = true;
		}
		effect.play();
		
		
	}
	
	// Method to gather the pane for future use.
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	
}
