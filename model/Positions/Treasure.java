package model.Positions;

public class Treasure extends Position {
    private int treasureID;
    private int size;

    public Treasure(int treasureID, int size) {
        setTreasureID(treasureID);
    }

    public void setTreasureID(int treasureID) {
        this.treasureID = treasureID;
    }
    public int getTreasureID() {
        return treasureID;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "S";
    }
}
