package Pack1;

public class MyScene {
	
	    private String description;
	    private String choice1Description;
	    private String choice2Description;
	    private int choice;

	    public MyScene(String description, String choice1Description, String choice2Description, int choice) {
	        this.description = description;
	        this.choice1Description = choice1Description;
	        this.choice2Description = choice2Description;
	        this.choice = choice;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public String getChoice1Description() {
	        return choice1Description;
	    }

	    public String getChoice2Description() {
	        return choice2Description;
	    }

	    public int getChoice() {
	        return choice;
	    }
	}
