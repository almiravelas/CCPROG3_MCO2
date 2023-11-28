import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInitializerView {
    private JFrame frame;

    public GameInitializerView(GameInitializer gameInitializer) {
        frame = new JFrame("Game Initializer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new BorderLayout());

        JButton initializeGameButton = new JButton("Initialize Game");
        panel.add(initializeGameButton, BorderLayout.CENTER);

        frame.add(panel);

        // Controller
        GameInitializerController controller = new GameInitializerController(gameInitializer, this);
        initializeGameButton.addActionListener(controller);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        frame.dispose();
    }
}
