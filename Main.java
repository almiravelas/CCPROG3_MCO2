
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Main {
    public static void main(String[] args) {
        Tools.Clear();
        Scanner sc = new Scanner(System.in);
        Creatures starterCreature = null;
        boolean inGame = true;
        Inventory playerInventory = new Inventory();

        String[] starterCreatureNames = {
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

System.out.println("Choose your starter creature:");
        for (int i = 0; i < starterCreatureNames.length; i++) {
            System.out.println("[" + (i + 1) + "]" + starterCreatureNames[i]);
        }
        int starterChoice = 0;
        boolean validChoice = false;






        do {
            System.out.print("Choice: ");
            if (sc.hasNextInt()) {
                starterChoice = sc.nextInt();
                if (starterChoice >= 1 && starterChoice <= starterCreatureNames.length) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            } else {
                sc.next();
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!validChoice);

        try {
            FileReader reader = new FileReader("EL1.txt");
            BufferedReader br = new BufferedReader(reader);

            String line;

            
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (i == starterChoice - 1){
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

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        playerInventory.addCreature(starterCreature);
        playerInventory.changeActiveCreature(starterCreature);
        System.out.println("You chose: " + starterCreature.getName());

        while (inGame) {
            Tools.Clear();

            System.out.println("What do you want to do? ");
            System.out.println("[1] View Inventory");
            System.out.println("[2] Explore an Area");
            System.out.println("[3] Evolve Creature");
            System.out.println("[4] Exit.");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Tools.Clear();

                    playerInventory.listAll();

                    System.out.println("What do you want to do?");
                    System.out.println("[1] Change Active Creature");
                    System.out.println("[2] Back to Main Menu");

                    System.out.print("Choice: ");
                    int si = sc.nextInt();
                    if (si == 1){
                        Tools.Clear();
                        System.out.println("Choose a creature to swap with: ");
                        int i = 0;
                        for (Creatures c : playerInventory.getCreatures()){
                            System.out.println("[" + (++i) + "] " + c.getName());
                        }
                        System.out.print("Choice: ");
                        int swapChoice = sc.nextInt();
                        sc.nextLine();

                        Tools.Clear();

                        Creatures temp = playerInventory.getActiveCreature();
                        playerInventory.changeActiveCreature(playerInventory.getCreatures().get(swapChoice - 1));
                        System.out.println("You swapped your active creature to " + playerInventory.getActiveCreature().getName());
                        Tools.Pause();
                    } else if (si == 2){
                        break;
                    } 

                    break;
                case 2:
                    Tools.Clear();

                    System.out.println("Choose which Area you want to explore:");
                    System.out.println("[1] Area 1 5x1");
                    System.out.println("[2] Area 2 3x3");
                    System.out.println("[3] Area 3 4x4");
                    System.out.println("[4] Back to Main Menu");
                    System.out.print("Choice: ");
                    int areaChoice = sc.nextInt();

                    Tools.Clear();
                    switch (areaChoice) {
                        case 1:
                            
                            Areas area1 = new Areas(1,5);
                            area1.exploreArea(playerInventory, sc);
                            break;
                        case 2:
                            Areas area2 = new Areas(4,4);
                            area2.exploreArea(playerInventory, sc);
                            break;
                        case 3:
                            Areas area3 = new Areas(5,5);
                            area3.exploreArea(playerInventory, sc);
                            break;
                        case 4:
                            inGame = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Defaulting to Area 1.");
                            Tools.Pause();

                            
                            break;
                    }
                
                    break;
                case 3:
                    Tools.Clear();

                    System.out.println("NOT AVAILABLE");
                    break;
                case 4:
                System.exit(0);

            }
        }
    }
}
