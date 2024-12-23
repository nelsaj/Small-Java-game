package model.Positions;

public class Treasure extends Position {
    private int treasureID;

    public Treasure(int treasureID) {
        setTreasureID(treasureID);
    }

    public void setTreasureID(int treasureID) {
        this.treasureID = treasureID;
    }
    public int getTreasureID() {
        return treasureID;
    }

    public void digEvent () {
        hasBeenDigged = true;
    }
}
