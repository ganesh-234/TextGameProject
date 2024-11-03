package Pack1;

import java.util.Scanner;

import Pack1.MyScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Pack1.Ending;
import javafx.event.ActionEvent;

/*
 * Planning
 * 
 * Using switch statements instead of if statements for the choices
 * Ending determined by examining a string of integers. 
 * 
 * GUI
 * JavaFX
 * Buttons for each choice
 * Simple backgrounds
 * Text box explaining scenario.
 * 
 * Username Input
 * 
 * Idea: Create a choice class
 * Inheret the class from the main class.
 * 
 */


public class textgameMain extends Application {

		public void start(Stage stage)throws Exception {
			Parent root =
					FXMLLoader.load(getClass().getResource("Textproject_UI.fxml"));

			Scene Scene = new Scene(root);
			stage.setTitle("Text Adventure Game");
			stage.setScene(Scene);
			stage.show();
		}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}