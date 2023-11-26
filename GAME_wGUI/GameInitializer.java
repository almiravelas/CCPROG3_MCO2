import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameInitializer {

    private Inventory playerInventory;
    private JFrame frame;

    public GameInitializer(JFrame frame, Inventory playerInventory) {
        this.frame = frame;
        this.playerInventory = playerInventory;
    }

    public void initializeGame() {
        final String[] STARTER_CREATURE_NAMES = {
            "STRAWANDER", "CHOCOWOOL", "PARFWIT", "BROWNISAUR", "FRUBAT",
            "MALTS", "SQUIRPIE", "CHOCOLITE", "OSHACONE"
        };

        displayStarterMessage(STARTER_CREATURE_NAMES);
    }

    private void displayStarterMessage(String[] STARTER_CREATURE_NAMES) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("Choose your starter creature:"));

        for (int i = 0; i < STARTER_CREATURE_NAMES.length; i++) {
            int choice = i;
            JButton button = new JButton(STARTER_CREATURE_NAMES[i]);
            button.addActionListener(e -> handleStarterChoice(choice + 1, STARTER_CREATURE_NAMES));
            panel.add(button);
        }

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void handleStarterChoice(int starterChoice, String[] STARTER_CREATURE_NAMES) {
        Creatures startCreature = loadStarterFile(starterChoice);
        addStarter(playerInventory, startCreature);
        JOptionPane.showMessageDialog(frame, "You chose: " + startCreature.getName());
    }

    private Creatures loadStarterFile(int choice) {
        Creatures starterCreature = null;
        try (BufferedReader br = new BufferedReader(new FileReader("EL1.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == choice - 1) {
                    // Splitting the line and creating the creature
                    // ...
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return starterCreature;
    }

    private void addStarter(Inventory inventory, Creatures starterCreature) {
        inventory.addCreature(starterCreature);
        inventory.changeActiveCreature(starterCreature);
    }
}
