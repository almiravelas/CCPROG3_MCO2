import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameGUI extends JFrame {

    // Main frame components
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    // Components for the main menu
    private JPanel mainMenuPanel = new JPanel();
    private JLabel welcomeLabel = new JLabel("WELCOME TO THE GAME");
    private JButton startButton = new JButton("Start Game");

    // Components for the creature selection
    private JPanel creatureSelectionPanel = new JPanel();
    private JLabel creatureLabel = new JLabel("Choose your starter creature:");
    private JComboBox<String> creatureComboBox = new JComboBox<>(new String[]{
        "STRAWANDER", "CHOCOWOOL", "PARFWIT", "BROWNISAUR", "FRUBAT",
        "MALTS", "SQUIRPIE", "CHOCOLITE", "OSHACONE"
    });
    private JButton selectCreatureButton = new JButton("Select Creature");

    // Components for the exploration area
    private JPanel areaPanel = new JPanel();
    private JLabel areaLabel = new JLabel();
    private JButton[] movementButtons = new JButton[5];
    private String[] movementOptions = {"UP", "DOWN", "LEFT", "RIGHT", "EXIT"};

    // Game state variables
    private int areaWidth = 5;
    private int areaHeight = 1;
    private int playerRow = 0;
    private int playerCol = 0;
    private char[][] area;
    private String selectedCreature = "";


       // Constructor for setting up the game GUI
    public GameGUI() {
        // Setup the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setTitle("Game");

        initializeComponents();
        layoutComponents();
        addListeners();


        // Add cardPanel to the main frame
        add(cardPanel);
        cardLayout.show(cardPanel, "Main Menu");

    }

    // Initializes the game area
    private void initializeArea() {
        area = new char[areaHeight][areaWidth];
        for (char[] row : area) {
            Arrays.fill(row, '_');
        }
        area[0][0] = 'P'; // Player's starting position
        updateAreaDisplay();
    }

    // Initializes components and the game area
    private void initializeComponents() {
        initializeArea();
    }

    // Lays out the components in their respective panels
    private void layoutComponents() {
        layoutMainMenu();
        layoutCreatureSelection();
        layoutAreaPanel();

        cardPanel.add(mainMenuPanel, "Main Menu");
        cardPanel.add(creatureSelectionPanel, "Creature Selection");
        cardPanel.add(areaPanel, "Area");
    }

    // Lays out the Main Menu panel
    private void layoutMainMenu() {
        mainMenuPanel.setLayout(new BorderLayout());
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        mainMenuPanel.add(welcomeLabel, BorderLayout.CENTER);
        mainMenuPanel.add(startButton, BorderLayout.PAGE_END);
    }

    // Lays out the Creature Selection panel
    private void layoutCreatureSelection() {
        creatureSelectionPanel.setLayout(new FlowLayout());
        creatureSelectionPanel.add(creatureLabel);
        creatureSelectionPanel.add(creatureComboBox);
        creatureSelectionPanel.add(selectCreatureButton);
        creatureLabel.setHorizontalAlignment(JLabel.CENTER);
        creatureLabel.setVerticalAlignment(JLabel.CENTER);
        addAreaSizeButtons();
    }

    // Lays out the Area Panel
    private void layoutAreaPanel() {
        areaPanel.setLayout(new GridLayout(3, 3));
        areaPanel.add(areaLabel);
        areaLabel.setHorizontalAlignment(JLabel.CENTER);
        areaLabel.setVerticalAlignment(JLabel.CENTER);
        areaPanel.add(createMovementButtonPanel());
    }

    private void addListeners() {
        startButton.addActionListener(e -> cardLayout.show(cardPanel, "Creature Selection"));
        selectCreatureButton.addActionListener(e -> {
            selectedCreature = (String) creatureComboBox.getSelectedItem();
            areaLabel.setText("<html><div style='text-align: center;'>You chose: " + selectedCreature + "<br><br>Explore the area:</div></html>");
            cardLayout.show(cardPanel, "Area");
        });

        for (int i = 0; i < movementButtons.length; i++) {
            int finalI = i;
            movementButtons[i].addActionListener(e -> handleMovement(finalI));
        }
    }

        // Adds buttons to set the area size
        private void addAreaSizeButtons() {
            addButtonForAreaSize("1x5 Area", 5, 1);
            addButtonForAreaSize("4x4 Area", 4, 4);
            addButtonForAreaSize("5x5 Area", 5, 5);
        }

           // Helper method to add a button for setting the area size
    private void addButtonForAreaSize(String buttonText, int width, int height) {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> {
            setAreaSize(width, height);
            initializeArea(); // Reset the area to the new size
        });
        creatureSelectionPanel.add(button);
    }

    // Sets the size of the game area
    private void setAreaSize(int width, int height) {
        this.areaWidth = width;
        this.areaHeight = height;
        initializeComponents();
    }

    private void handleMovement(int movementType) {
            // Clear current position
    area[playerRow][playerCol] = '_';
        // Implement movement logic
        switch (movementType) {
            case 0: // UP
            playerRow = Math.max(playerRow - 1, 0);
            break;
        case 1: // DOWN
            playerRow = Math.min(playerRow + 1, areaHeight - 1);
            break;
        case 3: // RIGHT
            playerCol = Math.min(playerCol + 1, areaWidth - 1);
            break;
        case 2: // LEFT
            playerCol = Math.max(playerCol - 1, 0);
            break;
        case 4: // EXIT
            cardLayout.show(cardPanel, "Main Menu");
            return;
        }
            // Set new position
    area[playerRow][playerCol] = 'P';

    updateAreaDisplay();
    }

    private JPanel createMovementButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < movementButtons.length; i++) {
            movementButtons[i] = new JButton(movementOptions[i]);
            buttonPanel.add(movementButtons[i]);
        }
        return buttonPanel;
    }
    
    public void updateAreaDisplay() {
        StringBuilder areaDisplay = new StringBuilder("<html>Area:<br>");
        for (int row = 0; row < area.length; row++) {
            for (int col = 0; col < area[0].length; col++) {
                areaDisplay.append(area[row][col] == 'P' ? "[P]" : "[_]");
            }
            areaDisplay.append("<br>");
        }
        areaDisplay.append("</html>");
        areaLabel.setText(areaDisplay.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI().setVisible(true));
    }

}
