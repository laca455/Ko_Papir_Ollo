package com.game;

import com.game.players.Jatekos;
import com.game.players.Gep;
import com.game.storage.PontNyilvantartas;
import com.game.storage.SQLDatabase;

import java.util.Scanner;

public class KoPapirOllo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PontNyilvantartas pontNyilvantartas = new PontNyilvantartas();
        SQLDatabase database = new SQLDatabase();

        System.out.println("Üdvözöllek a Kő-Papír-Olló játékban!");
        System.out.print("Válassz módot: 1 - Gép ellen | 2 - Két játékos: ");
        int mode = scanner.nextInt();
        scanner.nextLine();
        
        Jatekos jatekos1 = new Jatekos();
        Jatekos jatekos2 = null;
        Gep gep = null;

        if (mode == 1) {
            jatekos1.setNev("Játékos 1"); // Alapértelmezett név beállítása
            gep = new Gep();
        } else {
            jatekos2 = new Jatekos();
            System.out.print("Első játékos neve: ");
            jatekos1.setNev(scanner.nextLine());
            System.out.print("Második játékos neve: ");
            jatekos2.setNev(scanner.nextLine());
        }

        while (true) {
            System.out.print(jatekos1.getNev() + " választása (K/P/O, vagy Q a kilépéshez): ");
            String player1Choice = scanner.nextLine().toUpperCase();
            
            if (player1Choice.equals("Q")) {
                System.out.println("Kiléptél a játékból. Viszlát! " +
                        "Köszönöm, hogy megnézted a mini játékom Papp László D51P4L");
                break;
            }

            if (isValidChoice(player1Choice)) {
                System.out.println("Érvénytelen választás! Kérlek válassz K, P vagy O lehetőséget.");
                continue;
            }
            
            String player2Choice;
            if (mode == 1) {
                player2Choice = gep.valaszt();
                System.out.println("Gép választása: " + player2Choice);
            } else {
                System.out.print(jatekos2.getNev() + " választása (K/P/O): ");
                player2Choice = scanner.nextLine().toUpperCase();
                if (isValidChoice(player2Choice)) {
                    System.out.println("Érvénytelen választás!");
                    continue;
                }
            }
            
            String result = getWinner(player1Choice, player2Choice);
            System.out.println(result);
            pontNyilvantartas.mentes(result);
            database.mentesAdatbazisba(jatekos1.getNev(), player1Choice, (mode == 1) ? "Gép" : jatekos2.getNev(), player2Choice, result);
        }
        
        scanner.close();
    }

    private static boolean isValidChoice(String choice) {
        return !choice.equals("K") && !choice.equals("P") && !choice.equals("O");
    }
    
    private static String getWinner(String player1, String player2) {
        if (player1.equals(player2)) {
            return "Döntetlen!";
        }
        
        if ((player1.equals("K") && player2.equals("O")) ||
            (player1.equals("P") && player2.equals("K")) ||
            (player1.equals("O") && player2.equals("P"))) {
            return "Játékos 1 nyert!";
        } else {
            return "Játékos 2 nyert!";
        }
    }
}
/// Papp László D51P4L