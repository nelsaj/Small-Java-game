package model.Positions;

public abstract class Position {
    protected boolean hasBeenDigged;

    public void digEvent() {hasBeenDigged = true;}
    public boolean getDigStatus() {return hasBeenDigged;}
}
