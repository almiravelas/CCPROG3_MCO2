import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInitializerController implements ActionListener {
    private GameInitializer gameInitializer;
    private GameInitializerView gameInitializerView;

    public GameInitializerController(GameInitializer gameInitializer, GameInitializerView gameInitializerView) {
        this.gameInitializer = gameInitializer;
        this.gameInitializerView = gameInitializerView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameInitializer.initializeGame();
        // Close the GameInitializerView after initialization
        gameInitializerView.close();
    }
}
