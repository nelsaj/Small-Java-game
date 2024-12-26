package model;

public class Player {
    private int lives;
    private int score;
    private int playerNbr;

    Player(int playerNbr) {
        setLives(3); setScore(0);
        setPlayerNbr(playerNbr);
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

    public void removePoints (int points) {
        if (points > this.score) setScore(0);
        else setScore(this.score - points);
    }
    public void removeLives (int lives) {
        if (lives > this.lives) setLives(0);
        else setLives(this.lives - lives);
    }
}
