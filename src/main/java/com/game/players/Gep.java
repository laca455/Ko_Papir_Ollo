package com.game.players;

import java.util.Random;

public class Gep {
    private Random random = new Random();
    private static final String[] LEHETOSEGEK = {"K", "P", "O"};

    public String valaszt() {
        return LEHETOSEGEK[random.nextInt(LEHETOSEGEK.length)];
    }
}
