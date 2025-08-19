package ch.tbz.leon_michel;

public class GuessTheNumber {
    private final int guessingNumber;
    private boolean isInGame;

    public GuessTheNumber(int min, int max) {
        this.guessingNumber = (int) (Math.random() * (max - min)  + min);
    }

    public boolean isInGame() {
        return isInGame;
    }
}
