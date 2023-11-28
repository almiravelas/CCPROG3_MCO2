import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;


public class Areas {

    private String[][] area;
    private boolean inArea = true;
    private int playerRow = 0;
    private int playerCol = 0;
    private String direction;
    private boolean outOfBounds = false;
    private ArrayList<Creatures> creatures;
    private BattlePhase battle;
    private Random rand = new Random();
    private boolean outOfBoundsMessage = false;
    private boolean first = true;

    
    public Areas(int rows, int cols, String creaturesFile) {
        this.area = new String[rows][cols];
        this.creatures = CreatureFactory.initializeCreatures(creaturesFile);
        this.battle = new BattlePhase();
        this.outOfBounds = false;
    }


    public void setMovePlayer(String direction) {
        this.direction = direction;
    }


    public Creatures generateRandomCreatures() {
        int randomIndex = rand.nextInt(creatures.size());
        return creatures.get(randomIndex);
    }


    public void showArea() {
        System.out.println("Area 1 ");
        for (int row = 0; row < area.length; row++) {
            for (int col = 0; col < area[0].length; col++) {
                if (row == playerRow && col == playerCol) {
                    System.out.print("[P]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }


    public void move() {
        
        int newRow = playerRow;
        int newCol = playerCol;

        switch (direction) {
            case "UP": newRow--; break;
            case "DOWN": newRow++; break;
            case "RIGHT": newCol++; break;
            case "LEFT": newCol--; break;
        }

        outOfBounds = newRow < 0 || newRow >= area.length || newCol < 0 || newCol >= area[0].length;
    
        if(!outOfBounds){
            playerRow = newRow;
            playerCol = newCol;
        }

    }

    private void handleMovementChoice(Scanner sc){
        try{
        System.out.println("\nMovements");
        System.out.println("[1] UP [2] DOWN [3] RIGHT [4] LEFT or [5] Exit the Area?");
        System.out.print("Choice:");
        
        int choice = sc.nextInt();
        switch (choice) {
            case 1: setMovePlayer("UP"); break;
            case 2: setMovePlayer("DOWN"); break;
            case 3: setMovePlayer("RIGHT"); break;
            case 4: setMovePlayer("LEFT"); break;
            case 5: inArea = false; break;
            default: System.out.println("INVALID"); break;

        } 
    }catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
        sc.nextLine(); // clear the buffer
    }
}

    private void handleCreatureEncounter(Inventory inventory, Scanner sc, boolean first) {
        battle.startBattle(generateRandomCreatures(), inventory, sc);
        first = true;
        outOfBounds = false;
    }

    private void handleOutOfBounds() {
        if (!outOfBoundsMessage) {
            System.out.println("OUT OF BOUNDS");
            this.outOfBoundsMessage = true;
        }
        outOfBounds = false;
    }

    private void randomEncounter(Inventory inventory, Scanner sc, boolean first) {
        int randomNum = first ? 100 : rand.nextInt(100);
        if (randomNum < 40) {
            handleCreatureEncounter(inventory, sc, first);
        }
    }

    public void exploreArea(Inventory inventory, Scanner sc) {
        while (inArea) {
            Tools.Clear();
            
            showArea();
            handleMovementChoice(sc);
            move();
    
            if (outOfBounds && !direction.equals("RIGHT") && !direction.equals("LEFT")) {
                handleOutOfBounds();
            } else if (!outOfBounds) {
                // Only check for random encounters if the player is not out of bounds
                randomEncounter(inventory, sc,first);
                showArea();
                this.outOfBoundsMessage = false;
            }
    
            this.first = false;
        }
    }
    


}
