/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import core.Game;
import core.Player;
import core.Soup;
import java.util.Scanner;

/**
 *
 * @author Noé Cruz López | Zurck'z 2018 | MarWare Corp. 2018
 * <contactozurckz@gmail.com>
 */
public class SoupGame {

    private Soup game;
    private Player player;

    // HARD CORE PROPERTIES GAME ↓↓↓↓↓↓
    private final static String OPTIONS[] = {"PLAY", "EXIT"};
    private String WORDS[] = {"aplicacion",
        "ventana", "base", "clic", "digitalizar", "editar", "equipo", "icono", "artificial", "personal", "zurckz"};
    private final static int LIVES = 3;
    private final static int SIZE = 15;
    // HARD CORE PROPERTIES GAME  ↑↑↑↑↑↑

    private static Scanner in = new Scanner(System.in);

    public SoupGame() {
        this.game = new Soup(SIZE, LIVES, WORDS);
        this.game.configure();
        this.game.showWords();
        this.game.start();
    }

    public void start() {
        String opc;
        do {
            showMenu();
            System.out.print("\tSelecciona una opción: ");
            opc = in.nextLine();
            switch (opc) {
                case "1":
                    this.play();
                    break;
            }
        } while (!"2".equals(opc));
    }

    private void play() {
        if (this.player == null) {
            String name;
            System.out.print("\tNombre: ");
            name = in.nextLine();
            this.player = new Player(name, 0, 0);
        }
        printN(50, "▬", '\n');
        int f = WORDS.length % 2 == 0 ? WORDS.length / (WORDS.length > 4 ? 4 : WORDS.length) : WORDS.length / 4 + 1, c = 0;

        while (this.game.isReady() && !this.game.isFinished()) {
            System.out.printf("\tPlayer: %s\t\tScore: %d\t\tGP: %d\n", player.getName(), player.getScore(), player.getGamesPlayed());
            printN(50, "▬", '\n');
            for (int i = 0; i < f; i++) {
                for (int j = 0; j < 4 && c < WORDS.length; j++) {
                    System.out.printf(" %d %s ", (c + 1), WORDS[c].toUpperCase());
                    c++;
                }
                System.out.println("");
            }
            System.out.println("");
            this.game.showRawBoard();
            printN(50, "─", '\n');
            System.out.print("Ingresa el numero de palabra y las coordenadas X,Y\nDonde inicia la palabra Ej(1 1 3): ");
            validateEntry(in.nextLine());
            if (!this.game.continuePlaying()) {
                this.game.finish();
            }
            c = 0;
        }
        printN(50, "▬", '\n');
        System.out.println("\n\t\tFelicidades!!\n\t\tEncontraste todas las palabras! XD");
        printN(50, "▬", '\n');
        this.game.restart();

    }

    public void validateEntry(String input) {
        try {
            String inputs[] = input.split("\\ ");
            int wordIndex = Integer.parseInt(inputs[0]);
            int x = Integer.parseInt(inputs[1]);
            int y = Integer.parseInt(inputs[2]);
            if (this.game.checkWord(x - 1, y - 1, WORDS[wordIndex - 1])) {
                this.player.increaseScore(2);
                WORDS[wordIndex - 1] = " --------- ";
            }
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida");
        }
    }

    private void printN(int n, Object any, char sep) {
        for (int i = 0; i < n; i++) {
            System.out.printf("%s", any, sep);
        }
        System.out.print(sep);
    }

    private void showMenu() {
        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.printf("\t[%d] - %s\n", (i + 1), OPTIONS[i]);
        }

    }

}
