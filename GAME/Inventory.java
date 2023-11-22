import java.util.ArrayList;

public class Inventory{
    private ArrayList<Creatures> creatures;
    private Creatures activeCreature;
    private boolean hasChosenStarterCreature; //flag to track the starter creature

    public Inventory(){
        creatures = new ArrayList<>();
        hasChosenStarterCreature = false;

    }

    public void addCreature(Creatures creature){
        creatures.add(creature);
    }

    public void changeActiveCreature(Creatures creature){
        activeCreature = creature;
        hasChosenStarterCreature = true;
    }

    public ArrayList<Creatures> getCreatures(){
        return creatures;
    }

    public Creatures getActiveCreature(){
        return activeCreature;
    }

    public void listAll(){
        System.out.println("Inventory: ");
        
        if (hasChosenStarterCreature) {
            // Display the active creature
            System.out.println("=Active Creature=");
            System.out.println("Name: " + activeCreature.getName());
            System.out.println("Type: " + activeCreature.getType());
            System.out.println("Family: " + activeCreature.getFamily());
            System.out.println("EL: " + activeCreature.getEvolutionLevel());
            System.out.println(activeCreature);
        }

        int i = 0;
        for(Creatures creature : creatures){
            if (creature != activeCreature){
            System.out.println();
            System.out.println("Creature " + (++i) + ":");
            System.out.println("Name: " + creature.getName());
            System.out.println("Type: " + creature.getType());
            System.out.println("Family: " + creature.getFamily());
            System.out.println("EL: " + creature.getEvolutionLevel());
            System.out.println(creature);
            }
        }

        System.out.println();
    }
}