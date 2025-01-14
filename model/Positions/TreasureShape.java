package model.Positions;

/**
 * Klass som representerar kluster av skatter (skattformer).
 * 
 * @author Nelly Sajland
 */
public class TreasureShape {
    private int treasureID;
    private int size;
    private int spacesDug;

    private static final int[][][] allowedShapes = 
    {
        {{1, 0, 0},
        {0, 0 , 0},
        {0, 0, 0}},

        {{1, 1, 0},
        {0, 0, 0},
        {0, 0, 0}},

        {{1, 1, 1},
        {0, 0, 0},
        {0, 0, 0}},

        {{1, 1, 0},
        {0, 1, 0},
        {0, 0, 0}},

        {{1, 1, 1},
        {0, 1, 0},
        {0, 0, 0}},

        {{1, 1, 0},
        {1, 1, 0},
        {0, 0, 0}},

        {{1, 1, 1},
        {0, 0, 1},
        {0, 0, 0}},

        {{1, 1, 0},
        {0, 1, 1},
        {0, 0, 0}},

        {{0, 1, 0},
        {1, 1, 1},
        {0, 1, 0}}
    };

    /**
     * Initierar en TreasureShape med ID och storlek. Startvärde för spacesDug sätts till 0.
     * 
     * @param treasureID skattformens ID
     * @param size skattformens storlek
     * 
     * @author Nelly Sajland
     */
    public TreasureShape(int treasureID, int size) {
        this.treasureID = treasureID; this.size = size;
        spacesDug = 0;
    }

    /**
     * Hämtar storleken på skattformen.
     *
     * @return storleken på skattformen
     * 
     * @author Nelly Sajland
     */
    public int getSize() {
        return size;
    }

    /**
     * Hämtar skattformens ID.
     *
     * @return skattens ID
     * 
     * @author Nelly Sajland
     */
    public int getTreasureID() {
        return treasureID;
    }

    /**
     * Hämtar alla tillåtna former för skatter.
     *
     * @return en 3D-array som representerar tillåtna skattformer
     * 
     * @author Nelly Sajland
     */
    public static int[][][] getAllowedShapes() {
        return allowedShapes;
    }

    /**
     * Ökar antalet uppgrävda platser för skatten med 1.
     * 
     * @author Nelly Sajland
     */
    public void digged() {
        spacesDug++;
    }

    /**
     * Kontrollerar om hela skatten har grävts upp.
     *
     * @return true om alla platser har grävts upp, annars false
     * 
     * @author Nelly Sajland
     */
    public boolean checkIfComplete() {
        if (spacesDug == size) {
            return true;
        } else {
            return false;
        }
    }

}