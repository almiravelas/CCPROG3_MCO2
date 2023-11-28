import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {

    private Inventory inventory;

    public MainMenuController(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "View Inventory":
                new InventoryView(inventory);
                break;
            case "Explore an Area":
                // Handle Explore an Area action
                System.out.println("Explore an Area selected");
                break;
            case "Evolve a Creature":
                // Handle Evolve a Creature action
                System.out.println("Evolve a Creature selected");
                break;
            case "Exit":
                // Handle Exit action
                System.exit(0);
                break;
            default:
                break;
        }
    }
}