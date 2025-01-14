package model.Positions;

/**
 * Neutral position på spelplanen
 * 
 * @author Nelly Sajland
 */
public class Neutral extends Position {
    
    /**
     * Returnerar en sträng som ger info om vilken position. (I detta fall ges ingen)
     * 
     * @return Tom sträng
     * 
     * @author Nelly Sajland
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * Returnerar en sträng med HEX-kod för färgen som ska representera positionen på spelplanen.
     * 
     * @return HEX-kod
     * 
     * @author Nelly Sajland
     */
    public String getColor() {return "FFFFFF";}
}
