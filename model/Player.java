package model;

public class Player {
    private int lives;
    private int score;
    private int playerNbr;

    Player(int playerNbr) {

    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    public int getLives() {
        return lives;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public void setPlayerNbr(int playerNbr) {
        this.playerNbr = playerNbr;
    }
    public int getPlayerNbr() {
        return playerNbr;
    }
}
