import java.util.ArrayList;
import java.util.Scanner;

public class Evolution {
    private Inventory inventory;
    private Scanner scanner;

    public Evolution(Inventory inventory) {
        this.inventory = inventory;
        this.scanner = new Scanner(System.in);
    }

    public void performEvolution() {
        System.out.println("=== Evolution ===");

        // Select two creatures for evolution
        Creatures creature1 = selectCreature("Select the first creature for evolution: ");
        Creatures creature2 = selectCreature("Select the second creature for evolution: ");

        // Check if both creatures are eligible for evolution
        if (isValidForEvolution(creature1, creature2)) {
            // Perform successful evolution
            System.out.println("=== Evolution Successful ===");
            System.out.println("Selected Creatures: ");
            displayCreatureDetails(creature1);
            displayCreatureDetails(creature2);

            // Remove selected creatures from the inventory
            inventory.getCreatures().remove(creature1);
            inventory.getCreatures().remove(creature2);

            // Create and add the evolved creature to the inventory
            Creatures evolvedCreature = createEvolvedCreature(creature1);
            inventory.addCreature(evolvedCreature);

            // Display the evolved creature
            System.out.println("=== Evolved Creature ===");
            displayCreatureDetails(evolvedCreature);

        } else {
            // Evolution fails
            System.out.println("=== Evolution Failed ===");
            System.out.println("Selected Creatures are not eligible for evolution.");
        }
    }

    private Creatures selectCreature(String prompt) {
        inventory.listAll();
        System.out.println(prompt);

        int creatureIndex;
        do {
            System.out.print("Enter the creature number: ");
            creatureIndex = scanner.nextInt();
        } while (creatureIndex < 1 || creatureIndex > inventory.getCreatures().size());

        return inventory.getCreatures().get(creatureIndex - 1);
    }

    private boolean isValidForEvolution(Creatures creature1, Creatures creature2) {
        return creature1 != null && creature2 != null &&
                creature1.getEvolutionLevel() == creature2.getEvolutionLevel() &&
                creature1.getFamily().equals(creature2.getFamily()) &&
                creature1.getEvolutionLevel() < 3; // EL3 creatures are not allowed for evolution
    }

    private Creatures createEvolvedCreature(Creatures creature) {
        // Create a new creature with the next evolution level of the same family
        return new Creatures(
                creature.getName() + " Evolved",
                creature.getType(),
                creature.getFamily(),
                creature.getImage(),
                creature.getEvolutionLevel() + 1,
                creature.getHealth()
        );
    }

    private void displayCreatureDetails(Creatures creature) {
        System.out.println("Creature: ");
        System.out.println("Name: " + creature.getName());
        System.out.println("Type: " + creature.getType());
        System.out.println("Family: " + creature.getFamily());
        System.out.println("EL: " + creature.getEvolutionLevel());
        System.out.println("Health: " + creature.getHealth());
        //System.out.println("Image: " + creature.getImage());
        System.out.println();
    }
}
