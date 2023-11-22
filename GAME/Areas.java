import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Areas {

    private String[][] area;
    private boolean inArea = true;
    private int playerRow = 0;
    private int playerCol = 0;
    private String direction;
    private Inventory playerInventory;
    private boolean outOfBounds;
    private ArrayList<Creatures> creatures;
    private BattlePhase battle;
    
    public Areas(int rows, int cols) {
        this.area = new String[rows][cols];
        this.initializeCreatures("EL1.txt");
        this.battle = new BattlePhase();
        this.outOfBounds = false;
    }


    public void setMovePlayer(String direction) {
        this.direction = direction;
    }


    public void initializeCreatures(String creaturesFile) {
        this.creatures = new ArrayList<>();

        try {
            FileReader reader = new FileReader(creaturesFile);
            BufferedReader br = new BufferedReader(reader);

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String type = parts[1];
                String family = parts[2];
                String image = parts[3];
                int evolutionLevel = Integer.parseInt(parts[4]);

                Creatures creature = new Creatures(name, type, family, image, evolutionLevel);
                this.creatures.add(creature);
            }

            br.close();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }


    public Creatures generateRandomCreatures() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(creatures.size());
        return creatures.get(randomIndex);
    }

  
    public void exploreArea(Inventory inventory, Scanner sc) {
        boolean first = true;
        boolean outOfBoundsMessage = false;
        while (inArea) {
            Tools.Clear();
            int rand;

            if (first)
                rand = 100;
            else
                rand = new Random().nextInt(100);

            if (outOfBounds && !direction.equals("RIGHT") && !direction.equals("LEFT")) {
            if (!outOfBoundsMessage) {
                        System.out.println("OUT OF BOUNDS");
                        outOfBoundsMessage = true;
            }
            outOfBounds = false;
            // Check for creature encounter (40% chance)
        } else if (rand < 40) {
                // Start a battle phase with the encountered creature
                battle.startBattle(generateRandomCreatures(), inventory ,  sc);
                first = true;
                outOfBounds = false;

            } else {
                first = false;
                showArea();
                outOfBoundsMessage = false;

                System.out.println("\nMovements");
                System.out.println("[1] UP [2] DOWN [3] RIGHT [4] LEFT or [5] Exit the Area?");
                System.out.print("Choice:");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        setMovePlayer("UP");
                        move();
                        break;

                    case 2:
                        setMovePlayer("DOWN");
                        move();
                        break;

                    case 3:
                        setMovePlayer("RIGHT");
                        move();
                        break;

                    case 4:
                        setMovePlayer("LEFT");
                        move();
                        break;

                    case 5:
                        inArea = false;
                        break;

                    default:
                        System.out.println("INVALID");
                        break;
                }

            }

           
        }
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
        switch (direction) {
            case "UP":
                if (playerRow > 0) {
                    playerRow--;
                    outOfBounds = false;
                } else
                    outOfBounds = true;
                break;
            case "DOWN":
                if (playerRow < area.length - 1) {
                    playerRow++;
                    outOfBounds = false;
                } else
                    outOfBounds = true;
                break;
            case "RIGHT":
                if (playerCol < area[0].length - 1) {
                    playerCol++;
                    outOfBounds = false;
                } else
       
                    outOfBounds = true;
                break;
            case "LEFT":
                if (playerCol > 0) {
                    playerCol--;
                    outOfBounds = false;
                } else
        
                    outOfBounds = true;
                break;
        }
    }

}
