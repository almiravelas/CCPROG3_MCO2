import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Areas extends JPanel {

    private String[][] area;
    private int playerRow = 0;
    private int playerCol = 0;
    private ArrayList<Creatures> creatures;
    private BattlePhase battle;
    private Random rand = new Random();
    private GameLoop gameLoop;
    private boolean inArea = true;
    
    public Areas(int rows, int cols, String creaturesFile, GameLoop gameLoop) {
        this.gameLoop = gameLoop;
        this.setLayout(new GridLayout(rows, cols));

        area = new String[rows][cols];
        this.creatures = CreatureFactory.initializeCreatures(creaturesFile);
        this.battle = new BattlePhase();
        initializeAreaPanel();
        addMovementButtons();
    }

    private void initializeAreaPanel() {
        // Code to initialize the area panel goes here
        // This could include setting up labels for each grid cell
    }

    private void addMovementButtons() {
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton exitButton = new JButton("Exit Area");

        // Adding action listeners to each button
        upButton.addActionListener(e -> movePlayer("UP"));
        downButton.addActionListener(e -> movePlayer("DOWN"));
        leftButton.addActionListener(e -> movePlayer("LEFT"));
        rightButton.addActionListener(e -> movePlayer("RIGHT"));
        exitButton.addActionListener(e -> inArea = false);

        // Adding buttons to the panel
        this.add(upButton);
        this.add(downButton);
        this.add(leftButton);
        this.add(rightButton);
        this.add(exitButton);
    }

    private void movePlayer(String direction) {
        int newRow = playerRow;
        int newCol = playerCol;

        switch (direction) {
            case "UP": newRow--; break;
            case "DOWN": newRow++; break;
            case "RIGHT": newCol++; break;
            case "LEFT": newCol--; break;
        }

        if (isValidPosition(newRow, newCol)) {
            playerRow = newRow;
            playerCol = newCol;
            updateAreaDisplay();
        } else {
            JOptionPane.showMessageDialog(this, "OUT OF BOUNDS");
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < area.length && col >= 0 && col < area[0].length;
    }

    private void updateAreaDisplay() {
        // Update the area display to reflect the new player position
        // and handle creature encounters or other events
        randomEncounter();
    }

    private void randomEncounter() {
        int randomNum = rand.nextInt(100);
        if (randomNum < 40) {
            handleCreatureEncounter();
        }
    }

    private void handleCreatureEncounter() {
        Creatures creature = generateRandomCreatures();
        // Handle the battle phase here, possibly opening a new panel or dialog
        battle.startBattle(creature, gameLoop.getPlayerInventory());
    }

    public Creatures generateRandomCreatures() {
        int randomIndex = rand.nextInt(creatures.size());
        return creatures.get(randomIndex);
    }

    // Additional GUI handling methods...
}

