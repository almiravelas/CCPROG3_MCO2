
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Creatures starterCreature = null;
                // int starterChoice = 0;
        Inventory playerInventory = new Inventory();
        // boolean validChoice = false;
        GameInitializer gameInitializer = new GameInitializer(sc, playerInventory);

        Creatures starterCreature = gameInitializer.initializeGame();

        playerInventory.addCreature(starterCreature);
        playerInventory.changeActiveCreature(starterCreature);


        GameLoop gameLoop = new GameLoop(playerInventory, sc);
        gameLoop.startGameLoop();




    }
}
