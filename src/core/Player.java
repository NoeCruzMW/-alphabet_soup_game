/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Noé Cruz López | Zurck'z 2018 | MarWare Corp. 2018
 * <contactozurckz@gmail.com>
 */
public class Player {

    private String name;
    private int gamesPlayed;
    private int score;

    public Player(String name, int gamesPlayed, int score) {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increaseScore(int value) {
        this.score += value;
    }

    public void increaseGames(int value) {
        this.gamesPlayed += value;
    }
}
