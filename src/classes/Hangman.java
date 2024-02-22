package classes;

import processing.core.PApplet;

public class Hangman {
    private String secretWord;
    private char[] guessedLetters;
    private boolean[] alreadyGuessedLetters;
    private int guesses;
    private int index;

    public Hangman(String word) {
        secretWord = word.toLowerCase();
        guessedLetters = new char[secretWord.length()];

        // init partial word, and set all
        // letters to '_'
        guessedLetters = new char[word.length()];
        alreadyGuessedLetters = new boolean[29];

        for (int i = 0; i < alreadyGuessedLetters.length; i++) {
            alreadyGuessedLetters[i] = false;
        }
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    public char[] getGuessedLetters() {
        return guessedLetters;
    }

    public int getSectretWordLenght() {
        return secretWord.length();
    }

    public int getGuesses() {
        return guesses;
    }

    public boolean guess(char letter) {
        char lowerCase = Character.toLowerCase(letter);
        boolean letterInSecretWord = false;
        if (lowerCase >= 'a' && lowerCase <= 'z') {
            index = lowerCase - 'a';
        } else if (lowerCase == 'æ') {
            index = 26;
        } else if (lowerCase == 'ø') {
            index = 27;
        } else if (lowerCase == 'å') {
            index = 28;
        } else {
            letterInSecretWord = true;
        }

        if (alreadyGuessedLetters[index] == false && letterInSecretWord == false) {
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == lowerCase) {
                    guessedLetters[i] = lowerCase;
                    letterInSecretWord = true;
                }
            }
        }
        if (alreadyGuessedLetters[index] == true && letterInSecretWord == false) {
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) != lowerCase) {
                    letterInSecretWord = true;
                }
            }
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == lowerCase) {
                    letterInSecretWord = true;
                }
            }
        }

        if (letterInSecretWord == false) {
            guesses++;
        }

        alreadyGuessedLetters[index] = true;
        System.out.println("GUESSES:" + guesses);

        return letterInSecretWord;
    }

    public void drawGuessedLetters(PApplet p) {
        // draw word on screen
        p.textSize(32);
        for (int i = 0; i < guessedLetters.length; i++) {
            // draw guessed letter in center of the screen
            p.text(guessedLetters[i], p.width / 2 + 20 * i -
                    guessedLetters.length * 20 / 2, p.height / 2);
        }
    }

    public boolean isAlive() {
        if(guesses < 6) {
            return true;
        }
        else return false;
    }
}
