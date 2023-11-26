import java.util.Random;
import java.util.Scanner;

import javax.tools.Tool;

public class BattlePhase {

    private Creatures creature;

    public BattlePhase(){
        
    }

    public int startBattle(Creatures creature, Inventory inventory, Scanner sc) {
    
        int actions = 0;
        double health = 50;
        Random rand = new Random();

        while (actions < 3 || health > 0){
            Tools.Clear();

            System.out.println("Battling with a Creature!");

            System.out.println("\nENEMY CREATURE: ");
            System.out.println("Name: " + creature.getName());
            System.out.println("Health: " + health);
            System.out.println("Level: " + creature.getEvolutionLevel());
            System.out.println("Type: " + creature.getType());

            System.out.println("\nACTIVE CREATURE:");
            System.out.println("Name: " + inventory.getActiveCreature().getName());
            System.out.println("Level: " + inventory.getActiveCreature().getEvolutionLevel());
            System.out.println("Type: " + inventory.getActiveCreature().getType());

            System.out.println("\nList of Actions:");
            System.out.println("[1] Attack");
            System.out.println("[2] Swap");
            System.out.println("[3] Catch");
            System.out.println("[4] Run");
            System.out.print("Selected an Action: ");

            int choice = sc.nextInt();
            sc.nextLine();

            Tools.Clear();
            if (choice == 1){
                double hit = (rand.nextInt(1000) + 1) / 10;
                if (creature.getType().equals("GRASS") && inventory.getActiveCreature().getType().equals("FIRE") || 
                    creature.getType().equals("FIRE") && inventory.getActiveCreature().getType().equals("WATER") ||
                    creature.getType().equals("WATER") && inventory.getActiveCreature().getType().equals("GRASS")){
                    hit *= 1.5;
                }
                System.out.println("You chose Attack");
                System.out.println("You attacked the enemy creature with " + hit + " damage");

                health -= hit;
                actions++;

            } else if (choice == 2){
                System.out.println("You chose Swap");
                
                if (inventory.getCreatures().size() > 1){
                    System.out.println("Choose a creature to swap with: ");
                    int i = 0;
                    for (Creatures c : inventory.getCreatures()){
                        System.out.println("[" + (++i) + "] " + c.getName());
                    }
                    System.out.print("Choice: ");
                    int swapChoice = sc.nextInt();
                    sc.nextLine();

                    Tools.Clear();

                    Creatures temp = inventory.getActiveCreature();
                    inventory.changeActiveCreature(inventory.getCreatures().get(swapChoice - 1));
                    System.out.println("You swapped your active creature to " + inventory.getActiveCreature().getName());
                    actions++;

                } else {
                    System.out.println("You only have one creature in your inventory");
                }

            } else if (choice == 3){
                System.out.println("You chose Catch");
                
                
                if (rand.nextInt(100) < (40+50 - health)){
                    inventory.addCreature(creature);
                    System.out.println("You caught the enemy creature");
                    Tools.Pause();
                    return 1;
                } else {
                    System.out.println("You failed to catch the enemy creature");
                }
                actions++;
            } else if (choice == 4){
                System.out.println("You chose Run");
                System.out.println("You ran away from the enemy creature");
                
                Tools.Pause();
                return -1;
            } else {
                System.out.println("Invalid Choice");
            }
            
                        
            if (health <= 0){
                System.out.println("You defeated the enemy creature!");
                Tools.Pause();
                return -1;
                
            }
            else if (actions >= 3){
                Tools.Clear();
                System.out.println("You got ran out of actions!");
                Tools.Pause();
                return -1;
                
            }
            
            Tools.Pause();

        }

        return 0;
    }
    
}
