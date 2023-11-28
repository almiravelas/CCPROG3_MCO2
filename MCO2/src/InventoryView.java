import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InventoryView {
    JFrame frame;
    private Inventory inventory;

    public InventoryView(Inventory inventory) {
        this.inventory = inventory;

        frame = new JFrame("Inventory");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton changeActiveCreatureButton = new JButton("Change Active Creature");
        panel.add(changeActiveCreatureButton, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Display inventory content in the JTextArea
        textArea.setText(getInventoryText(inventory));

        frame.add(panel);

        // Controller
        InventoryController controller = new InventoryController(inventory, this);
        changeActiveCreatureButton.addActionListener(controller);

        frame.setVisible(true);
    }

    public void updateInventory() {
        frame.repaint();
    }

    // Helper method to get the inventory content as text
    private String getInventoryText(Inventory inventory) {
        StringBuilder sb = new StringBuilder();

        sb.append("Inventory: ");

        if (inventory.hasChosenStarterCreature() && inventory.getActiveCreature() != null) {
            // Display the active creature
            sb.append("\n\n=== Active Creature ===\n");
            sb.append("Name: ").append(inventory.getActiveCreature().getName()).append("\n");
            sb.append("Type: ").append(inventory.getActiveCreature().getType()).append("\n");
            sb.append("Family: ").append(inventory.getActiveCreature().getFamily()).append("\n");
            sb.append("EL: ").append(inventory.getActiveCreature().getEvolutionLevel()).append("\n");
            sb.append("Health: ").append(inventory.getActiveCreature().getHealth()).append("\n");
            sb.append(inventory.getActiveCreature()).append("\n");
        } else {
            sb.append("\n\nNo active creature selected.\n");
        }

        if (!inventory.getCreatures().isEmpty()) {
            // Display all creatures
            sb.append("\n=== All Creatures ===\n");
            int i = 0;
            for (Creatures creature : inventory.getCreatures()) {
                if (creature != inventory.getActiveCreature()) {
                    sb.append("\nCreature ").append(++i).append(":\n");
                    sb.append("Name: ").append(creature.getName()).append("\n");
                    sb.append("Type: ").append(creature.getType()).append("\n");
                    sb.append("Family: ").append(creature.getFamily()).append("\n");
                    sb.append("EL: ").append(creature.getEvolutionLevel()).append("\n");
                    sb.append("opt Health: ").append(creature.getHealth()).append("\n");
                    sb.append(creature).append("\n");
                }
            }
        } else {
            sb.append("\nInventory is empty.\n");
        }

        return sb.toString();
    }
}
