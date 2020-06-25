package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;


/**
 * This is the main Application for the project. It is the mainframe that will activete the 
 * creation of windowns and the start of the program. It stars with a try statement so if there is any errors
 * they will be catch and handled, so the program doesn't just crash.
 * 
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}