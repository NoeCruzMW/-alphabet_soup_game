/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static core.Soup.DIRECTION.DOWN;
import static core.Soup.DIRECTION.UP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Noé Cruz López | Zurck'z 2018 | MarWare Corp. 2018
 * <contactozurckz@gmail.com>
 */
/*
    PERRO  3,3    
     0  1  2  3  4  5  6  7 
   0[ ][ ][ ][ ][ ][ ][ ][ ]
   1[ ][ ][ ][ ][ ][ ][ ][ ]
   2[ ][ ][ ][ ][ ][ ][ ][ ]
   3[ ][ ][P][E][R][R][O][ ]
   4[ ][ ][ ][ ][ ][ ][ ][ ]
   5[ ][ ][ ][ ][ ][ ][ ][ ]
   6[ ][ ][ ][ ][ ][ ][ ][ ]
   7[ ][ ][ ][ ][ ][ ][ ][ ]
   8[ ][ ][ ][ ][ ][ ][ ][ ]

 */
public class Soup extends Game {
    
    protected int size;
    protected List<String> words;
    protected List<String> wordsFound;
    protected String[][] board;
    protected Map<String, String> wordPositions;
    
    public static enum DIRECTION {
        RIGHT, LEFT, UP, DOWN
    }
    
    public Soup(int size, int lives, String words[]) {
        super(lives);
        this.size = size;
        this.words = new ArrayList<>(Arrays.asList(words));
        this.isReady = false;
        this.board = new String[size][size];
        this.wordPositions = new HashMap<>();
    }
    
    private int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    
    @Override
    public void start() {
        super.start();
    }
    
    @Override
    public void restart() {
        if (this.isStarted) {
            this.wordPositions.clear();
            this.board = new String[size][size];
            this.isReady = false;
            this.wordsFound.clear();
            this.isStarted = false;
            this.isFinished = false;
            this.configure();
        }
        
    }
    
    @Override
    public void configure() {
        
        int x, y, mix = 15, mixValue = 0;
        String xWord;
        for (int i = 0; i < this.words.size(); i++) {
            xWord = words.get(i).toUpperCase();
            
            if (xWord.length() > size) {
                continue;
            }
            
            x = getRandomNumber(0, size - 1);
            y = getRandomNumber(0, size - 1);
            
            if (this.wordPositions.containsKey(x + "," + y)) {
                i--;
                continue;
            }
            
            if (!checkRandomDirections(xWord, x, y)) {
                if (mixValue <= mix) {
                    mixValue++;
                    i--;
                    continue;
                }
            }
            mixValue = 0;
        }
        this.fillBoard();
        this.isReady = true;
    }
    
    private boolean checkRandomDirections(String word, int x, int y) {
        
        Map<DIRECTION, DIRECTION> revised = new HashMap<>();
        while (revised.size() < 4) {
            DIRECTION d = DIRECTION.values()[getRandomNumber(0, 3)];
            
            if (revised.containsKey(d)) {
                continue;
            }
            
            if (!checkSetPosition(word, x, y, d, true)) {
                revised.put(d, d);
                continue;
            }
            this.wordPositions.put(x + "," + y, word + "|" + d);
            return true;
        }
        
        return false;
    }
    
    private boolean checkSetPosition(String w, int x, int y, DIRECTION direction, boolean check) {
        boolean continueChecking = true;
        int initX = x, initY = y, i = 0;
        while (continueChecking) {
            switch (direction) {
                case DOWN:
                    if (check) {
                        if (!(initX + w.length() <= size)) {
                            return false;
                        }
                        if (this.board[x][y] != null) {
                            continueChecking = false;
                            break;
                        }
                    } else {
                        this.board[x][y] = w.charAt(i) + "";
                        i++;
                    }
                    x++;
                    break;
                case RIGHT:
                    if (check) {
                        if (!(initY + w.length() <= size)) {
                            return false;
                        }
                        if (this.board[x][y] != null) {
                            continueChecking = false;
                            break;
                        }
                    } else {
                        this.board[x][y] = w.charAt(i) + "";
                        i++;
                    }
                    y++;
                    break;
                case LEFT:
                    if (check) {
                        
                        if (!(initY - w.length() >= 0)) {
                            return false;
                        }
                        if (this.board[x][y] != null) {
                            continueChecking = false;
                            break;
                        }
                    } else {
                        this.board[x][y] = w.charAt(i) + "";
                        i++;
                    }
                    y--;
                    break;
                case UP:
                    if (check) {
                        if (!(initX - w.length() >= 0)) {
                            return false;
                        }
                        if (this.board[x][y] != null) {
                            continueChecking = false;
                            break;
                        }
                    } else {
                        this.board[x][y] = w.charAt(i) + "";
                        i++;
                    }
                    x--;
                    break;
            }
            if ((x == (initX + w.length())) || (x == (initX - w.length())) || (y == initY + w.length()) || (y == (initY - w.length()))) {
                break;
            }
        }
        if (continueChecking && check == true) {
            continueChecking = checkSetPosition(w, initX, initY, direction, !check);
        }
        return continueChecking;
    }
    
    public void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] == null) {
                    this.board[i][j] = "" + ((char) getRandomNumber(65, 90));
                }
            }
            
        }
    }
    
    public void showRawBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("[%s]", this.board[i][j]);
            }
            System.out.println("");
        }
        
    }
    
    public void showWords() {
        this.wordPositions.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
    }
    
    public boolean checkWord(int x, int y, String word) {
        String key = x + "," + y;
        if (wordPositions.containsKey(key)) {
            if (wordPositions.get(key).split("\\|")[0].equals(word.toUpperCase())) {
                
                if (wordsFound == null) {
                    this.wordsFound = new ArrayList<>();
                }
                
                this.wordsFound.add(word.toUpperCase());
                return true;
            }
        }
        return false;
    }
    
    public boolean continuePlaying() {
        if (wordsFound == null) {
            return false;
        }
        
        return this.words.size() > this.wordsFound.size();
    }
    
    public List<String> getWordsFound() {
        return this.wordsFound;
    }
    
    public boolean addWord(String e) {
        return this.words.add(e);
    }
}
