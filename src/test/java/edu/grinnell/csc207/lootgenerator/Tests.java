package edu.grinnell.csc207.lootgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    public void monTest() throws FileNotFoundException {
        File file = new File("data/large/monstats.txt");
        Scanner s = new Scanner(file);
        String[] mon = s.nextLine().split("\t");
        s.close();
        assertEquals("DarkHunter", mon[0]);
        assertEquals("CorruptRogue", mon[1]);
    }

    @Test
    public void treasTest() throws FileNotFoundException {
        File file = new File("data/large/TreasureClassEx.txt");
        Scanner s = new Scanner(file);
        for (int i = 1; i < 40; i++) {
            s.nextLine();
        }
        String[] treasure = s.nextLine().split("\t");
        s.close();
        assertEquals("Act 4 Junk", treasure[0]);
    }

    @Test
    public void firstLocationTest() throws FileNotFoundException {
        int treas = LootGenerator.whereTreasureClass("Act 1 Miss B");
        assertEquals(12, treas);
    }

    @Test
    public void secLocationTest() throws FileNotFoundException {
        int treas = LootGenerator.whereTreasureClass("well hello there");
        assertEquals(0, treas);
    }

}
