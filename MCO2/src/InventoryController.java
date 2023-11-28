import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryController implements ActionListener {
    private Inventory inventory;
    private InventoryView inventoryView;

    public InventoryController(Inventory inventory, InventoryView inventoryView) {
        this.inventory = inventory;
        this.inventoryView = inventoryView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getActionCommand().equals("Change Active Creature")) {
            // Show a dialog with a list of creatures to choose from
            String[] creatureNames = inventory.getCreatures().stream()
                    .map(Creatures::getName)
                    .toArray(String[]::new);

            String selectedCreature = (String) JOptionPane.showInputDialog(
                    inventoryView.frame,
                    "Choose a creature to set as the active creature:",
                    "Change Active Creature",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    creatureNames,
                    creatureNames[0]);

            // Update the active creature in the inventory
            if (selectedCreature != null) {
                Creatures newActiveCreature = inventory.getCreatures().stream()
                        .filter(creature -> creature.getName().equals(selectedCreature))
                        .findFirst()
                        .orElse(null);

                if (newActiveCreature != null) {
                    inventory.changeActiveCreature(newActiveCreature);
                    inventoryView.updateInventory();
                }
            }
        }
    }
}
