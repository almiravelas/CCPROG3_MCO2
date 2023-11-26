import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    private JPanel cards;
    private static final String MAIN_MENU = "Main Menu";
    private static final String AREA_SCREEN = "Areas";
    private static final String INVENTORY_SCREEN = "Inventory";
    private static final String EVOLUTION_SCREEN = "Evolution";
    // private static final String EXPLORE_AREA = "AREA EXPLORE";
    private GameLoop gameLoop;

    public GameGUI(Inventory playerInventory) {
        this.gameLoop = new GameLoop(this, playerInventory);

                this.setTitle("GAME GUI - MENU");
                this.setResizable(false);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setSize(728,455);
                this.setLayout(new FlowLayout());

                cards = new JPanel(new CardLayout());
                MainMenuCard mainMenuCard = new MainMenuCard(
                    e -> showCard(AREA_SCREEN),
                    e -> showCard(INVENTORY_SCREEN),
                    e -> showCard(EVOLUTION_SCREEN)
                );

        InventoryScreen inventoryScreenCard = new InventoryScreen(
            e -> showCard(MAIN_MENU));
        AreaScreen areaScreenCard = new AreaScreen(
            e -> showCard(MAIN_MENU),
            e -> gameLoop.handleExploreArea(1),
            e -> gameLoop.handleExploreArea(2),
            e -> gameLoop.handleExploreArea(3)
            
            );
        EvolutionScreen evolutionScreenCard = new EvolutionScreen(
            e -> showCard(MAIN_MENU));

        cards.add(mainMenuCard, MAIN_MENU);
        cards.add(areaScreenCard, AREA_SCREEN);
        cards.add(inventoryScreenCard, INVENTORY_SCREEN);
        cards.add(evolutionScreenCard, EVOLUTION_SCREEN);

        this.add(cards);
        this.setVisible(true);
    }

    private void showCard(String card){
        CardLayout c1 = (CardLayout)(cards.getLayout());
        c1.show(cards, card);
    }
}


//CLASS OF THE MAINCARD
class MainMenuCard extends JPanel{

    private JButton areaButton;
    private JButton inventoryButton;
    private JButton evolveButton; 

    public MainMenuCard(ActionListener areaListener, ActionListener inventoryListener, ActionListener evolveListener){
        areaButton = new JButton("Area");
        areaButton.addActionListener(areaListener);
        this.add(areaButton);

        inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(inventoryListener);
        this.add(inventoryButton);

        evolveButton = new JButton("Evolution");
        evolveButton.addActionListener(evolveListener);
        this.add(evolveButton);
    }
}


//CLASSES FOR EACH CARDS: AREA, INVENTORY, EVOLUTION with their Buttons
//that goes exits back to the Main Menu
class AreaScreen extends JPanel{
    private JButton backButton;
    private JButton area1Button;
    private JButton area2Button;
    private JButton area3Button;

    public AreaScreen(ActionListener backListener, ActionListener area1Listener, 
                      ActionListener area2Listener, 
                      ActionListener area3Listener){
        this.add(
        new JLabel("Welcome to the Area\n\nChoose which Area you want to explore:"));

        area1Button = new JButton("Area 1");
        area1Button.addActionListener(area1Listener);

        area2Button = new JButton("Area 2");
        area2Button.addActionListener(area2Listener);

        area3Button = new JButton("Area 3");
        area3Button.addActionListener(area3Listener);

        backButton = new JButton("Back to Menu");
        backButton.addActionListener(backListener);
        
        this.add(area1Button);
        this.add(area2Button);
        this.add(area3Button);
        this.add(backButton);
    }

}