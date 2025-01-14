package model.Positions;

import controller.GameMapController;

/**
 * En position på spelplanen
 * 
 * @author Nelly Sajland
 */
public abstract class Position {
    private boolean hasBeenDigged;

    /**
     * Ändrar position till att vara uppgrävd.
     * 
     * @author Nelly Sajland
     */
    public void digEvent() {hasBeenDigged = true;}
    /**
    * Hämtar information om positionen har blivit uppgrävd. 
    * 
    * @return boolean om positionen har blivit uppgrävd
    *
    * @author Nelly Sajland
    */
    public boolean getDigStatus() {return hasBeenDigged;}
    /**
     * Returnerar en sträng med HEX-kod för färgen som ska representera positionen på spelplanen.
     * 
     * @return HEX-kod
     * 
     * @author Nelly Sajland
     */
    public abstract String getColor();
}
