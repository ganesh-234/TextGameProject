
package Pack1;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private List<AdventureEntry> entries;

    public Scoreboard() {
        entries = new ArrayList<>();
    }

    public void addEntry(AdventureEntry entry) {
        entries.add(entry);
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adventure Scoreboard:\n");
        sb.append("-------------------------------\n");
        for (int i = 0; i < entries.size(); i++) {
            AdventureEntry entry = entries.get(i);
            sb.append(String.format("Adventure %d: Time taken - %d minutes %d seconds\n", 
                                    i + 1, entry.getMinutes(), entry.getSeconds()));
        }
        sb.append("-------------------------------\n");
        return sb.toString();
    }
    
    public List<AdventureEntry> getEntries() {
        return new ArrayList<>(entries);
    }
}
