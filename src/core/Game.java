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
public abstract class Game implements IGame {

    protected int lives;
    protected boolean isReady;
    protected boolean isStarted;
    protected boolean isFinished;

    public Game() {
    }

    public Game(int lives) {
        this.lives = lives;
    }

    public Game(int lives, Player currentPlayer) {
        this.lives = lives;
    }

    @Override
    public void configure() {

    }

    @Override
    public void start() {
        this.isStarted = true;
    }

    @Override
    public void restart() {
        this.isStarted = false;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isReady() {
        return isReady;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void finish() {
        this.isFinished = true;
    }
}
