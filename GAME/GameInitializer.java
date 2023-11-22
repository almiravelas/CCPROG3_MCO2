
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameInitializer{

    private Scanner sc;
    private Inventory playerInventory;

    public GameInitializer(Scanner sc, Inventory playerInventory){
        this.sc = sc;
        this.playerInventory = playerInventory;
    }

    public int getUserChoice(Scanner sc, String[] options){
        int starterChoice = 0;
        boolean validChoice = false;

        do {
            System.out.print("Choice: ");
            if (sc.hasNextInt()) {
                starterChoice = sc.nextInt();
                if (starterChoice >= 1 && starterChoice <= options.length) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } else {
                sc.next();
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validChoice);

        return starterChoice;

    }

    public Creatures initializeGame(){
        Tools.Clear();
        System.out.println("WELCOME TO THE GAME");
        Tools.Clear();

        final String[] STARTER_CREATURE_NAMES = {
            "STRAWANDER",
            "CHOCOWOOL",
            "PARFWIT",
            "BROWNISAUR",
            "FRUBAT",
            "MALTS",
            "SQUIRPIE",
            "CHOCOLITE",
            "OSHACONE"
        };

        displayStarterMessage(STARTER_CREATURE_NAMES);
        int starterChoice = getUserChoice(sc, STARTER_CREATURE_NAMES);

        //load the starter creature file
        Creatures startCreatures = loadStarterFile(starterChoice);

        addStarter(playerInventory, startCreatures);
        return startCreatures;

    }
    
    public void displayStarterMessage(String[] STARTER_CREATURE_NAMES){

        System.out.println("Choose your starter creature:");
        for (int i = 0; i < STARTER_CREATURE_NAMES.length; i++) {
            System.out.println("[" + (i + 1) + "]" + STARTER_CREATURE_NAMES[i]);
        }
    }

    public Creatures loadStarterFile(int choice){
        Creatures starterCreature = null;
        try {
            FileReader reader = new FileReader("EL1.txt");
            BufferedReader br = new BufferedReader(reader);

            String line;

            
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == choice - 1){
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String type = parts[1];
                    String family = parts[2];
                    String image = parts[3];
                    int evolutionLevel = Integer.parseInt(parts[4]);
                    
                    starterCreature = new Creatures(name, type, family, image, evolutionLevel);
                }   
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return starterCreature;
    }

    public void addStarter(Inventory inventory, Creatures starterCreature){
        playerInventory.addCreature(starterCreature);
        playerInventory.changeActiveCreature(starterCreature);
        Tools.Clear();
        System.out.println("You chose: "+ starterCreature.getName());
        Tools.Clear();

    }
}