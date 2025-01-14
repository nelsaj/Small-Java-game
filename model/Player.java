package model;

/**
 * Representerar en spelare med liv, poäng och ett nummer.
 * 
 * @author Nelly Sajland
 */
public class Player {
    private int lives;
    private int score;
    private int playerNbr;

    /**
     * Skapar en ny spelare med ett angivet nummer. Startvärden för liv och poäng sätts till 3 respektive 0.
     *
     * @param playerNbr spelarens nummer
     * 
     * @author Nelly Sajland
     */
    public Player(int playerNbr) {
        setLives(3);
        setScore(0);
        setPlayerNbr(playerNbr); 
    }

    /**
     * Sätter spelarens antal liv.
     *
     * @param lives antalet liv att sätta
     * 
     * @author Nelly Sajland
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Hämtar spelarens antal liv.
     *
     * @return spelarens antal liv
     * 
     * @author Nelly Sajland
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sätter spelarens poäng.
     *
     * @param score poängen att sätta
     * 
     * @author Nelly Sajland
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Hämtar spelarens poäng.
     *
     * @return spelarens poäng
     * 
     * @author Nelly Sajland
     */
    public int getScore() {
        return score;
    }

    /**
     * Sätter spelarens nummer.
     *
     * @param playerNbr spelarens nummer att sätta
     * 
     * @author Nelly Sajland
     */
    public void setPlayerNbr(int playerNbr) {
        this.playerNbr = playerNbr;
    }

    /**
     * Hämtar spelarens nummer.
     *
     * @return spelarens nummer
     * 
     * @author Nelly Sajland
     */
    public int getPlayerNbr() {
        return playerNbr;
    }

    /**
     * Lägger till poäng till spelarens totala poäng.
     *
     * @param points antalet poäng att lägga till
     * 
     * @author Nelly Sajland
     */
    public void addPoints(int points) {
        setScore(score + points);
    }

    /**
     * Lägger till liv till spelarens totala antal liv.
     *
     * @param lives antalet liv att lägga till
     * 
     * @author Nelly Sajland
     */
    public void addLives(int lives) {
        setLives(this.lives + lives);
    }

    /**
     * Tar bort poäng från spelarens totala poäng. Poängen kan inte bli mindre än 0.
     *
     * @param points antalet poäng att ta bort
     * 
     * @author Nelly Sajland
     */
    public void removePoints(int points) {
        if (points > this.score) {
            setScore(0);
        } else {
            setScore(this.score - points);
        }
    }

    /**
     * Tar bort liv från spelarens totala antal liv. Antalet liv kan inte bli mindre än 0.
     *
     * @param lives antalet liv att ta bort
     * 
     * @author Nelly Sajland
     */
    public void removeLives(int lives) {
        if (lives > this.lives) {
            setLives(0);
        } else {
            setLives(this.lives - lives);
        }
    }
}
