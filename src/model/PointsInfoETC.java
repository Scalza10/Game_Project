package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class PointsInfoETC extends Label {  // New class that extends Label by adding chracteristics.
	
	public PointsInfoETC(String text) {
		setPrefWidth(130);					//Set a defined Width
		setPrefHeight(50);					//Set a defined Height
		//Adds an image for the background to set for the label
		BackgroundImage backgroundImage =new BackgroundImage(new Image("/modelos/buttonBlue.png",130,50,
				false,true),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground( new Background(backgroundImage));//sets the background
		setAlignment(Pos.CENTER_LEFT);					//Sets the alignment for the Label
		setPadding(new Insets(10,10,10,10));			//Adds padding to the label
		setText(text);									//Sets the text for the Label
	}
	

}
