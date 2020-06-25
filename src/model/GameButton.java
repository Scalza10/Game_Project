package model;

/**
 * Required libraries for the creation of buttons and their effects.
 */

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.effect.DropShadow;

/**
 * GameButton class that inherits properties from the Button class, it extends its capabilities.
 */

public class GameButton extends Button{
	
	// Constant string that possess the location of the background image of the button.
	// It is done this way to improve modularity of code
	
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/modelos/buttonLong_blue_pressed.png')";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/modelos/buttonLong_blue.png')";
	
	/**
	   * Creates a button.
	   * @param text: this is the text that will be displayed in the button
	   */
	public GameButton(String text) {
		
		setText(text);					// text displayed in button
		setPrefWidth(190);				// Width of button
		setPrefHeight(49);				// Height of button
		setStyle(BUTTON_FREE_STYLE);	// Starting style(aspect) of the button
		setButtonFont();				// Changes the font of the text inside the button
		initializeButton();				// Initializes the button itself.
		
	}
	
	/**
	   * Method to set the font of the text inside the button.
	*/
	private void setButtonFont() {
		setFont(Font.font("Verdana", 23));
	}
	
	/**
	   * Method to set the style of button when pressed.
	   * The button background will change when it is pressed therefore it needs two states.
	*/
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	
	/**
	   * This is the other style or bacground.
	*/
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}
	
	/**
	   * Method to initialize the functions of the buttons and what happens when different
	   *  mouse related events occur.
	*/
	
	private void initializeButton() {
		
		/**
		   * When the button is pressed the pressed style method will be called.
		   * @param The mouse event, in this case being clicked on top of the button.
		*/
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
			
		});
		
		/**
		   * When the button is released the released style method will be called.
		   * @param The mouse event, in this case released after being clicked on top of the button.
		*/		
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
			
			
		});
		
		/**
		   * When the mouse hover over the button this method is called which creates a shadow effect on the button.
		   * @param The mouse event, in this case the mouse hovers on top of the button.
		*/
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		/**
		   * When the mouse is outside the button limits this method is called. THis method clears all effects on the button.
		   * @param The mouse event, in this case the mouse is not on top of the button.
		*/
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
		
	}
	
}
