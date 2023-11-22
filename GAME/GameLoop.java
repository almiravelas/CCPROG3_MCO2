import java.util.Scanner;

public class GameLoop{

    private boolean inGame;
    private Scanner sc;
    private Inventory playerInventory;

    public GameLoop(Inventory playerInventory, Scanner sc){
        this.inGame = true;
        this.playerInventory = playerInventory;
        this.sc = sc;
    }

    public void startGameLoop(){
        while (inGame){
            Tools.Clear();
            System.out.println("What do you want to do? ");
            System.out.println("[1] View Inventory");
            System.out.println("[2] Explore an Area");
            System.out.println("[3] Evolve Creature");
            System.out.println("[4] Exit.");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1: handleViewInventory(); break;
                case 2: handleExploreArea(); break;
                case 3: System.out.println("NOT AVAILABLE"); break;
                case 4: System.exit(0);
            }
        }
    }


    private void handleViewInventory(){
        Tools.Clear();

        playerInventory.listAll();

        System.out.println("What do you want to do?");
        System.out.println("[1] Change Active Creature");
        System.out.println("[2] Back to Main Menu");
        System.out.print("Choice: ");
        int si = sc.nextInt();
        sc.nextLine();

        if (si == 1){
            handleChangeActiveCreature();
        } else if (si == 2){
            startGameLoop();
        } 

    }

    private void handleChangeActiveCreature(){
        Tools.Clear();
            System.out.println("Choose a creature to swap with: ");
            int i = 0;
            for (Creatures c : playerInventory.getCreatures()) {
                System.out.println("[" + (++i) + "] " + c.getName());
            }
            System.out.print("Choice: ");
            int swapChoice = sc.nextInt();
            sc.nextLine();

            Tools.Clear();

            // Creatures temp = playerInventory.getActiveCreature();
            playerInventory.changeActiveCreature(playerInventory.getCreatures().get(swapChoice - 1));
            System.out.println("You swapped your active creature to " + playerInventory.getActiveCreature().getName());
            Tools.Pause();
    }

    private void handleExploreArea(){
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
                Areas area1 = new Areas(1, 5);
                area1.exploreArea(playerInventory, sc);
                break;
            case 2:
                Areas area2 = new Areas(4, 4);
                area2.exploreArea(playerInventory, sc);
                break;
            case 3:
                Areas area3 = new Areas(5, 5);
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
    }

}
