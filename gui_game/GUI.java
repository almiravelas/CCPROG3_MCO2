import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI{
    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> new GameLayout().createAndShowGUI());
            
        }
}

class GameLayout{

    private JFrame frame;
    private JPanel cards;
    private static final String MAIN_MENU = "Main Menu";
    private static final String AREA_SCREEN = "Areas";
    private static final String INVENTORY_SCREEN = "Inventory";

    public void createAndShowGUI(){
        frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(728,455);
        frame.setLayout(null);

        ImageIcon image = new ImageIcon("C:\\Users\\63935\\Downloads\\386577_stardoge_8-bit-pokeball.png");
        frame.setIconImage(image.getImage());

        BackgroundPanel bgPanel = new BackgroundPanel("C:\\Users\\63935\\Downloads\\pokemon_firstimage.jpg");
        frame.setContentPane(bgPanel);

        cards = new JPanel(new CardLayout()); //I dont get it

        MainMenuCard mainMenuCard = new MainMenuCard(
            e -> showCard(AREA_SCREEN),
            e -> showCard(INVENTORY_SCREEN)
        );

        AreaScreen areaScreenCard = new AreaScreen(
            e -> showCard(MAIN_MENU));

        InventoryScreen inventoryScreenCard = new InventoryScreen( e -> showCard(MAIN_MENU));

        cards.add(mainMenuCard, MAIN_MENU);
        cards.add(areaScreenCard, AREA_SCREEN);
        cards.add(inventoryScreenCard, INVENTORY_SCREEN);
        

        frame.getContentPane().add(cards, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    private void showCard(String cardName){
        CardLayout c1 = (CardLayout) (cards.getLayout());
        c1.show(cards, cardName);
    }
}

class MainMenuCard extends JPanel{

    private JButton areaButton;
    private JButton inventoryButton;

    public MainMenuCard(ActionListener areaListener, ActionListener inventoryListener){
        areaButton = new JButton("Area");
        areaButton.addActionListener(areaListener);
        this.add(areaButton);

        inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(inventoryListener);
        this.add(inventoryButton);
    }
}

class AreaScreen extends JPanel{
    private JButton backButton;

    public AreaScreen(ActionListener backListener){
        this.add(new JLabel("Welcom to the Area"));
        backButton = new JButton("Back to Menu");
        backButton.addActionListener(backListener);
        this.add(backButton);
    }

}

class InventoryScreen extends JPanel{

    private JButton backButton;

    public InventoryScreen(ActionListener backListener){
        this.add(new JLabel("Welcome to the Inventory Screen"));
        backButton = new JButton("Back to the Menu");
        backButton.addActionListener(backListener);
        this.add(backButton);
    }
}