package Pack1;

public class AdventureEntry {
    private long minutes;
    private long seconds;

    public AdventureEntry(long minutes, long seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getSeconds() {
        return seconds;
    }
}