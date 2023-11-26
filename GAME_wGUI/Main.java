
// import java.util.Scanner; //removed because scanner is for console input
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Main {
    public static void main(String[] args) {

        // Scanner sc = new Scanner(System.in);
        // Creatures starterCreature = null;
                // int starterChoice = 0;
                // boolean validChoice = false;
        Inventory playerInventory = new Inventory();
        // GameInitializer gameInitializer = new GameInitializer(playerInventory);
        
        //Initialization of game and creature selection will be handled in GUI
        // Creatures starterCreature = gameInitializer.initializeGame();
        // playerInventory.addCreature(starterCreature);
        // playerInventory.changeActiveCreature(starterCreature);
        //REMOVE SCANNER FROM CONSTRUCTOR
        // GameLoop gameLoop = new GameLoop(playerInventory, sc);
        //GAME LOOP WILL BE TRIGGERED BY GUI EVENTS
        // gameLoop.startGameLoop();

        //LAUNCH THE GUI HERE
        GameLoop gameLoop = new GameLoop(playerInventory);
        GameGUI gameGUI = new GameGUI(gameLoop, playerInventory);

        gameGUI.setVisible(true);



    }
}
