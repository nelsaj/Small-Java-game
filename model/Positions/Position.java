package model.Positions;

import controller.GameMapController;

public abstract class Position {
    private boolean hasBeenDigged;

    public void digEvent() {hasBeenDigged = true;}
    public boolean getDigStatus() {return hasBeenDigged;}
}
