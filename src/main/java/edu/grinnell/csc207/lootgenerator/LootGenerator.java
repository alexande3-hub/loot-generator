package edu.grinnell.csc207.lootgenerator;

import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    public static String[] getMonster() {
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
    
        /**
         * 
         * @param TC the 
         * @return
         */
        public static int whereTreasureClass(String TC) {
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
        
        /**
         * Determines the base item the monster drops.
         * @param TC the starting TC representation we use to find the final item.
         * @return the string representing the final generated item from TC.
         */
        public static String fetchTreasureClass(String TC) {
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
        
        /**
         * Returns an array that includes armor and its armor.txt data.
         * @param armor the armor we use to find the matching entry in armor.txt.
         * @return an array representing the data in armor.txt that matches
         * with the given armor.
         */
        public static String[] generateBaseItem(String armor) {
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
        
        /**
         * Generates base statistics for a given piece of armor.
         * @param armor the armor we generate the defense for
         */
        public static void generateBaseStats(String armor) {
            Random rand = new Random();
            String[] armorArray = generateBaseItem(armor);
            int randInt = rand.nextInt(Integer.parseInt(armorArray[1]), Integer.parseInt(armorArray[2]));
            System.out.println("Defense: " + randInt);
        }
    
        /**
         * Generates a random prefix for an item.
         * @return an array representing the prefix and its info.
         */
        public static String[] generatePrefix() {
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
    
        /**
         * Generates a random suffix for an item.
         * @return an array representing the suffix and its info.
         */
        public static String[] generateSuffix() {
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
    
        /**
         * 
         * Prints the complete affix info for the given item,
         * along with additional info for the prefix and suffix.
         * @param item the item we are generating the prefix/suffix for.
         * 
         */
        public static void generateAffix(String item) {
            Random rand = new Random();
            String prefix = "";
            int value1 = -1;
            String stat1 = "";
            String suffix = "";
            int value2 = -1;
            String stat2 = "";
            int randInt = rand.nextInt(0, 2);
            if (randInt == 1) {
                String[] prefixArray = generatePrefix();
                prefix = prefixArray[0] + " ";
                stat1 = " " + prefixArray[1];
                value1 = rand.nextInt(Integer.parseInt(prefixArray[1]), Integer.parseInt(prefixArray[2]));
            }
            int randInt2 = rand.nextInt(0, 2);
            if (randInt2 == 1) {
                String[] suffixArray = generateSuffix();
                suffix = " " + suffixArray[0];
                stat2 = " " + suffixArray[1];
                value2 = rand.nextInt(Integer.parseInt(suffixArray[1]), Integer.parseInt(suffixArray[2]));
            }
            System.out.println(prefix + item + suffix);
            generateBaseStats(item);
            if (randInt == 1) {
                System.out.println(value1 + stat1);
            }
            if (randInt2 == 1) {
                System.out.println(value2 + stat2);
            }
        }
        
        public static void main(String[] args) {
            Scanner s = new Scanner(System.in);
            String input;
            do {
                String[] mon = getMonster();
                String monster = mon[0];
                System.out.println("Fighting " + monster);
                System.out.println("You have slain " + monster + "!");
                System.out.println(monster + " dropped:");
                System.out.println("");
                generateAffix(fetchTreasureClass(mon[3]));
                do {
                    System.out.print("Fight again [y/n]?");
                    input = s.next();
                } while (!(input.equals("Y") || input.equals("y") ||
                           input.equals("N") || input.equals("n")));
            } while (input.equals("Y") || input.equals("y"));
            s.close();
    }
}
