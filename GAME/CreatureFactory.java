import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreatureFactory{

    public static ArrayList<Creatures> initializeCreatures(String creaturesFile) {
        ArrayList<Creatures> creatures = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(creaturesFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String type = parts[1];
                String family = parts[2];
                String image = parts[3];
                int evolutionLevel = Integer.parseInt(parts[4]);

                Creatures creature = new Creatures(name, type, family, image, evolutionLevel);
                creatures.add(creature);
            }
        } catch (IOException e) {
            System.out.println("File not found: " + creaturesFile);
            e.printStackTrace();
        }

        return creatures;
    }
}
