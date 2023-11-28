import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ModuleLayer.Controller;


public class MainMenuView {
    private JFrame frame;
    private Inventory inventory;

    public MainMenuView(Inventory inventory) {
        frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton viewInventoryButton = new JButton("View Inventory");
        JButton exploreAreaButton = new JButton("Explore an Area");
        JButton evolveCreatureButton = new JButton("Evolve a Creature");
        JButton exitButton = new JButton("Exit");

        panel.add(viewInventoryButton);
        panel.add(exploreAreaButton);
        panel.add(evolveCreatureButton);
        panel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Controller
        MainMenuController controller = new MainMenuController(inventory);
        viewInventoryButton.addActionListener(controller);
        exploreAreaButton.addActionListener(controller);
        evolveCreatureButton.addActionListener(controller);
        exitButton.addActionListener(controller);
    }
}
