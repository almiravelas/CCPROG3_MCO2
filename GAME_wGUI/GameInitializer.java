import javax.swing.*;
import java.awt.*;
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
        JPanel panel = new JPanel(new GridLayout(STARTER_CREATURE_NAMES.length + 1, 1));
        panel.add(new JLabel("Choose your starter creature:"));

        for (int i = 0; i < STARTER_CREATURE_NAMES.length; i++) {
            JButton button = new JButton(STARTER_CREATURE_NAMES[i]);
            int finalI = i;
            button.addActionListener(e -> handleStarterChoice(finalI + 1));
            panel.add(button);
        }

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void handleStarterChoice(int starterChoice) {
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
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String type = parts[1];
                    String family = parts[2];
                    String image = parts[3];
                    int evolutionLevel = Integer.parseInt(parts[4]);
                    int health = 100;

                    starterCreature = new Creatures(name, type, family, image, evolutionLevel, health);
                }
                i++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "File not found: EL1.txt", "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return starterCreature;
    }

    private void addStarter(Inventory inventory, Creatures starterCreature) {
        inventory.addCreature(starterCreature);
        inventory.changeActiveCreature(starterCreature);
    }
}
