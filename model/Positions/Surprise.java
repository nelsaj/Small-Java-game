package model.Positions;

/**
 * Överraskningsposition på spelplanen
 * 
 * @author Nelly Sajland
 */
public class Surprise extends Position {
    
    /**
     * Returnerar en sträng som ger info om vilken position.
     * 
     * @return "Ö"
     * 
     * @author Nelly Sajland
     */
    @Override
    public String toString() {
        return "Ö";
    }

    /**
     * Returnerar en sträng med HEX-kod för färgen som ska representera positionen på spelplanen.
     * 
     * @return HEX-kod
     * 
     * @author Nelly Sajland
     */
    public String getColor() {return "006cff";}
}
