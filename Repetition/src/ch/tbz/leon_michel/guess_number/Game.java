package ch.tbz.leon_michel.guess_number;

public class Game {
    private final GuessTheNumber guessTheNumber;

    public Game (int min, int max){
        guessTheNumber = new GuessTheNumber(min, max);
        gameLoop();
    }

    private void gameLoop(){
        do {
            guessTheNumber.playRound();
        } while (guessTheNumber.isInGame());
        System.out.println("YOU WON!");
    }
}
