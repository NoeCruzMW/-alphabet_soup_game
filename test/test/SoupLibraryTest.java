/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import core.Soup;

/**
 *
 * @author Noé Cruz López | Zurck'z 2018 | MarWare Corp. 2018
 * <contactozurckz@gmail.com>
 */
public class SoupLibraryTest {

    public static void main(String[] args) {
        String words[] = {"WINNER", "GAME", "MACHINE", "SISTEMAS", "JAVA", "OBJETO", "FACEBOOK", "PYTHON", "RESIDENTE", "GOLANG", "LAPTOP", "AGUA"};
        Soup game = new Soup(15, 3, words);
        game.addWord("MOUSE");
        
        game.configure();
        game.start();
        
        game.showRawBoard();
        game.showWords();

    }
}
