package model.Positions;

/**
 * Fällaposition på spelplanen
 * 
 * @author Nelly Sajland
 */
public class Trap extends Position {
    
    /**
     * Returnerar en sträng som ger info om vilken position.
     * 
     * @return "F"
     * 
     * @author Nelly Sajland
     */
    @Override
    public String toString() {
        return "F";
    }

    /**
     * Returnerar en sträng med HEX-kod för färgen som ska representera positionen på spelplanen.
     * 
     * @return HEX-kod
     * 
     * @author Nelly Sajland
     */
    public String getColor() {return "ff3200";}
}
