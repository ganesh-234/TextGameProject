package Pack1;
/**
 * Sample Skeleton for 'Textproject_UI.fxml' Controller Class
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameController {

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
    
    private String description = "";
    
    private boolean scoreDisplayed = false;

    static long startTime = System.currentTimeMillis();
    
    private boolean[] EndingArray= new boolean[4];
    
	static MyScene currentScene = new MyScene("You wake up inside of a concrete room, likely a part of a larger building. The walls are made of concrete, with the ceiling having caved in above you, too high to reach. Through the hole above you, you can barely make out the setting sun, along with the loud snarling of what you can only assume to be zombies.\r\n"
    		+ "\r\n"
    		+ "      A sound to your left breaks your focus, coming from an intercom speaker on the wall. In a robotic voice, it announces “THIS IS AN AUTOMATED MESSAGE REMINDING ALL REMAINING FACULTY MEMBERS TO FLEE THE FACILITY IMMEDIATELY. AS OF TOMORROW MORNING, THE FACILITY AND ITS SURROUNDINGS WILL BE REMOTELY DETONATED, COMPLETELY DESTROYING THE FACILITY AND MUCH OF THE SURROUNDING AREA, IN ORDER TO DEAL WITH THE COUNTLESS UNDEAD. THIS MANDATORY TIME OFF WILL BE COMING OUT OF YOUR SICK DAYS.”\r\n"
    		+ "\r\n"
    		+ "      The room you are in has two doors. The one in front of you is unlabeled, but likely leads further into the facility, promising more resources but also promising more distance from clear exit behind you, in the form of a rusted metal door labeled “Fire Exit”. It almost seems too easy, if not for the sound of loud undead snarling that can be heard coming from the other side.\r\n"
    		+ "", "1. Open door into building", "2. Open fire exit door",0);
    public void initialize() {
       
    	scoreboard = new Scoreboard();
    	
        configureGameChoices();
        configureScoreboardButton();
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
        
        MyScene scene = new MyScene("You wake up inside of a concrete room, likely a part of a larger building. The walls are made of concrete, with the ceiling having caved in above you, too high to reach. Through the hole above you, you can barely make out the setting sun, along with the loud snarling of what you can only assume to be zombies.\r\n"
        		+ "\r\n"
        		+ "      A sound to your left breaks your focus, coming from an intercom speaker on the wall. In a robotic voice, it announces “THIS IS AN AUTOMATED MESSAGE REMINDING ALL REMAINING FACULTY MEMBERS TO FLEE THE FACILITY IMMEDIATELY. AS OF TOMORROW MORNING, THE FACILITY AND ITS SURROUNDINGS WILL BE REMOTELY DETONATED, COMPLETELY DESTROYING THE FACILITY AND MUCH OF THE SURROUNDING AREA, IN ORDER TO DEAL WITH THE COUNTLESS UNDEAD. THIS MANDATORY TIME OFF WILL BE COMING OUT OF YOUR SICK DAYS.”\r\n"
        		+ "\r\n"
        		+ "      The room you are in has two doors. The one in front of you is unlabeled, but likely leads further into the facility, promising more resources but also promising more distance from clear exit behind you, in the form of a rusted metal door labeled “Fire Exit”. It almost seems too easy, if not for the sound of loud undead snarling that can be heard coming from the other side.\r\n"
        		+ "", "1. Open door into building", "2. Open fire exit door", 0);
        currentScene = scene;
      
        
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
            currentScene = scene;
            
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
            currentScene = scene;

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
   
    private void configureScoreboardButton() {
    	ScoreboardButton.setOnAction(e -> DisplayScore());
    }
    
    
    private void configureGameChoices() {
        Button1.setOnAction(e -> handleChoice(1));
        Button2.setOnAction(e -> handleChoice(2));
    }
    
    private void displayWelcomeMessage() {
        topText.setText("You wake up inside of a concrete room, likely a part of a larger building. The walls are made of concrete, with the ceiling having caved in above you, too high to reach. Through the hole above you, you can barely make out the setting sun, along with the loud snarling of what you can only assume to be zombies.\r\n"
        		+ "\r\n"
        		+ "      A sound to your left breaks your focus, coming from an intercom speaker on the wall. In a robotic voice, it announces “THIS IS AN AUTOMATED MESSAGE REMINDING ALL REMAINING FACULTY MEMBERS TO FLEE THE FACILITY IMMEDIATELY. AS OF TOMORROW MORNING, THE FACILITY AND ITS SURROUNDINGS WILL BE REMOTELY DETONATED, COMPLETELY DESTROYING THE FACILITY AND MUCH OF THE SURROUNDING AREA, IN ORDER TO DEAL WITH THE COUNTLESS UNDEAD. THIS MANDATORY TIME OFF WILL BE COMING OUT OF YOUR SICK DAYS.”\r\n"
        		+ "\r\n"
        		+ "      The room you are in has two doors. The one in front of you is unlabeled, but likely leads further into the facility, promising more resources but also promising more distance from clear exit behind you, in the form of a rusted metal door labeled “Fire Exit”. It almost seems too easy, if not for the sound of loud undead snarling that can be heard coming from the other side.\r\n"
        		+ "Do you:");
        description =  "You wake up inside of a concrete room, likely a part of a larger building. The walls are made of concrete, with the ceiling having caved in above you, too high to reach. Through the hole above you, you can barely make out the setting sun, along with the loud snarling of what you can only assume to be zombies.\r\n"
        		+ "\r\n"
        		+ "      A sound to your left breaks your focus, coming from an intercom speaker on the wall. In a robotic voice, it announces “THIS IS AN AUTOMATED MESSAGE REMINDING ALL REMAINING FACULTY MEMBERS TO FLEE THE FACILITY IMMEDIATELY. AS OF TOMORROW MORNING, THE FACILITY AND ITS SURROUNDINGS WILL BE REMOTELY DETONATED, COMPLETELY DESTROYING THE FACILITY AND MUCH OF THE SURROUNDING AREA, IN ORDER TO DEAL WITH THE COUNTLESS UNDEAD. THIS MANDATORY TIME OFF WILL BE COMING OUT OF YOUR SICK DAYS.”\r\n"
        		+ "\r\n"
        		+ "      The room you are in has two doors. The one in front of you is unlabeled, but likely leads further into the facility, promising more resources but also promising more distance from clear exit behind you, in the form of a rusted metal door labeled “Fire Exit”. It almost seems too easy, if not for the sound of loud undead snarling that can be heard coming from the other side.\r\n"
        		+ "";
	Button1.setText("Open door into building");
	Button2.setText("Open fire exit door");

    }

    private void startNewGame() {
    	startTime = System.currentTimeMillis();
        playerString = ""; // Reset the player 
        System.out.println(playerString);
        configureGameChoices();
        displayWelcomeMessage();
    }
    
    private void fixFinalEnding() {
    String playerString = "222222222";
    determineEndingTime("22222222");
    handleEnding("22222222");
    System.out.println(playerString);
}

    private void handleChoice(int choice) {
        // Append the choice
        playerString += choice;
        
        Ending ending;
        
        
        switch (playerString) {
    	case "score":
            String scoreboardInfo = scoreboard.display();
            scoreboard.display();
            topText.setText(scoreboardInfo);
            Button2.setDisable(true);
            Button1.setDisable(true);
            break;
        
            case "1":
                topText.setText("Through the doorway, you see a long hallway extend out in front of you. At the end of the hallway is a door labeled “EMPLOYEES ONLY” in bright white text, printed on a black-and-yellow chevron pattern. There’s many other labeled doors along the hall, but you can’t see the door’s labels around the several zombies wandering blindly around the hallway. They seem hungry, but none of them have noticed you yet.\r\n"
                		+ "      The door has a keycard scanner that blocks access, but you can see one of the zombies, wearing a sort of employee outfit with black pants and a dress shirt, has a keycard attached to a lapel on their belt.\r\n"
                		+ "\r\n"
                		+ "      The way things are, you see two options unfold in front of you: Try to sneak up and pickpocket the keycard off of the zombie in the white shirt, or smash into the zombie, grab the keycard and run for your life, hoping that the other zombies don’t try to grab you.\r\n"
                		+ "      Do you:\r\n"
                		+ ""); 
                description = "Through the doorway, you see a long hallway extend out in front of you. At the end of the hallway is a door labeled “EMPLOYEES ONLY” in bright white text, printed on a black-and-yellow chevron pattern. There’s many other labeled doors along the hall, but you can’t see the door’s labels around the several zombies wandering blindly around the hallway. They seem hungry, but none of them have noticed you yet.\r\n"
                		+ "      The door has a keycard scanner that blocks access, but you can see one of the zombies, wearing a sort of employee outfit with black pants and a dress shirt, has a keycard attached to a lapel on their belt.\r\n"
                		+ "\r\n"
                		+ "      The way things are, you see two options unfold in front of you: Try to sneak up and pickpocket the keycard off of the zombie in the white shirt, or smash into the zombie, grab the keycard and run for your life, hoping that the other zombies don’t try to grab you.\r\n"
                		+ "      Do you:\r\n"
                		+ "";
                Button1.setText("Try the stealth option");
                Button2.setText("Stealth is for nerds. Run for it!");
                System.out.println(playerString);
                break;
            case "11":
                topText.setText("You sneak behind a variety of objects in the hallway, silent as a shadow in the night as you slink silently through the hall towards your target. The employee-zombie is none the wiser as you reach your arm out from behind a large potted plant and nab the keycard from his belt, before silently slinking towards the door. The zombies only notice your presence when the door unlocks with a click, and they’re far too late to do anything but watch helplessly as it closes behind you, with them on the other side.\r\n"
                		+ "      What you find on the other side of this door is the inside of a large laboratory, complete with large cylindrical glass tanks in the center of the room filled with a thick, greenish fluid, with one of them holding an unconscious-looking zombie. A flask of the same greenish fluid sits on a desk nearby you, along with several other weird liquids you don’t recognize.\r\n"
                		+ "\r\n"
                		+ "      Before you have time to look around any more, a zombie shambles into the room. With nothing to defend yourself with, you don’t stand much of a chance.\r\n"
                		+ "\r\n"
                		+ "Do you:\r\n");
                description = "You sneak behind a variety of objects in the hallway, silent as a shadow in the night as you slink silently through the hall towards your target. The employee-zombie is none the wiser as you reach your arm out from behind a large potted plant and nab the keycard from his belt, before silently slinking towards the door. The zombies only notice your presence when the door unlocks with a click, and they’re far too late to do anything but watch helplessly as it closes behind you, with them on the other side.\r\n"
                		+ "      What you find on the other side of this door is the inside of a large laboratory, complete with large cylindrical glass tanks in the center of the room filled with a thick, greenish fluid, with one of them holding an unconscious-looking zombie. A flask of the same greenish fluid sits on a desk nearby you, along with several other weird liquids you don’t recognize.\r\n"
                		+ "\r\n"
                		+ "      Before you have time to look around any more, a zombie shambles into the room. With nothing to defend yourself with, you don’t stand much of a chance.\r\n"
                		+ "\r\n"
                		+ "Do you:\r\n";
                Button1.setText("Hide under the table!");
                Button2.setText("Throw the flask at it!");
                System.out.println(playerString);
                break;
            case "111":
                topText.setText("You duck, pressing your back up one of the a particularly cloudy-looking liquid tank. You’re not sure if the zombie can see you through it, but you simply hold your breath as it wanders around the room aimlessly, searching for... something. You’re not sure what, but most likely it’s something soft and squishy to chew on, like your skull.\r\n"
                		+ "\r\n"
                		+ "      As you plan your next move, luck appears to not be on your side, as you hear its steps draw closer, and closer, and closer still to your hiding place.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "You duck, pressing your back up one of the a particularly cloudy-looking liquid tank. You’re not sure if the zombie can see you through it, but you simply hold your breath as it wanders around the room aimlessly, searching for... something. You’re not sure what, but most likely it’s something soft and squishy to chew on, like your skull.\r\n"
                		+ "\r\n"
                		+ "      As you plan your next move, luck appears to not be on your side, as you hear its steps draw closer, and closer, and closer still to your hiding place.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Hide under the table!");
                Button2.setText("Run for the exit!");
                System.out.println(playerString);
                break;
            case "1111":
                topText.setText("Thinking quickly, you hide under the table that the glassware is sitting on, ducking under and hoping that the shambling beast doesn’t find you. It doesn’t seem to, as it lumbers around the room. It looks around the room, unfocused, before eventually it starts to leave.\r\n"
                		+ "      Breathing a sigh of relief, you watch as it walks away, and try to leave your hiding spot under the table. In your haste, however, you bump your head on the bottom of the table, upsetting the glassware on top with a soft “clink!” against one another. The zombie’s head snaps back towards your location, and you scramble back beneath the table, cursing yourself as you do.\r\n"
                		+ "\r\n"
                		+ "      The zombie begins to double back, and walks towards the table you’re under. It doesn’t see you yet, but it might if you wait too long.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "Thinking quickly, you hide under the table that the glassware is sitting on, ducking under and hoping that the shambling beast doesn’t find you. It doesn’t seem to, as it lumbers around the room. It looks around the room, unfocused, before eventually it starts to leave.\r\n"
                		+ "      Breathing a sigh of relief, you watch as it walks away, and try to leave your hiding spot under the table. In your haste, however, you bump your head on the bottom of the table, upsetting the glassware on top with a soft “clink!” against one another. The zombie’s head snaps back towards your location, and you scramble back beneath the table, cursing yourself as you do.\r\n"
                		+ "\r\n"
                		+ "      The zombie begins to double back, and walks towards the table you’re under. It doesn’t see you yet, but it might if you wait too long.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Throw a glass vial to distract it");
                Button2.setText("Stay there");
                System.out.println(playerString);
                break;
            case "11111":
                topText.setText("The vial shatters somewhere else in the room where you threw it, and the zombie goes over to investigate it, away from your position. However, as you do so, you hear the malcontented grunting of several other zombies reacting to the loud sound from outside the door. It sounds like they might be coming in to investigate the sound as well.\r\n"
                		+ "\r\n"
                		+ "Do you:\r\n");
                description = "The vial shatters somewhere else in the room where you threw it, and the zombie goes over to investigate it, away from your position. However, as you do so, you hear the malcontented grunting of several other zombies reacting to the loud sound from outside the door. It sounds like they might be coming in to investigate the sound as well.\r\n"
        		+ "\r\n"
        		+ "Do you:\r\n";
                Button1.setText("Sneak past");
                Button2.setText("Stay where you are");
                System.out.println(playerString);
                break;
            case "111111":
                topText.setText("      As silent as a whisper, you sneak past the distracted zombies, gone before they ever realized you were there. The room you find yourself in, through the other door, is a decontamination room with lockers along one wall and multiple chemical showers along the other. Most of the lockers are locked (naturally) but one of them is slightly ajar, and you can see a screwdriver inside of it.\r\n"
                		+ "      On the ceiling, able to be entered if you climb on top of the lockers, is a vent shaft covered with a metal grate, albeit a bit rusted and loose-looking.  Across the room, on the opposite wall, is another door.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "      As silent as a whisper, you sneak past the distracted zombies, gone before they ever realized you were there. The room you find yourself in, through the other door, is a decontamination room with lockers along one wall and multiple chemical showers along the other. Most of the lockers are locked (naturally) but one of them is slightly ajar, and you can see a screwdriver inside of it.\r\n"
                		+ "      On the ceiling, able to be entered if you climb on top of the lockers, is a vent shaft covered with a metal grate, albeit a bit rusted and loose-looking.  Across the room, on the opposite wall, is another door.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Try to reach the vent shaft");
                Button2.setText("Go through the door");
                System.out.println(playerString);
                break;
            case "111112":
            	determineEndingTime("111112");
            	handleEnding("111112");
                System.out.println(playerString);
                break;
            case "1111111":
            	topText.setText("As you climb onto the lockers to try to get through the vent, you try to pull the grate off by hand, you realize that it’s not quite as loose as you’d first realized. You can’t quite pull it off by hand, and with the rattling of the bars, you hear the sound of zombies stirring, both from the room you came from and from the door at the other end of the room. It sounds like they’re coming to investigate.\r\n"
            			+ "\r\n"
            			+ "      Do you:\r\n");
            	description = "As you climb onto the lockers to try to get through the vent, you try to pull the grate off by hand, you realize that it’s not quite as loose as you’d first realized. You can’t quite pull it off by hand, and with the rattling of the bars, you hear the sound of zombies stirring, both from the room you came from and from the door at the other end of the room. It sounds like they’re coming to investigate.\r\n"
            			+ "\r\n"
            			+ "      Do you:\r\n";
            	Button1.setText("Unscrew the gate with the screwdriver.");
            	Button2.setText("Just pull harder");
            	break;
            case "11111111":
            	determineEndingTime("1111111");
            	handleEnding("11111111");
            	System.out.println(playerString);
            	break;
            case "11111112":
            	determineEndingTime("11111112");
            	handleEnding("11111112");
                System.out.println(playerString);
                break;
            case "1111112":
            	determineEndingTime("1111112");
            	handleEnding("1111112");
                System.out.println(playerString);
                break;
            case "11112":
            	determineEndingTime("11112");
            	handleEnding("11112");
                System.out.println(playerString);
                break;
            case "1112":
            	determineEndingTime("1112");
            	handleEnding("1112");
                System.out.println(playerString);
                break;
            case "112":
                determineEndingTime("112");
                handleEnding("112");
                System.out.println(playerString);
                break;
            case "12":
                topText.setText("You BOOK IT for the zombie, slamming your entire weight into them through your shoulder and sending their body clattering to the ground. You grab the keycard off their again-lifeless corpse, but find yourself quickly approached by the several angry undead that noticed your loud, obvious actions. There’s only one step left in your plan: RUN!\r\n"
                		+ "      As you run towards the door at the end of the hallway, a group of zombies step in your way, blocking your exit. With no other option, you sprint back down the hallway, ducking down another corridor and running as fast as you can through the halls. As you run away, You find another door, one that looks heavy and metal, and to its left you see another corridor, with a somehow-still-working escalator that leads to a lower floor. The door might hold up against the zombies if you find yourself on the other side, but everyone knows that it’s rude to talk to strangers on an escalator, so that probably extends to eating people, too.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "You BOOK IT for the zombie, slamming your entire weight into them through your shoulder and sending their body clattering to the ground. You grab the keycard off their again-lifeless corpse, but find yourself quickly approached by the several angry undead that noticed your loud, obvious actions. There’s only one step left in your plan: RUN!\r\n"
                		+ "      As you run towards the door at the end of the hallway, a group of zombies step in your way, blocking your exit. With no other option, you sprint back down the hallway, ducking down another corridor and running as fast as you can through the halls. As you run away, You find another door, one that looks heavy and metal, and to its left you see another corridor, with a somehow-still-working escalator that leads to a lower floor. The door might hold up against the zombies if you find yourself on the other side, but everyone knows that it’s rude to talk to strangers on an escalator, so that probably extends to eating people, too.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Make a break for the door!"); 
                Button2.setText("Ride the escalator!");
                break;
            case "122":
                topText.setText("      As you step onto the escalator, you already regret your decision, thinking that surely there’s no possible way that this will work. As the zombies approach you from behind, you watch as they arrive at the top of the escalator and... begin patiently riding it down, glaring at you with seething hatred the entire time. When you get to the bottom, you watch as one of the zombies’ pant legs gets caught in the escalator, causing them to trip and fall at the bottom.\r\n"
                		+ "      Seeing this moment of weakness, it gives you a moment to do something. Looking to your left, you see a room labeled “Facility barber”. Looking to your right, you see what looks to be a discarded boombox.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "      As you step onto the escalator, you already regret your decision, thinking that surely there’s no possible way that this will work. As the zombies approach you from behind, you watch as they arrive at the top of the escalator and... begin patiently riding it down, glaring at you with seething hatred the entire time. When you get to the bottom, you watch as one of the zombies’ pant legs gets caught in the escalator, causing them to trip and fall at the bottom.\r\n"
                		+ "      Seeing this moment of weakness, it gives you a moment to do something. Looking to your left, you see a room labeled “Facility barber”. Looking to your right, you see what looks to be a discarded boombox.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Run for the barber shop!"); 
                Button2.setText("Dance Battle!");
                break;
            case "1221":
                topText.setText("      Thinking quickly, you dive into the barber shop, and put on a nearby apron, dressing yourself like a true barber. As the pursuing zombies follow you in, they look at you with confusion, making note of your new uniform. “Here for a trim, my good sirs?” you ask, brandishing a pair of scissors and offering them a seat in the salon’s chairs. Shrugging, a few of them fill the chairs for their haircut, the rest of them forming a single-file line to patiently wait.\r\n"
                		+ "      The hour that follows is one of surprising pleasantry, as you make small talk with the zombies about their day while trimming their hair, and hoping to whatever god you do or don’t pray to that they don’t remember that they’re zombies.\r\n"
                		+ "\r\n"
                		+ "      After about an hour, the zombies give you their thanks and leave the salon, leaving you in peace to ponder what just happened. However, while you’re thinking, you notice a large iron door out of the corner of your eye, in the far end of the salon. Given that the only other way out is the exit that the zombies just left through, it seems promising. The only problem is, when you try the handle, it’s locked.\r\n"
                		+ "      Do you:\r\n");
                description = "      Thinking quickly, you dive into the barber shop, and put on a nearby apron, dressing yourself like a true barber. As the pursuing zombies follow you in, they look at you with confusion, making note of your new uniform. “Here for a trim, my good sirs?” you ask, brandishing a pair of scissors and offering them a seat in the salon’s chairs. Shrugging, a few of them fill the chairs for their haircut, the rest of them forming a single-file line to patiently wait.\r\n"
                		+ "      The hour that follows is one of surprising pleasantry, as you make small talk with the zombies about their day while trimming their hair, and hoping to whatever god you do or don’t pray to that they don’t remember that they’re zombies.\r\n"
                		+ "\r\n"
                		+ "      After about an hour, the zombies give you their thanks and leave the salon, leaving you in peace to ponder what just happened. However, while you’re thinking, you notice a large iron door out of the corner of your eye, in the far end of the salon. Given that the only other way out is the exit that the zombies just left through, it seems promising. The only problem is, when you try the handle, it’s locked.\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Convince the door to open up with therapy!"); 
                Button2.setText("Dance Battle!");
                break;
            case "1222":
                determineEndingTime("1222");
                handleEnding("1222");
                System.out.println(playerString);
                break;
            case "12211":
                topText.setText("      Sitting down in one of the barber chairs, you ask the door how it feels. With an unsatisfied lack of sound, you realize that it might be a tougher nut to crack than you’d first thought. You spend the next several minutes having a one-sided conversation with the door about healthy methods of coping with the current political climate.\r\n"
                		+ "\r\n"
                		+ "      As you grab the handle of the door to pull it to a chair to facilitate further discussion (Because as everyone knows, a couch is the best place to speak ) the door swings open towards you, unlocked since before you got here. Who knew you were so good at therapy?\r\n"
                		+ "\r\n"
                		+ "      Behind the door is a metal catwalk, about a story above the ground below which leads into what looks like a large concrete warehouse filled with shipping crates. The only way forward is down, so you’ll need a way to get to the floor below.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "      Sitting down in one of the barber chairs, you ask the door how it feels. With an unsatisfied lack of sound, you realize that it might be a tougher nut to crack than you’d first thought. You spend the next several minutes having a one-sided conversation with the door about healthy methods of coping with the current political climate.\r\n"
                		+ "\r\n"
                		+ "      As you grab the handle of the door to pull it to a chair to facilitate further discussion (Because as everyone knows, a couch is the best place to speak ) the door swings open towards you, unlocked since before you got here. Who knew you were so good at therapy?\r\n"
                		+ "\r\n"
                		+ "      Behind the door is a metal catwalk, about a story above the ground below which leads into what looks like a large concrete warehouse filled with shipping crates. The only way forward is down, so you’ll need a way to get to the floor below.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Do some cool parkour moves off the crates"); 
                Button2.setText("Dance Battle!");
                break;
            case "12212":
                determineEndingTime("12212");
                handleEnding("12212");
                System.out.println(playerString);
                break;
            case "122111":
                topText.setText("      You crack your fingers and limber up, before diving haphazardly towards the metal containers. With all the grace of a delicate bird, you immediately slam your face directly into the side of one of the shipping containers, and knock into several more crates before hitting the ground. You probably should have considered your zero hours of parkour training, but you also did therapy at a door just a minute earlier, and that went fine without training, so you’re not sure what went wrong.\r\n"
                		+ "\r\n"
                		+ "      You’re still left to think about this when the zombies from before, hair as fresh as before, return with friends. Climbing down the ladder that you really wish you’d seen before jumping down, you quickly find yourself surrounded by a horde of hungry zombies, ready to eat your flesh right in this open, echo-y location. What a shame that you’ll end up staining this weird floor with colored panels that light up when you step on them.\r\n"
                		+ "\r\n"
                		+ "      With no options left, you prepare to do the only thing left that you can think to do:\r\n");
                description = "      You crack your fingers and limber up, before diving haphazardly towards the metal containers. With all the grace of a delicate bird, you immediately slam your face directly into the side of one of the shipping containers, and knock into several more crates before hitting the ground. You probably should have considered your zero hours of parkour training, but you also did therapy at a door just a minute earlier, and that went fine without training, so you’re not sure what went wrong.\r\n"
                		+ "\r\n"
                		+ "      You’re still left to think about this when the zombies from before, hair as fresh as before, return with friends. Climbing down the ladder that you really wish you’d seen before jumping down, you quickly find yourself surrounded by a horde of hungry zombies, ready to eat your flesh right in this open, echo-y location. What a shame that you’ll end up staining this weird floor with colored panels that light up when you step on them.\r\n"
                		+ "\r\n"
                		+ "      With no options left, you prepare to do the only thing left that you can think to do:\r\n";
                Button1.setText("Dance Battle!"); 
                Button2.setText("Dance Battle!");
                break;
            case "122112":
                determineEndingTime("122112");
                handleEnding("122112");
                System.out.println(playerString);
                break;
            case "1221111":
                determineEndingTime("1221111");
                handleEnding("1221111");
                System.out.println(playerString);
                break;
            case "1221112":
                determineEndingTime("1221112");
                handleEnding("1221112");
                System.out.println(playerString);
                break;
            case "121":
                topText.setText("      You slam open the door, diving through it and slamming it shut behind you. For a brief moment you think you’re safe, before the hungry undead slam their bodies against the other side of the door, almost making it in before you shove your full weight against the door, desperate to hold it shut.\r\n"
                		+ "      Seeking something to bar the door, you look frantically around the room, and spot a handgun within reach. It looks like it’s loaded and everything, and even has a scope for some reason.\r\n"
                		+ "      In fact, the entire room you’re in seems to be filled with guns, armored vehicles, and a variety of different military-grade weaponry.\r\n"
                		+ "\r\n"
                		+ "      The way you see it, you’ve got two options: Open the door and blow ‘em all away with the contents of the room, or try to hold the door from being opened until the zombies give up and leave.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "      You slam open the door, diving through it and slamming it shut behind you. For a brief moment you think you’re safe, before the hungry undead slam their bodies against the other side of the door, almost making it in before you shove your full weight against the door, desperate to hold it shut.\r\n"
                		+ "      Seeking something to bar the door, you look frantically around the room, and spot a handgun within reach. It looks like it’s loaded and everything, and even has a scope for some reason.\r\n"
                		+ "      In fact, the entire room you’re in seems to be filled with guns, armored vehicles, and a variety of different military-grade weaponry.\r\n"
                		+ "\r\n"
                		+ "      The way you see it, you’ve got two options: Open the door and blow ‘em all away with the contents of the room, or try to hold the door from being opened until the zombies give up and leave.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Blast 'Em!"); 
                Button2.setText("Hold the Door!");
                break;
            case "1212":
                determineEndingTime("1212");
                handleEnding("1212");
                System.out.println(playerString);
                break;
            case "1211":
                topText.setText("Choosing violence, you grab the handgun from the ground nearby you and take a few steps away from the door, just in time for the approaching zombies to knock it off of its hinges, shambling through the door frame before being blasted away by your gun.\r\n"
                		+ "\r\n"
                		+ "      However, as one might imagine, handguns are quite loud, and the zombies were already making a lot of noise by breaking down the door. All of this noise was bound to attract more undead, and you watch as countless more undead begin swarming in through the now-broken door.\r\n"
                		+ "      By all means, they should spell death for you, but there’s one thing that they didn’t account for: You’ve got an armory full of new toys to play with.\r\n"
                		+ "\r\n"
                		+ "      Turning around, you see two different weapons within your reach: A shotgun, and a grenade launcher.\r\n"
                		+ "      Do you:\r\n");
                description = "Choosing violence, you grab the handgun from the ground nearby you and take a few steps away from the door, just in time for the approaching zombies to knock it off of its hinges, shambling through the door frame before being blasted away by your gun.\r\n"
                		+ "\r\n"
                		+ "      However, as one might imagine, handguns are quite loud, and the zombies were already making a lot of noise by breaking down the door. All of this noise was bound to attract more undead, and you watch as countless more undead begin swarming in through the now-broken door.\r\n"
                		+ "      By all means, they should spell death for you, but there’s one thing that they didn’t account for: You’ve got an armory full of new toys to play with.\r\n"
                		+ "\r\n"
                		+ "      Turning around, you see two different weapons within your reach: A shotgun, and a grenade launcher.\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Grab the Shotgun!"); 
                Button2.setText("Grenade launcher time!");
                break;
            case "12112":
                determineEndingTime("12112");
                handleEnding("12112");
                System.out.println(playerString);
                break;
            case "12111":
                topText.setText("      Grabbing hold of the shotgun, you send rounds of buckshot into the zombies, knocking them over like bowling pins. Two quick shots from your newfound double-barrel sees significant success in fending them back. That is, until you need to reload, where they begin to make more ground, driving you into the room.\r\n"
                		+ "\r\n"
                		+ "      As you back away to reload, one of the zombies lurches forward towards you, reaching towards your shotgun.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "      Grabbing hold of the shotgun, you send rounds of buckshot into the zombies, knocking them over like bowling pins. Two quick shots from your newfound double-barrel sees significant success in fending them back. That is, until you need to reload, where they begin to make more ground, driving you into the room.\r\n"
                		+ "\r\n"
                		+ "      As you back away to reload, one of the zombies lurches forward towards you, reaching towards your shotgun.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Hit it with the stock!"); 
                Button2.setText("Shoot it!");
                break;
            case "121112":
                determineEndingTime("121112");
                handleEnding("12112");
                System.out.println(playerString);
                break;
            case "121111":
                topText.setText("      You slam the stock into the creature’s jaw, sending its head flying off of its body back into the crowd, and leaving the rest of its body to go limp and flop to the ground.\r\n"
                		+ "\r\n"
                		+ "      Seeing as this shotgun is empty, you look around again for more weaponry, and see an entire wall, filled to the top with weapons of war. Shotguns, rifles, grenade launchers, oh my! Everything you may need to defend yourself from the hundreds and hundreds of zombies that are currently shoving their way through the door and into the room.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n");
                description = "      You slam the stock into the creature’s jaw, sending its head flying off of its body back into the crowd, and leaving the rest of its body to go limp and flop to the ground.\r\n"
                		+ "\r\n"
                		+ "      Seeing as this shotgun is empty, you look around again for more weaponry, and see an entire wall, filled to the top with weapons of war. Shotguns, rifles, grenade launchers, oh my! Everything you may need to defend yourself from the hundreds and hundreds of zombies that are currently shoving their way through the door and into the room.\r\n"
                		+ "\r\n"
                		+ "      Do you:\r\n";
                Button1.setText("Load up on weapons!"); 
                Button2.setText("I'm good with what I have!");
                break;
            case "1211112":
                determineEndingTime("1211112");
                handleEnding("1211112");
                System.out.println(playerString);
                break;
            case "1211111":
                topText.setText("Grabbing things off the walls, you begin firing wildly into the crowd of zombies \r\n"
                		+ "\r\n"
                		+ "      ...But yet more flood into the room, like a sea of green, ready to tear you apart with their teeth.\r\n"
                		+ "\r\n"
                		+ "      As a sea of zombies flood into the room, weapons on your back and guns in your hands, what are you thinking in this moment?\r\n");
                description = "Grabbing things off the walls, you begin firing wildly into the crowd of zombies \r\n"
                		+ "\r\n"
                		+ "      ...But yet more flood into the room, like a sea of green, ready to tear you apart with their teeth.\r\n"
                		+ "\r\n"
                		+ "      As a sea of zombies flood into the room, weapons on your back and guns in your hands, what are you thinking in this moment?\r\n";
                Button1.setText("Let 'em come!"); 
                Button2.setText("Maybe I should give up?");
                break;
            case "12111111":
                determineEndingTime("12111111");
                handleEnding("12111111");
                System.out.println(playerString);
                break;
            case "12111112":
                determineEndingTime("12111112");
                handleEnding("12111112");
                System.out.println(playerString);
                break;
            case "2":
                topText.setText("      As you open the fire exit door, you meet the source of the snarling and banging sounds: Unsurprisingly, a large number of zombies. At the sound of you opening the large metal door, a thousand rotten eyes rest on you, before the many zombies begin sprinting towards you.\r\n"
                		+ "\r\n"
                		+ "      Upon seeing this situation, you realize two things: Hindsight is 20/20, and is probably the last place you should be right now.\r\n"
                		+ "\r\n"
                		+ "Do you:\r\n");
                description = "      As you open the fire exit door, you meet the source of the snarling and banging sounds: Unsurprisingly, a large number of zombies. At the sound of you opening the large metal door, a thousand rotten eyes rest on you, before the many zombies begin sprinting towards you.\r\n"
                		+ "\r\n"
                		+ "      Upon seeing this situation, you realize two things: Hindsight is 20/20, and is probably the last place you should be right now.\r\n"
                		+ "\r\n"
                		+ "Do you:\r\n";
                Button1.setText("Hide"); 
                Button2.setText("Fight");
                System.out.println(playerString);
                break;
            case "21":
            	determineEndingTime("21");
            	handleEnding("21");
                System.out.println(playerString);
            	break;
            case "22":
                if (EndingArray[1] == true) {
                    topText.setText(" Pulling the pin on your grenade, you throw it into the crowd of zombies nearest to you, blowing them all away in a giant fiery explosion. Blood and gore rains from the sky as their innards return to earth.\r\n"
                    		+ "      However, as you soon notice, there’s a lot more zombies out here than just those ones. A further-off group of zombies who heard the blast begins shambling towards you. They’re making ground quick, and you only had the one grenade.\r\n"
                    		+ "\r\n"
                    		+ "      Looking around you, you only see two points of exit: Firstly, to your left is a staircase leading down, likely meant to act as a fire escape for a basement level. Secondly, there’s the open door, leading back the way you came.\r\n"
                    		+ "      Do you run:\r\n");
                    description = " Pulling the pin on your grenade, you throw it into the crowd of zombies nearest to you, blowing them all away in a giant fiery explosion. Blood and gore rains from the sky as their innards return to earth.\r\n"
                    		+ "      However, as you soon notice, there’s a lot more zombies out here than just those ones. A further-off group of zombies who heard the blast begins shambling towards you. They’re making ground quick, and you only had the one grenade.\r\n"
                    		+ "\r\n"
                    		+ "      Looking around you, you only see two points of exit: Firstly, to your left is a staircase leading down, likely meant to act as a fire escape for a basement level. Secondly, there’s the open door, leading back the way you came.\r\n"
                    		+ "      Do you run:\r\n";
                    Button1.setText("Into the building!"); 
                    Button2.setText("Down the Stairs");
                    System.out.println(playerString);
                }
                else {
                	determineEndingTime("22a");
                	handleEnding("22a");
                    System.out.println(playerString);
                	break;
                	
                }
            	break;
            case "221":
            	determineEndingTime("221");
            	handleEnding("221");
                System.out.println(playerString);
            	break;
            case "222":
                    topText.setText("Rushing down the stairs, you can hear the zombies chasing behind you, but moving safely down a staircase is hard when your legs are made of bad meat and rotten bone. In fact, you’re likely to outpace them.\r\n"
                    		+ "\r\n"
                    		+ "      Only one problem: When you get to the bottom of the stairs, you find yourself blocked from the way forward by a large metal door, which doesn’t have a doorknob on this side of the door. The hinges are on this side of the door, however, but like the rest of the door, they’re made of a very sturdy-looking metal, right down to the screws.\r\n"
                    		+ "\r\n"
                    		+ "      Through a barred window in the steel door, however, you see salvation: The doorway leads into an underground parking garage, with a garage door off to the right side.  In front of that door is a large armored vehicle, which looks strong enough to survive an explosion, much less a few zombies.  That’s your ticket out of here, and all you have to do is get through this door. \r\n"
                    		+ "      Do you:\r\n");
                    description = "Rushing down the stairs, you can hear the zombies chasing behind you, but moving safely down a staircase is hard when your legs are made of bad meat and rotten bone. In fact, you’re likely to outpace them.\r\n"
                    		+ "\r\n"
                    		+ "      Only one problem: When you get to the bottom of the stairs, you find yourself blocked from the way forward by a large metal door, which doesn’t have a doorknob on this side of the door. The hinges are on this side of the door, however, but like the rest of the door, they’re made of a very sturdy-looking metal, right down to the screws.\r\n"
                    		+ "\r\n"
                    		+ "      Through a barred window in the steel door, however, you see salvation: The doorway leads into an underground parking garage, with a garage door off to the right side.  In front of that door is a large armored vehicle, which looks strong enough to survive an explosion, much less a few zombies.  That’s your ticket out of here, and all you have to do is get through this door. \r\n"
                    		+ "      Do you:\r\n";
                    Button1.setText("Smash the door down!"); 
                    Button2.setText("Go for the Hinges!");
                    System.out.println(playerString);
                    break;
            case "2221":
            	determineEndingTime("2221");
            	handleEnding("2221");
                System.out.println(playerString);
            	break;
            case "2222":
                if (EndingArray[0] == true) {
                    topText.setText("Pulling out your trusty screwdriver, you unscrew the hinges from the wall, causing the door to swing towards you and clatter against the wall. From behind it, however, you can hear the zombies start to close in from the stairs below you.\r\n"
                    		+ "\r\n"
                    		+ "You see two options before you. Firstly, you can sprint out into the parking garage and hope you make it to the armored vehicle. Secondly, you can hide yourself under the door and wait until the zombies leave.\r\n"
                    		+ "\r\n"
                    		+ "      Do you:\r\n");
                    description = "Pulling out your trusty screwdriver, you unscrew the hinges from the wall, causing the door to swing towards you and clatter against the wall. From behind it, however, you can hear the zombies start to close in from the stairs below you.\r\n"
                    		+ "\r\n"
                    		+ "You see two options before you. Firstly, you can sprint out into the parking garage and hope you make it to the armored vehicle. Secondly, you can hide yourself under the door and wait until the zombies leave.\r\n"
                    		+ "\r\n"
                    		+ "      Do you:\r\n";
                    Button1.setText("Hide under the door!"); 
                    Button2.setText("Run for it!");
                    System.out.println(playerString);
                }
                else {
                	determineEndingTime("2222a");
                	handleEnding("2222a");
                    System.out.println(playerString);
                	break;
                	
                }
                break;
            case "22221":
            	determineEndingTime("22221");
            	handleEnding("22221");
                System.out.println(playerString);

            	break;
                
            case "22222":
            	topText.setText("      Sprinting out into the parking garage, you hear the pained groaning of zombies spilling through the doorway you just unlocked, shambling after you in thirst for your tender man-flesh. They’re gaining, but you have a decently substantial lead ahead of them, so you feel like you should have enough room to make it to the vehicle.\r\n"
                		+ "      That is, until one of them breaks away, ahead of the rest of the pack, lurching forward to grab you by the arm, over the sleeve of your shirt.\r\n"
                		+ "      The way you see it, you can either try to punch it in the mouth to disorient the zombie and hope it pulls away, or you can try to pull your arm out of your shirt, leaving the zombie only holding onto the shirt instead of your arm.\r\n"
                		+ "\r\n"
                		+ "Do you:\r\n");
            	description = "      Sprinting out into the parking garage, you hear the pained groaning of zombies spilling through the doorway you just unlocked, shambling after you in thirst for your tender man-flesh. They’re gaining, but you have a decently substantial lead ahead of them, so you feel like you should have enough room to make it to the vehicle.\r\n"
                		+ "      That is, until one of them breaks away, ahead of the rest of the pack, lurching forward to grab you by the arm, over the sleeve of your shirt.\r\n"
                		+ "      The way you see it, you can either try to punch it in the mouth to disorient the zombie and hope it pulls away, or you can try to pull your arm out of your shirt, leaving the zombie only holding onto the shirt instead of your arm.\r\n"
                		+ "\r\n"
                		+ "Do you:\r\n";
                Button1.setText("Punch it!"); 
                Button2.setText("Remove your shirt!");
                System.out.println(playerString);
            	break;
            case "222221":
            	determineEndingTime("222221");
            	handleEnding("222221");
                System.out.println(playerString);

            	break;
            case "222222":
            	topText.setText("      You hastily remove your shirt, leaving the zombie clumsily clutching the shirt in their hand. As you continue to make your escape, you leap into the seat of the front seat of the armored vehicle, and try to drive off, and you find a problem immediately: It isn’t turned on.\r\n"
            			+ "\r\n"
            			+ "      Behind you, the zombie horde is still approaching. You don’t have much time to act.\r\n"
            			+ "\r\n"
            			+ "      Do you:\r\n");
            	description = "      You hastily remove your shirt, leaving the zombie clumsily clutching the shirt in their hand. As you continue to make your escape, you leap into the seat of the front seat of the armored vehicle, and try to drive off, and you find a problem immediately: It isn’t turned on.\r\n"
            			+ "\r\n"
            			+ "      Behind you, the zombie horde is still approaching. You don’t have much time to act.\r\n"
            			+ "\r\n"
            			+ "      Do you:\r\n";
                Button1.setText("Hotwire it!"); 
                Button2.setText("Try the keys!");
                System.out.println(playerString);
            	break;
            case "2222221":
            	determineEndingTime("2222221");
            	handleEnding("2222221");
                System.out.println(playerString);
            	break;
            case "2222222":
                if (EndingArray[2] == true) {
                    topText.setText("      Trying the keys on the keyring, you sift through a few of the keys on the ring, until you find a car key. Pushing it into the ignition and turning, the car hums to life. What a coincidence!\r\n"
                    		+ "      Hearing the zombies shambling behind you, you put the car into drive and start to drive off, only to see that there’s a metal garage door in front of you. Luckily, outside the car, next to the door, you can see a button to open the door. What luck!\r\n"
                    		+ "\r\n"
                    		+ "\r\n"
                    		+ "      Do you:\r\n");
                    description = "      Trying the keys on the keyring, you sift through a few of the keys on the ring, until you find a car key. Pushing it into the ignition and turning, the car hums to life. What a coincidence!\r\n"
                    		+ "      Hearing the zombies shambling behind you, you put the car into drive and start to drive off, only to see that there’s a metal garage door in front of you. Luckily, outside the car, next to the door, you can see a button to open the door. What luck!\r\n"
                    		+ "\r\n"
                    		+ "\r\n"
                    		+ "      Do you:\r\n";
                    Button1.setText("Open the garage door!"); 
                    Button2.setText("Crash through it!");
                    System.out.println(playerString);
                }
                else {
                	determineEndingTime("2222222a");
                	handleEnding("2222222a");
                    System.out.println(playerString);
                	break;
                	
                }
            case "22222222":
            	topText.setText("Not wanting to slow down for the door to open, you crash directly through the metal door, tearing through it like tinfoil. With a loud crash, you can practically see your exit laid out in front of you.\r\n"
            			+ "      The only question is this: How carefully do you drive?\r\n"
            			+ "      The last question asks if you drive carefully or quickly. If you drive carefully, you die swarmed by zombies when you stop at a red light, who could follow you because you were using your turn signals.\r\n"
            			+ "\r\n"
            			+ "      Finally, after a victorious run, you manage to fly away in your newfound helicopter, getting out of there before the dawn breaks. You survived.\r\n"
            			+ "\r\n"
            			+ "      Do you:\r\n");
            	description = "Not wanting to slow down for the door to open, you crash directly through the metal door, tearing through it like tinfoil. With a loud crash, you can practically see your exit laid out in front of you.\r\n"
            			+ "      The only question is this: How carefully do you drive?\r\n"
            			+ "      The last question asks if you drive carefully or quickly. If you drive carefully, you die swarmed by zombies when you stop at a red light, who could follow you because you were using your turn signals.\r\n"
            			+ "\r\n"
            			+ "      Finally, after a victorious run, you manage to fly away in your newfound helicopter, getting out of there before the dawn breaks. You survived.\r\n"
            			+ "\r\n"
            			+ "      Do you:\r\n";
            	
                Button1.setText("Drive carefully"); 
                Button2.setText("Drive Fast!");
                Button2.setOnAction(e -> fixFinalEnding());
                System.out.println(playerString);
            	break;
            case "22222221":
            	determineEndingTime("22222221");
            	handleEnding("22222221");
                System.out.println(playerString);
            	break;
            case "222222222":
            	determineEndingTime("22222222");
            	handleEnding("22222222");
                System.out.println(playerString);
            	break;
                }
        
        
        
            //we can add more cases 
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
        String playertemp = "";
        if (scoreDisplayed == false) {
        	playertemp = playerString;
            String scoreboardInfo = scoreboard.display();
            scoreboard.display();
            topText.setText(scoreboardInfo);
            Button2.setDisable(true);
            Button1.setDisable(true);
            scoreDisplayed = true;
            playerString = "score";
            System.out.println(playertemp);
            System.out.println(playerString);
        } else if (scoreDisplayed == true){
        	topText.setText(description);
            Button2.setDisable(false);
            Button1.setDisable(false);
            scoreDisplayed = false;
            playerString = playertemp;
            System.out.println(playertemp);
            System.out.println(playerString);
        }
    }

    

    public void handleEnding(String playerString) {
    	Ending ending;
    	
    	switch(playerString) {
    		
    	
        case "1112":
        	ending = new Ending("1112", "      As you begin to run towards the exit, you end up running right past the zombie, who is all-too-eager to chase you down. In your fright, you trip, and are latched onto by the rotting undead and eaten whole.\r\n"
        			+ "\r\n"
        			+ "\r\n"
        			+ "", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            System.out.println(EndingArray[0]);
        	break;
        case "11112":
        	ending = new Ending("1112", "As the zombie makes its way towards your hiding spot, it naturally investigates the area around the table, before poking its head underneath it, locking eyes with you. Given that it’s standing in the way of the only way out from under the table, it’s no guess as to what happens to you next."
        			+ "", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            System.out.println(EndingArray[0]);
        	break;
        case "111112":
        	ending = new Ending("11112", "    Staying where you are, it’s not too long before the zombies figure out that the shattered glassware likely came from the same place as the rest of the glass vials, and begin investigating the table.\r\n"
        			+ "      You can already tell what happens next.\r\n"
        			+ "", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
        	break;
        case "11111111":
        	ending = new Ending("111112", "      Thinking quickly, you reach down to grab the screwdriver Graceful as a cat and silent as the wind in the night, you unscrew the grate open and climb into the ventilation shaft, pulling the grate up and into the vent with you. When the zombies get to the room you were in, it’s like nobody was even there.\r\n"
        			+ "\r\n"
        			+ "      You spend the rest of the night searching the compound’s corridors, silently slinking from room to room through the vents with not a single zombie even noticing you were ever there.\r\n"
        			+ "      But the compound is maze-like, and as morning quickly comes, you find yourself - along with the rest of the building - engulfed in a ball of flames.\r\n"
        			+ "\r\n"
        			+ "      Good thing you held onto that screwdriver, though. It might still come in handy some time in the future.\r\n"
        			+ "\r\n"
        			+ "TRUE ENDING 2: TACTICAL\r\n", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            EndingArray[0] = true;
            item2Text.setText("Screwdriver");
            item2Image.setImage(
            		new Image("Pack1/images/Screwdriver.png"));
            System.out.println(EndingArray[0]);
        	break;
        case "1111112":
        	ending = new Ending("111112", "     As you leave through the open door to a room you don’t know anything about, you walk into an open room with several zombies inside of it. There’s an awkward moment of tension between you and the zombies, before they all rush after you, groaning loudly and angrily.\r\n"
        			+ "      As you run back into the decontamination room, you meet face to face with the zombies from before, who had come back into this room to investigate the loud noise.\r\n"
        			+ "\r\n"
        			+ "      As you find yourself pincered, there’s no way out, and you are torn apart and eaten by the zombies.\r\n"
        			+ "", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            System.out.println(EndingArray[0]);
        	break;
        case "11111112":
           	ending = new Ending("1111112", "As your monkey-brain engages and decides to keep doing the thing you were already doing that wasn’t working, it unsurprisingly doesn’t work, and soon enough the zombies come to investigate the loud sound of metal on concrete, only to find you perched on top of a locker rattling the grate, seemingly as loudly as you can.\r\n"
           			+ "\r\n"
           			+ "      All the zombies have to do is rock the locker a little bit, and you fall backwards off of it into their loving arms. And by “loving arms”, I mean they grab ahold of you and then eat you alive.\r\n"
           			+ "\r\n", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            break;
    	case "112": 
    		ending = new Ending("112", " You grab the flask off the table, and throw it as hard as you can at the zombie, shattering glass into its face. As the liquid makes contact with the zombie’s skin... Nothing. Nothing happens.\r\n"
    				+ "\r\n"
    				+ "      Looking back, the zombie suspended in that same liquid probably should have told you that this wouldn’t do much in the way of harming it. It was probably some sort of weird-looking embalming fluid, or something similar.\r\n"
    				+ "      Oh well, live and learn. Or rather, die and learn, because with that zombie that now suddenly knows you’re here, you won’t be living for much longer.", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
    		break;
    	case "12111111":
    		ending = new Ending("12111111", "   With a newfound arsenal in your pocket, you fight on deep into the night, blasting zombie after zombie back to the grave where they belong. Bullet after bullet, blast after blast. At some point, you find a tank somehow, and blow up. You are death, and you’re sending every zombie you can find back to death.\r\n"
    				+ "\r\n"
    				+ "      However, as the morning comes, so too does the promise of the facility’s detonation. As you watch the morning sun, you gaze into the sunrise and salute, as the entire building goes up in flames.\r\n"
    				+ "\r\n"
    				+ "      Your only regret is that you never got to use the grenade you were saving in your back pocket for most of the fight.\r\n"
    				+ "\r\n"
    				+ "TRUE ENDING 3: KILLER\r\n", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            EndingArray[1] = true;
            item1Text.setText("Grenade");
            item1Image.setImage(
            		new Image("Pack1/images/Grenade.png"));
            System.out.println(EndingArray[1]);
            break;
    	case "12111112":
    		ending = new Ending("12111112", "      Come on, after all this way? Seriously?\r\n"
    				+ "\r\n"
    				+ "      Well, whatever. Your loss, I suppose. As you ponder giving yourself up to the horde of undead, you hesitate, and the choice is made for you with a pair of grotesque, rotten hands that grapple you by the legs, pulling you under the sloshing ocean of groaning mouths.\r\n"
    				+ "\r\n"
    				+ "      You are swallowed beneath the waves, and you do not come back up.\r\n"
    				+ "\r\n"
    				+ "ENDING 11: Anticlimax\r\n", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            break;
    	case "1211112":
    		ending = new Ending("1211112", "      You’re willing to leave people’s stuff where it lies. I can respect that. However, the zombies cannot, and they quickly tear you apart with their teeth and hands. But at least whoever left these weapons here won’t have to clean them later, save for all of your blood that got all over them.\r\n"
    				+ "\r\n"
    				+ "ENDING 10: Respectful of other people’s things\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
            break;
    	case "1212":
    		ending = new Ending("1212","      So, fun fact. The mythbusters tested this exact situation once, right down to the justification remaining as zombies beating down a door. Any given zombie has an average push force of 50 PSI, and with 10 to 15 zombies that becomes anywhere between 500 and 750 PSI beating against that door.\r\n"
    				+ "      For context, steel can withstand anywhere between 300 and 500 PSI, and as a reminder, the entire door doesn’t need to fail, just the hinges.\r\n"
    				+ "\r\n"
    				+ "      Anyways, with the loudness of the banging on the door attracting yet more zombies, the hinges of the door is broken and the door is pushed inwards. With you still on the other side of the door trying to hold it back, you are thoroughly squashed into paste trying to hold back the strength of an uncountable number of zombies.\r\n"
    				+ "\r\n"
    				+ "ENDING 7: Don’t blame me, blame the mythbusters\r\n", "restart", "quit");
           	topText.setText(ending.getDescription());
        	description = ending.getDescription();
    		break;
    	case "1222":
    		ending = new Ending("1222","      Seeing that one of the zombies has its pant leg caught in an escalator, and that the other two are tumbling over the first, you realize that this is the perfect time to get a first strike against them in a dance battle.\r\n"
    				+ "      Thinking quickly, you turn on the boombox, and begin to break out your very best moves. However, as you do so, you trip on a piece of rubble, causing you to fall head-over-heels and faceplant in a pile of your own limbs. Now that you are no longer dancing, the dance fight is over, and it’s now socially appropriate for the zombies to walk over and eat you alive.\r\n"
    				+ "     Typical.\r\n"
    				+ "\r\n"
    				+ "ENDING 12: Have a nice trip\r\n", "restart", "quit");
           	topText.setText(ending.getDescription());
        	description = ending.getDescription();
    		break;
    	case "12212":
    		ending = new Ending("12212","Obviously, the best way to make the door open up is to challenge it to a dance battle. Right as you begin to dance vigorously, you find your footing loose, and you trip over a piece of rubble on the ground, collapsing in a heap.\r\n"
    				+ "      As you get up from your mistake, you realize that with a start like that, there’s no WAY you could exist. Solemnly, you shuffle out of the salon thinking about where it all went wrong. Did you start with the wrong trick? Should you have stretched before you started? Your mother was right, that dance class WAS a waste of time.\r\n"
    				+ "\r\n"
    				+ "      As you sadly shuffle out of the barbershop, you don’t even have time to recognize that the zombies that left less than thirty seconds ago are still there, only noticing when they start eating you like a TV dinner.\r\n"
    				+ "      Typical.\r\n"
    				+ "\r\n"
    				+ "ENDING 13: Tripping over your words\r\n","restart", "quit");
           	topText.setText(ending.getDescription());
        	description = ending.getDescription();
    		break;
    	case "122112":
    		ending = new Ending("122112","      Your brilliant mind comes up with another brilliant idea: A dance battle.\r\n"
    				+ "      Before you start to dance, however, you begin to stretch. Stretch your calves, stretch your arms, stretch your legs. You wouldn’t want to pull a muscle during your dance.\r\n"
    				+ "      Then, you begin to tie your shoes, settle your outfit, this and that. You wouldn’t want to dance without a fitting outfit.\r\n"
    				+ "     \r\n"
    				+ "      Then, finally, once everything’s nice and ready, you begin to dance. You dance and you dance, like nobody’s watching. But just as you start getting into it, in an unforeseen twist, your foot gets caught on a piece of rubble beneath you, and your dance falls apart. You begin to stumble, you begin to trip, and you start to lean backwards and fall, but just in the nick of time, you catch yourself on the railing.\r\n"
    				+ "      Then, it dawns on you. Come to think of it, you’re not actually sure how dancing helps in this situation.\r\n"
    				+ "      Typical.\r\n"
    				+ "\r\n"
    				+ "ENDING 14: Something something, tripping pun.\r\n","restart", "quit");
           	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            break;
    	case "1221111":
    		ending = new Ending("1221111","You subconsciously kick away a piece of rubble near your feet, before you begin dancing like you’ve never danced before, busting out your best moves in front of the lifeless undead. One of them begins to tap their foot, then another, then another, and soon enough, a full-blown dance party breaks out, with you as the life of it.\r\n"
    				+ "\r\n"
    				+ "\r\n"
    				+ "      Somewhere in the middle, a particularly inebriated undead hands you their keys before they head home. Said something along the lines of “GRRRUUUUUUH”. They probably meant something about not wanting to drive drunk. Oh well, it’s probably fine. Some of these keys look important.\r\n"
    				+ "\r\n"
    				+ "     Yessir, when the party starts to die down once the sun starts to come up, you’re sure that every zombie there will remember your name for years. You’re just getting to the part where high-fives are being handed out when the building’s remote detonation you’d forgotten about begins and blows you to smithereens.\r\n"
    				+ "      Oh, well. Typical.\r\n"
    				+ "\r\n"
    				+ "TRUE ENDING 4: CLUMSY\r\n","restart", "quit");
           	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            EndingArray[2] = true;
            item3Text.setText("Key");
            item3Image.setImage(
            		new Image("Pack1/images/Keychain.png"));
            System.out.println(EndingArray[2]);
            break;
    	case "1221112":
    		ending = new Ending("1221112","You subconsciously kick away a piece of rubble near your feet, before you begin dancing like you’ve never danced before, busting out your best moves in front of the lifeless undead. One of them begins to tap their foot, then another, then another, and soon enough, a full-blown dance party breaks out, with you as the life of it.\r\n"
    				+ "\r\n"
    				+ "\r\n"
    				+ "      Somewhere in the middle, a particularly inebriated undead hands you their keys before they head home. Said something along the lines of “GRRRUUUUUUH”. They probably meant something about not wanting to drive drunk. Oh well, it’s probably fine. Some of these keys look important.\r\n"
    				+ "\r\n"
    				+ "     Yessir, when the party starts to die down once the sun starts to come up, you’re sure that every zombie there will remember your name for years. You’re just getting to the part where high-fives are being handed out when the building’s remote detonation you’d forgotten about begins and blows you to smithereens.\r\n"
    				+ "      Oh, well. Typical.\r\n"
    				+ "\r\n"
    				+ "TRUE ENDING 4: CLUMSY\r\n","restart", "quit");
           	topText.setText(ending.getDescription());
        	description = ending.getDescription();
            EndingArray[2] = true;
            System.out.println(EndingArray[2]);
            break;
    	case "12112":
    		ending = new Ending("12112","\r\n"
    				+ "      As you pick up the grenade launcher from the rack and fire it at the zombies pouring through the door that is directly in front of you, you begin to realize that there’s no such thing as a melee-friendly grenade.\r\n"
    				+ "\r\n"
    				+ "      As the grenade hits against the zombies in front of you, the resulting explosion throws your body into the back wall. But hey, at least you took out a bunch of zombies with you.\r\n"
    				+ "      Oops.\r\n"
    				+ "\r\n"
    				+ "ENDING 8: Whoops.\r\n", "restart", "quit");
           	topText.setText(ending.getDescription());
        	description = ending.getDescription();
    		break;
    	case "21":
			ending = new Ending("21", "      You try to hide, ducking back into the building and hiding behind the wall. Unfortunately, despite being rotten and decrepit, the zombies retain their object permanence, watching you do this before turning the corner and digging into their new meal.\r\n"
					+ "\r\n"
					+ "      The last thing to pass through your head - besides the teeth of a zombie - is the idea that you probably could have found more resources deeper inside of the building you were in, or maybe a safer method of escape. If only you could go back somehow…\r\n"
					+ "\r\n"
					+ "ENDING 15: Inattentive\r\n", "restart", "quit" );
			System.out.println(ending.getDescription());
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
    		break;
    	case "22a":
			ending = new Ending("22a", "      With nothing prepared to defend yourself with, you are quickly overwhelmed by the ensuing swarm of zombies, being pulled to the ground and eaten alive by a thousand rotted mouths.\r\n"
					+ "\r\n"
					+ "      The last thing to pass through your head - besides the teeth of a zombie - is the idea that you probably could have found more resources deeper inside of the building you were in, or maybe a safer method of escape. If only you could go back somehow…\r\n"
					+ "\r\n"
					+ "ENDING 16: Escape Artist\r\n", "restart", "quit" );
        	topText.setText(ending.getDescription());
        	description = ending.getDescription();
    		break;
    	case "221":
			ending = new Ending("221", "      Fleeing back into the building, you run away, as fast as your legs can carry you, only to encounter a hallway full of more zombies. While they didn’t notice you before, they definitely noticed the loud explosion noise that just erupted from outside.\r\n"
					+ "      Quickly, you are overwhelmed, grabbed onto and torn apart by a thousand hungry mouths. Maybe running back into the building where you knew zombies were waiting for you wasn’t such a good idea after all.\r\n"
					+ "\r\n"
					+ "ENDING 17: Forgetful\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
    		break;
    	case "2221":
			ending = new Ending("2221", "As you try to smash into door, you realize that you don’t have a lot of hope with this method. You can’t break the door down very easily, as it’s heavy and very sturdily-built metal door. There’s not any real way to break it down with what you have on-hand. You’re still slamming against it repeatedly with your shoulder when the zombies make their way behind you, trapping you cornered at the bottom of the stairs.\r\n"
					+ "\r\n"
					+ "ENDING 18: Weak\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
    		break;
    	case "2222a":
			ending = new Ending("2222a", "As you try to attack the door’s hinges, you realize that the door’s more sturdy than it looks, and you can’t truly get a good grip on the hinges. There’s no good way to remove the door from its hinges, nor the hinges from the frame, and you soon find yourself overwhelmed by the zombies that were chasing behind you.\r\n"
					+ "\r\n"
					+ "      As the life leaves your eyes, you wish to yourself that you’d had the foresight to bring something to remove the door’s hinges with.\r\n"
					+ "\r\n"
					+ "ENDING 19: Poor planner\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
    		break;
    	case "22221":
			ending = new Ending("22221", "As you tuck yourself beneath the metal door, you realize that it’s very heavy, being made of solid metal and all. Oh well, nothing you can’t handle for a short amount of time.\r\n"
					+ "      As the zombies descend the staircase to your level, you hear them shamble towards the doorway, and then feel a rapidly-increasing amount of weight step onto the door to walk through the doorway. With the weight of twenty to thirty zombies standing on top of the already-very-heavy door, your muscle tissue is turned into a thick red paste, which leaks from beneath the door as your body is crushed beneath the immense weight.\r\n"
					+ "\r\n"
					+ "ENDING 20: Hindsight is 20/20\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
    		break;
    	case "22222221":
			ending = new Ending("22222221", "You wouldn’t want to crash now, would you? Of course not. So, you instead start to drive carefully, abiding the speed limits of the surrounding area, using your turn signals before you turn, and making sure to stop at red lights.\r\n"
					+ "      Unfortunately for you, the zombies do not share your concern for roadside safety, and would much rather eat your brains. You end up dying when you try to stop for a zombie that was walking across a crosswalk.\r\n"
					+ "      Well, at least that zombie was thankful.\r\n"
					+ "\r\n"
					+ "ENDING 25: For the bit\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
    		break;
        case "2222221":
			ending = new Ending("22222221", "      As you try to hotwire the helicopter, you try your best to hotwire it, but you realize something during it: Hotwiring a vehicle takes a minute or two, which is more time than you have right now. With the zombie horde encroaching, you only have just enough time to pop the hood open and find the right wires before you’re being chewed on.\r\n"
					+ "      In your final moments, you only wish you’d somehow had the key to the ignition.\r\n"
					+ "\r\n"
					+ "ENDING 22: Time management expert\r\n"
					+ "\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
        	break;
        case "2222222a":
			ending = new Ending("2222222a", "As you try for the keys, you check your pockets and realize something: You don’t have them. You have no reason to have had the keys to this specific vehicle, so you’re not sure why you’d thought to try using the keys which you don’t have.\r\n"
					+ "      As the zombies encroach upon the vehicle, they open the door, flooding into the vehicle and devouring you whole.\r\n"
					+ "      In your final moments, you only wish you’d somehow had the key to the ignition.\r\n"
					+ "\r\n"
					+ "ENDING 23: Unprepared\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
        	break;
    	case "22222222":
			ending = new Ending("22222222", " As you crash through the gates of the concrete complex, running over zombie after zombie, the armored vehicle treats you well, holding strong against the mistreatment of zombies underneath your tires. You hear the protesting groans of undead masses throw themselves against your vehicle, but there’s nothing they can do to stop or even slow your escape.\r\n"
					+ "      Eventually, as you drive further and further away from the complex, the bumpy roads of the many zombies attempting to stop your escape begin to smooth out. Eventually, the cracked asphalt roads begin to smooth out to something more normal, more familiar.\r\n"
					+ "      Signs of civilization begin to appear, buildings and road signs become more common, until you find yourself in a fully-fledged town, with real buildings, real vehicles, real people walking their real dogs down the real sidewalks. It’s perfect, just like you remembered.\r\n"
					+ "\r\n"
					+ "      A tear falls down your face. Finally, you’ve found yourself away from that wretched place. You’ll never have to see another zombie ever again.\r\n"
					+ "\r\n"
					+ "TRUE ENDING 1: ALIVE\r\n", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
    		break;
        case "222221":
			ending = new Ending("222221", "As you rear back your fist and punch the zombie in the mouth, you feel the sickening feeling of rotten teeth biting down onto your bare knuckles. You try to pull your hand away, but the zombie bites down harder, pulling you further from the vehicle, and closer towards the encroaching hoard. As you are pulled into the hoard’s collective mass, you are quickly overwhelmed and eaten.\r\n"
					+ "      In hindsight, the mouth was probably the exact worse place to punch a zombie in. Even if you’d escaped, you likely would’ve been infected anyways.\r\n"
					+ "\r\n"
					+ "ENDING 21: What did you think would happen?\r\n"
					+ "", "restart", "quit" );
        	description = ending.getDescription();
        	topText.setText(ending.getDescription());
    		break;
        case "121112":			ending = new Ending("222221", "      As you pull the trigger on your shotgun, trying to stop the incoming zombie from stealing your gun from you, expecting a resounding “BLAM” to meet you, you are instead met with a pitiful“click”. You suddenly remember the reason you’d tried to step back: Your gun needs to reload.\r\n"
        		+ "\r\n"
        		+ "      With no more bullets to stop the zombie from rushing you down, it grabs onto your shoulder and bites down, HARD. Quickly, you are overrun with more, and more zombies rush in to finish the job.\r\n"
        		+ "\r\n"
        		+ "ENDING 9: Forgot how to count\r\n", "restart", "quit" );
    	description = ending.getDescription();
    	topText.setText(ending.getDescription());
    	break;
        	
    		
        }
    	Button1.setText("Restart");
    	Button2.setText("Quit");
        Button1.setOnAction(e -> startNewGame());
        Button2.setOnAction(e -> Platform.exit());
        
        
}
    

}
