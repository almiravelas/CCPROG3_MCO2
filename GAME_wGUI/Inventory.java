import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Creatures> creatures;
    private Creatures activeCreature;
    private boolean hasChosenStarterCreature; // Flag to track the starter creature
    private JFrame frame;

    public Inventory(JFrame frame) {
        this.frame = frame;
        creatures = new ArrayList<>();
        hasChosenStarterCreature = false;
    }

    public void addCreature(Creatures creature) {
        creatures.add(creature);
    }

    public void changeActiveCreature(Creatures creature) {
        activeCreature = creature;
        hasChosenStarterCreature = true;
        updateInventoryDisplay(); // Update the GUI display
    }

    public ArrayList<Creatures> getCreatures() {
        return creatures;
    }

    public Creatures getActiveCreature() {
        return activeCreature;
    }

    public void listAll() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        if (hasChosenStarterCreature && activeCreature != null) {
            panel.add(new JLabel("=== Active Creature ==="));
            panel.add(new JLabel("Name: " + activeCreature.getName()));
            // Add more labels for other properties
        } else {
            panel.add(new JLabel("No active creature selected."));
        }

        if (!creatures.isEmpty()) {
            panel.add(new JLabel("=== All Creatures ==="));
            for (Creatures creature : creatures) {
                if (creature != activeCreature) {
                    panel.add(new JLabel("Name: " + creature.getName()));
                    // Add more labels for other properties
                }
            }
        } else {
            panel.add(new JLabel("Inventory is empty."));
        }

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void updateInventoryDisplay() {
        // Call listAll or any other method to refresh the GUI
        listAll();
    }

    // Additional GUI-related methods...
}
