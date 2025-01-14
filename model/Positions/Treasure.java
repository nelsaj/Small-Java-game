package model.Positions;

/**
 * Skattposition på spelplanen
 * 
 * @author Nelly Sajland
 */
public class Treasure extends Position {
    private int treasureID;

    /**
     * Initierar en Treasure och tilldelar ett ID till den.
     *
     * @param treasureID skattens ID
     * 
     * @author Nelly Sajland
     */
    public Treasure(int treasureID) {
        setTreasureID(treasureID);
    }

    /**
     * Sätter skattens ID.
     *
     * @param treasureID skattens ID att sätta
     * 
     * @author Nelly Sajland
     */
    public void setTreasureID(int treasureID) {
        this.treasureID = treasureID;
    }
    /**
     * Hämtar skattens ID.
     *
     * @return treasureID skattens ID
     * 
     * @author Nelly Sajland
     */
    public int getTreasureID() {
        return treasureID;
    }

    /**
     * Returnerar en sträng som ger info om vilken position.
     * 
     * @return "S"
     * 
     * @author Nelly Sajland
     */
    @Override
    public String toString() {
        return "S";
    }

    /**
     * Returnerar en sträng med HEX-kod för färgen som ska representera positionen på spelplanen.
     * 
     * @return HEX-kod
     * 
     * @author Nelly Sajland
     */
    public String getColor() {return "ffcd00";}
}
