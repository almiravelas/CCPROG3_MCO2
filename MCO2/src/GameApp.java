import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.text.View;

public class GameApp {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        GameInitializer gameInitializer = new GameInitializer(new Scanner(System.in), inventory);

        // Initialize the GameInitializerView first
        SwingUtilities.invokeLater(() -> {
            GameInitializerView gameInitializerView = new GameInitializerView(gameInitializer);
            gameInitializerView.show();
        });

        // Then initialize the MainMenuView
        SwingUtilities.invokeLater(() -> {
            new MainMenuView(inventory);
        });
    }
}