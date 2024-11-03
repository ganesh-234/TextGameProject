package Pack1;

public class Ending {
    private String endingString;
    private String description;
    private String restart;
    private String quit;

    public Ending(String endingString, String description, String restart, String quit) {
        this.endingString = endingString;
        this.description = description;
        this.restart = "Start Again?";
        this.quit = "Go Back";
        
    }

    public String getDescription() {
        return description;
    }

    public String getEndingString() {
        return endingString;
    }
    public String getRestart() {
        return restart;
    }

    public String getQuit() {
        return quit;
    }
}
