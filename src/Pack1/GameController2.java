package Pack1;
/**
 * Sample Skeleton for 'Textproject_UI.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameController2 {

    @FXML // fx:id="topText"
    private Text topText; // Value injected by FXMLLoader

    @FXML // fx:id="Button1"
    private Button Button1; // Value injected by FXMLLoader

    @FXML // fx:id="Button2"
    private Button Button2; // Value injected by FXMLLoader

    @FXML // fx:id="item1Text"
    private Text item1Text; // Value injected by FXMLLoader

    @FXML // fx:id="item2Text"
    private Text item2Text; // Value injected by FXMLLoader

    @FXML // fx:id="item3Text"
    private Text item3Text; // Value injected by FXMLLoader

    @FXML // fx:id="ScoreboardButton"
    private Button ScoreboardButton; // Value injected by FXMLLoader

    @FXML // fx:id="item1Image"
    private ImageView item1Image; // Value injected by FXMLLoader

    @FXML // fx:id="item2Image"
    private ImageView item2Image; // Value injected by FXMLLoader

    @FXML // fx:id="item3Image"
    private ImageView item3Image; // Value injected by FXMLLoader
    
    
    private String playerString = "";

    private Map<String, Runnable> choiceActions = new HashMap<>();
    
    private static Scoreboard scoreboard;

    static long startTime = System.currentTimeMillis();
    
    private boolean[] EndingArray= new boolean[4];
    @FXML
    public void initialize() {
       
    	scoreboard = new Scoreboard();
    	
        configureGameChoices();
        displayWelcomeMessage();
    }
   
    public static void startGame() {
        System.out.println("Welcome to the Text Adventure Game!");

        // Player's adventure string
        String playerString="";



        
        int choiceNum = 0;
        // Start the adventure
        explore(playerString);

        
    }

    public static void explore(String playerString) {     
        
        MyScene scene = new MyScene("You are lost in an enchanted forest, and there's a fork in the road before you.", "1. Go left", "2. Go Right", 0);
      
        
        System.out.println(scene.getDescription());
        // Display choice description
        System.out.println(scene.getChoice1Description());
        
        System.out.println(scene.getChoice2Description());

        int choice = getUserChoice(2);

        // Record the player's choice in the adventure string
        playerString=playerString+choice;

        if (choice == 1) {
            // Scenario for going left
        	
            // Create a scene for going left
            scene = new MyScene("You go left and encounter a mysterious creature.",
                              "1. Approach the creature", "2. Ignore the creature and continue", 1);
            
	        System.out.println(scene.getDescription());

	        // Display choice description
	        System.out.println(scene.getChoice1Description());
	        
	        System.out.println(scene.getChoice2Description());
	        
            choice = getUserChoice(2);
            
	        playerString=playerString+choice;
        } else {
            // Scenario for going right
            scene = new MyScene("You go and encounter a hidden cave.",
                    "1. Approach the creature", "2. Walk past the cave", 2);

	        System.out.println(scene.getDescription());

	        // Display choice description
	        System.out.println(scene.getChoice1Description());
	        
	        System.out.println(scene.getChoice2Description());
      
            choice = getUserChoice(2);

	        playerString=playerString+choice;
            if (choice == 1) {
                System.out.println("You enter the cave and discover a treasure chest. Your adventure continues!");
                System.out.println(playerString);
                determineEndingTime(playerString);
                // You can continue the story based on the player's choice.
            } else {
                System.out.println("You decide not to enter the cave and continue your journey.");
                System.out.println(playerString);
                determineEndingTime(playerString);
                // You can continue the story based on the player's choice.
            }
        }
    }

    
    
    public static int getUserChoice(int maxChoice) {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.print("Enter your choice (1-" + maxChoice + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
            }
            choice = scanner.nextInt();
        } while (choice < 1 || choice > maxChoice);

        return choice;
    }
   
    
    private void configureGameChoices() {
        Button1.setOnAction(e -> handleChoice(1));
        Button2.setOnAction(e -> handleChoice(2));
    }
    
    private void displayWelcomeMessage() {
        topText.setText("You are lost in an enchanted forest, and there's a fork in the road before you.");
	Button1.setText("Go Left");
	Button2.setText("Go Right");

    }

    private void startNewGame() {
    	startTime = System.currentTimeMillis();
        playerString = ""; // Reset the player 
        System.out.println(playerString);
        configureGameChoices();
        displayWelcomeMessage();
        GoldenEnding();
    }

    private void handleChoice(int choice) {
        // Append the choice
        playerString += choice;
        
        Ending ending;
        
        
        switch (playerString) {
            case "1":
                topText.setText("You go left and encounter a mysterious creature."); 
                Button1.setText("Approach the creature");
                Button2.setText("Ignore the creature and continue");
                System.out.println(playerString);
                break;
            case "11":
                determineEndingTime("11");
                handleEnding("11");
                System.out.println(playerString);
                break;
            case "12":
                determineEndingTime("12");
                handleEnding("12");
                System.out.println(playerString);
                break;
            case "2":
                topText.setText("You go and encounter a hidden cave.");
                Button1.setText("Explore the Cave"); 
                Button2.setText("Walk past the cave");
                System.out.println(playerString);
                break;
            case "21":
            	determineEndingTime("21");
            	handleEnding("21");
                System.out.println(playerString);
            	break;
            case "22":
        		ending = new Ending("22", "You have reached Ending 4", "restart", "quit" );
        		topText.setText(ending.getDescription());
            	determineEndingTime("22");
            	handleEnding("22");
                System.out.println(playerString);
            	break;
            //we can add more cases 
        }

    }
    public static void determineEndingTime(String playerString) {
        // Example of determining ending based on the player's adventure string
        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
    	


        scoreboard.addEntry(new AdventureEntry(elapsedMinutes, elapsedSeconds));

        System.out.println("Your adventure lasted for " + elapsedMinutes + " minutes and " + secondsDisplay + " seconds");
        
        scoreboard.display();

    }
    
    public void DisplayScore() {
    	
    	scoreboard.display();
        topText.setText(scoreboard.toString());
    }
    
    public void GoldenEnding() {
    	boolean goldenending = false;
    	
    	for (boolean value : EndingArray) {
    	    // If any value is false, set GoldenEnding to false and break out of the loop
    	    if (value==false) {
    	        goldenending = false;
    	        break;
    	    }
    	    else goldenending = true;
    	}
    	 if (goldenending == true) {
 	    	System.out.println("You have reached the golden ending. Thanks for playing.");
 	    	topText.setText("You have reached the golden ending. Thanks for playing.");
    	 }
    	
    }
    
    public void handleEnding(String playerString) {
    	Ending ending;
    	
    	switch(playerString) {
        case "11":
        	ending = new Ending("11", "You have reached Ending 1", "restart", "quit" );
        	topText.setText(ending.getDescription());
            EndingArray[0] = true;
            System.out.println(EndingArray[0]);
        	break;
    	case "12": 
    		ending = new Ending("12", "You have reached Ending 2", "restart", "quit" );
    		System.out.println(ending.getDescription());
        	topText.setText(ending.getDescription());
            EndingArray[1] = true;
            System.out.println(EndingArray[1]);
    		break;
    	case "21":
			ending = new Ending("21", "You have reached Ending 3", "restart", "quit" );
			System.out.println(ending.getDescription());
        	topText.setText(ending.getDescription());
            EndingArray[2] = true;
            System.out.println(EndingArray[2]);
    		break;
    	case "22":
			ending = new Ending("22", "You have reached Ending 4", "restart", "quit" );
        	topText.setText(ending.getDescription());
            EndingArray[3] = true;
            System.out.println(EndingArray[3]);
    		break;
    		
        }
    	Button1.setText("Restart");
    	Button2.setText("Quit");
        Button1.setOnAction(e -> startNewGame());
}

}
