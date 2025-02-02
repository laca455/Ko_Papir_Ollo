package com.game;

import java.io.FileWriter;
import java.io.IOException;

public class PontNyilvantartas {
    public void mentes(String eredmeny) {
        try (FileWriter writer = new FileWriter("pontok.txt", true)) {
            writer.write(eredmeny + "\n");
        } catch (IOException e) {
            System.out.println("Hiba a fájl mentésekor: " + e.getMessage());
        }
    }
}
