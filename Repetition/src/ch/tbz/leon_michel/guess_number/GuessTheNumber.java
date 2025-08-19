package ch.tbz.leon_michel.guess_number;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class GuessTheNumber {
    private final int guessingNumber;
    private boolean isInGame;
    private final List<Integer> guesses;
    private final int min;
    private final int max;

    public GuessTheNumber(int min, int max) {
        this.guessingNumber = (int) (Math.random() * (max - min)  + min);
        guesses = new ArrayList<>();
        isInGame = true;
        this.min = min;
        this.max = max;
        playRound();
    }

    public boolean isInGame() {
        return isInGame;
    }

    private Integer getGuess() {
        Scanner scanner = new Scanner(System.in);
        printInputBanner();
        return scanner.nextInt();
    }

    private void printInputBanner(){
        System.out.println("Type a number between " + min + " and " + max + ":");
    }

    public void  playRound(){
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
        AtomicBoolean alreadyGuessed = new AtomicBoolean(false);
        guesses.forEach(guessInList -> {
            if(Objects.equals(guess, guessInList)){
                System.out.println("Already guessed!");
                alreadyGuessed.set(true);
            }
        });
        return alreadyGuessed.get();
    }

    private boolean hasGuessed(Integer guess){
        isInGame = !(guess == guessingNumber);
        if(!isInGame){
            System.out.println("You guessed the number!");
        } else {
            evaluateGuess(guess);
        }
        return !isInGame;
    }

    private void evaluateGuess(Integer guess){
        if(guess > guessingNumber){
            System.out.println("Guess lower!");
        } else {
            System.out.println("Guess higher!");
        }
    }

}
