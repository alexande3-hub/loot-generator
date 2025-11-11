package edu.grinnell.csc207.lootgenerator;

import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    public String[] getMonster() {
        Scanner s = new Scanner("data/large/monstats.txt");
        Random rand = new Random();
        int randInt = rand.nextInt(1, 50);
        for (int i = 1; i < randInt; i++) {
            s.nextLine();
        }
        String[] mon = s.nextLine().split("\t");
        s.close();
        return mon;
    }

    public int whereTreasureClass(String TC) {
        Scanner s = new Scanner("data/large/TreasureClassEx.txt");
        for (int i = 0; i < 68; i++) {
            String[] tre = s.nextLine().split("\t");
            if (TC.equals(tre[0])) {
                return i + 1;
            }
        }
        return 0;
    }

    public String fetchTreasureClass(String TC) {
        Scanner s = new Scanner("data/large/TreasureClassEx.txt");
        Random rand = new Random();
        int randInt = rand.nextInt(1, 4);
        if (whereTreasureClass(TC) != 0) {
            for (int i = 0; i < whereTreasureClass(TC); i++) {
                s.nextLine();
            }
            return fetchTreasureClass(s.nextLine().split("\t")[randInt]);
        } else {
            return TC;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("This program kills monsters and generates loot!");
    }
}
