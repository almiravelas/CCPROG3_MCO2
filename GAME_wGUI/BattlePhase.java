import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class BattlePhase extends JPanel {

    private Creatures creature;
    private Inventory inventory;
    private double enemyHealth = 50;
    private int actions = 0;
    private Random rand = new Random();
    private JLabel enemyInfoLabel;
    private JLabel playerInfoLabel;
    private JFrame frame;

    public BattlePhase(JFrame frame, Inventory inventory) {
        this.frame = frame;
        this.inventory = inventory;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setupBattleUI();
    }

    private void setupBattleUI() {
        enemyInfoLabel = new JLabel();
        playerInfoLabel = new JLabel();

        this.add(enemyInfoLabel);
        this.add(playerInfoLabel);

        JButton attackButton = new JButton("Attack");
        JButton swapButton = new JButton("Swap");
        JButton catchButton = new JButton("Catch");
        JButton runButton = new JButton("Run");

        attackButton.addActionListener(e -> handleAttack());
        swapButton.addActionListener(e -> handleSwap());
        catchButton.addActionListener(e -> handleCatch());
        runButton.addActionListener(e -> handleRun());

        this.add(attackButton);
        this.add(swapButton);
        this.add(catchButton);
        this.add(runButton);
    }

    public void startBattle(Creatures creature) {
        this.creature = creature;
        updateBattleInfo();
    }

    private void updateBattleInfo() {
        enemyInfoLabel.setText("ENEMY CREATURE: Name: " + creature.getName() + ", Health: " + enemyHealth + ", Level: " + creature.getEvolutionLevel() + ", Type: " + creature.getType());
        playerInfoLabel.setText("ACTIVE CREATURE: Name: " + inventory.getActiveCreature().getName() + ", Level: " + inventory.getActiveCreature().getEvolutionLevel() + ", Type: " + inventory.getActiveCreature().getType());
    }

    private void handleAttack() {
        double hit = (rand.nextInt(1000) + 1) / 10.0;
        if (creature.getType().equals("GRASS") && inventory.getActiveCreature().getType().equals("FIRE") ||
            creature.getType().equals("FIRE") && inventory.getActiveCreature().getType().equals("WATER") ||
            creature.getType().equals("WATER") && inventory.getActiveCreature().getType().equals("GRASS")) {
            hit *= 1.5;
        }

        enemyHealth -= hit;
        actions++;

        JOptionPane.showMessageDialog(this, "You attacked the enemy creature with " + hit + " damage");

        checkBattleOutcome();
    }

    private void handleSwap() {
        // Swap logic (potentially open a new dialog to choose a creature)
        // ...
    }

    private void handleCatch() {
        if (rand.nextInt(100) < (40 + 50 - enemyHealth)) {
            inventory.addCreature(creature);
            JOptionPane.showMessageDialog(this, "You caught the enemy creature");
            endBattle();
        } else {
            JOptionPane.showMessageDialog(this, "You failed to catch the enemy creature");
            actions++;
            checkBattleOutcome();
        }
    }

    private void handleRun() {
        JOptionPane.showMessageDialog(this, "You ran away from the enemy creature");
        endBattle();
    }

    private void checkBattleOutcome() {
        if (enemyHealth <= 0) {
            JOptionPane.showMessageDialog(this, "You defeated the enemy creature!");
            endBattle();
        } else if (actions >= 3) {
            JOptionPane.showMessageDialog(this, "You ran out of actions!");
            endBattle();
        }

        updateBattleInfo();
    }

    private void endBattle() {
        // Close the battle phase and return to the main game screen
        frame.setContentPane(new MainGamePanel()); // Assuming MainGamePanel is your main game screen
        frame.revalidate();
        frame.repaint();
    }
}

