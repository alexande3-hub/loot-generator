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
                s.close();
                return i + 1;
            }
        }
        s.close();
        return 0;
    }

    public String fetchTreasureClass(String TC) {
        Scanner s = new Scanner("data/large/TreasureClassEx.txt");
        Random rand = new Random();
        int randInt = rand.nextInt(1, 4);
        if (whereTreasureClass(TC) != 0) {
            for (int i = 1; i < whereTreasureClass(TC); i++) {
                s.nextLine();
            }
            String fin = fetchTreasureClass(s.nextLine().split("\t")[randInt]);
            s.close();
            return fin;
        } else {
            s.close();
            return TC;
        }
    }

    public String[] generateBaseItem(String armor) {
        Scanner s = new Scanner("data/large/armor.txt");
        for (int i = 0; i < 202; i++) {
            String[] armorArray = s.nextLine().split("\t");
            if (armorArray[0].equals(armor)) {
                s.close();
                return armorArray;
            }
        }
        s.close();
        return null;
    }

    public void generateBaseStats(String armor) {
        Random rand = new Random();
        String[] armorArray = generateBaseItem(armor);
        int randInt = rand.nextInt(Integer.parseInt(armorArray[1]), Integer.parseInt(armorArray[2]));
        System.out.println("Defense: " + randInt);
    }

    public String[] generatePrefix() {
        Scanner s = new Scanner("data/large/MagicPrefix.txt");
        Random rand = new Random();
        int randInt = rand.nextInt(1, 373);
        for (int i = 1; i < randInt; i++) {
            s.nextLine();
        }
        String[] pre = s.nextLine().split("\t");
        s.close();
        return pre;
    }

    public String[] generateSuffix() {
        Scanner s = new Scanner("data/large/MagicSuffix.txt");
        Random rand = new Random();
        int randInt = rand.nextInt(1, 387);
        for (int i = 1; i < randInt; i++) {
            s.nextLine();
        }
        String[] suf = s.nextLine().split("\t");
        s.close();
        return suf;
    }

    public void generateAffix(String item) {
        Random rand = new Random();
        int randInt = rand.nextInt(0, 2);
        if (randInt == 1) {
            String[] prefixArray = generatePrefix();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("This program kills monsters and generates loot!");
    }
}
