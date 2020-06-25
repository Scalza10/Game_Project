package model;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;


public class CreditsLabel  extends Label{
	
	/**
	   * Contructor that creates a label with some special characteristics.
	   */
	public CreditsLabel(String label) {
		setPrefWidth(600);						//Definedwidth for label
		setPrefHeight(400);						//Defined height for label
		setPadding(new Insets(40,40,40,40));	//Adds padding
		setText(label);							//Sets the text of the label
		setWrapText(true);						//Enables text wrapping for space optimization
		setFont(Font.font("Verdana", 23));		//Sets the font for the label
	}	

}
