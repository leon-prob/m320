package ch.tbz.leon_michel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuessTheNumber {
    private final int guessingNumber;
    private boolean isInGame;
    private final List<Integer> guesses;

    public GuessTheNumber(int min, int max) {
        this.guessingNumber = (int) (Math.random() * (max - min)  + min);
        guesses = new ArrayList<>();
        isInGame = true;
    }

    public boolean isInGame() {
        return isInGame;
    }

    private Integer getGuess() {
        Scanner scanner = new Scanner(System.in);
        int guess = scanner.nextInt();
        scanner.next();
        return guess;
    }

    public void playRound(){
        if(isInGame){
            Integer guess = getGuess();
            if(alreadyGuessed(guess)){
                return;
            }
            if(hasGuessed(guess)){
                return;
            }
            guesses.add(guess);
        }
    }

    private boolean alreadyGuessed(Integer guess){
        if(guesses.contains(guess)){
            System.out.println("Already guessed!");
            return true;
        }
        return false;
    }

    private boolean hasGuessed(Integer guess){
        isInGame = !(guess == guessingNumber);
        if(!isInGame){
            System.out.println("You guessed the number!");
        }
        return isInGame;
    }

}
