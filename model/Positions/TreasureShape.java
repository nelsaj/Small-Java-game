package model.Positions;

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

    public TreasureShape(int treasureID, int size) {
        this.treasureID = treasureID; this.size = size;
        spacesDug = 0;
    }

    public int getSize() {return size;}
    public int getTreasureID() {return treasureID;}
    public static int[][][] getAllowedShapes() {return allowedShapes;}

    public void digged () {spacesDug++;}
    public boolean checkIfComplete () {
        if(spacesDug == size) return true;
        else return false;
    }
}