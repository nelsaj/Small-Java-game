package controller;

import java.util.ArrayList;
import java.util.Random;

import model.GameMap;
import model.Player;
import model.Positions.Neutral;
import model.Positions.Position;
import model.Positions.Surprise;
import model.Positions.Trap;
import model.Positions.Treasure;
import model.Positions.TreasureShape;
import view.MainView;

/**
 * Kontrollerar spelplanen och hanterar spelhändelser.
 * 
 * @author Nelly Sajland, Elliot Collins
 */
public class GameMapController {
    private MainView mainView;
    private GameMap gameMap;
    private Position[][] map;
    private int extraTurns;
    private String extraMessage;

    /**
     * Skapar en ny instans av GameMapController.
     *
     * @param mainView huvudvyn som uppdateras med händelser
     * 
     * @author Nelly Sajland, Elliot Collins
     */
    public GameMapController(MainView mainView) {
        this.gameMap = new GameMap();
        this.mainView = mainView;
        extraTurns = 0;
    }

    /**
     * Genererar en spelplan.
     *
     * @return en 2D-array som representerar spelplanen
     * 
     * @author Nelly Sajland
     */
    public Position[][] generateMap() {
        gameMap = new GameMap();
        map = gameMap.getMap();
        return map;
    }

    /**
     * Hämtar modellen för spelkartan.
     *
     * @return GameMap-objektet som representerar spelplanen
     * 
     * @author Nelly Sajland
     */
    public GameMap getMapModel() {
        return gameMap;
    }

    /**
     * Hanterar händelser när en spelare gräver en position.
     *
     * @param gameSpace positionen som grävdes
     * @param currentPlayer nuvarande spelare
     * @param opponentPlayer motspelaren
     * @return true om inga extra drag återstår, annars false
     * 
     * @author Nelly Sajland, Elliot Collins
     */
    public boolean digEvent(Position gameSpace, Player currentPlayer, Player opponentPlayer) {
        if (extraTurns > 0) extraTurns--;

        if (gameSpace instanceof Treasure)
            treasureCheck(gameSpace, currentPlayer);

        else if (gameSpace instanceof Trap)
            activateTrap(currentPlayer, opponentPlayer);

        else if (gameSpace instanceof Surprise)
            activateSurprise(currentPlayer, opponentPlayer);

        else if (gameSpace instanceof Neutral)
            eventMessage("");

        return !(extraTurns > 0);
    }

    /**
     * Kontrollerar om en skatt är komplett och uppdaterar poäng.
     *
     * @param gameSpace positionen som grävdes
     * @param player nuvarande spelare
     * 
     * @author Nelly Sajland, Elliot Collins
     */
    public void treasureCheck(Position gameSpace, Player player) {
        TreasureShape[] treasureShapes = gameMap.getTreasureShapes();

        for (TreasureShape treasureShape : treasureShapes) {
            if (treasureShape.getTreasureID() == ((Treasure) gameSpace).getTreasureID()) {
                treasureShape.digged();
                eventMessage("Player " + player.getPlayerNbr() + " grävde upp en del av en skatt!");

                if (treasureShape.checkIfComplete()) {
                    int points = treasureShape.getSize();
                    player.addPoints(points);
                    mainView.changePoints(player.getPlayerNbr(), String.valueOf(player.getScore()));
                    eventMessage("Player " + player.getPlayerNbr() + " grävde upp en skatt! Plus " + points + " poäng.");
                }
                break;
            }
        }
    }

    /**
     * Aktiverar en fälla och tillämpar dess effekt.
     *
     * @param currentPlayer nuvarande spelare
     * @param opponentPlayer motspelaren
     * 
     * @author Nelly Sajland, Elliot Collins
     */
    public void activateTrap(Player currentPlayer, Player opponentPlayer) {
        int randomIndex = new Random().nextInt(3);
        switch (randomIndex) {
            case 0:
                final int removerPoints = 1;
                currentPlayer.removePoints(removerPoints);
                mainView.changePoints(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getScore()));
                eventMessage("Player " + currentPlayer.getPlayerNbr() + " förlorade poäng.");
                break;

            case 1:
                int points = (int) currentPlayer.getScore() / 2;
                currentPlayer.removePoints(points);
                opponentPlayer.addPoints(points);
                mainView.changePoints(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getScore()));
                mainView.changePoints(opponentPlayer.getPlayerNbr(), String.valueOf(opponentPlayer.getScore()));
                eventMessage("Player " + opponentPlayer.getPlayerNbr() + " fick " + points + " poäng från player " + currentPlayer.getPlayerNbr() + ".");
                break;

            case 2:
                currentPlayer.removeLives(1);
                mainView.changeLifesGUI(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getLives()));
                eventMessage("Player " + currentPlayer.getPlayerNbr() + " förlorade 1 liv.");
                break;

            default:
                break;
        }
    }

    /**
     * Aktiverar en överraskning och tillämpar dess effekt.
     *
     * @param currPlayer nuvarande spelare
     * @param oppPlayer motspelaren
     * 
     * @author Nelly Sajland, Elliot Collins
     */
    public void activateSurprise(Player currPlayer, Player oppPlayer) {
        int randomIndex = new Random().nextInt(4);

        switch (randomIndex) {
            case 0:
                currPlayer.addLives(1);
                mainView.changeLifesGUI(currPlayer.getPlayerNbr(), String.valueOf(currPlayer.getLives()));
                eventMessage("Player " + currPlayer.getPlayerNbr() + " fick 1 liv.");
                break;
            case 1:
                extraTurns = currPlayer.getLives();
                String message = "Player " + currPlayer.getPlayerNbr() + " fick extra drag. " + currPlayer.getLives() + " stycken.";
                eventMessage(message);
                extraMessage = message;
                break;
            case 2: {
                ArrayList<int[]> treasureCoords = gameMap.getTreasureCoords();
                int[] randomCoord;
                do {
                    randomCoord = treasureCoords.get(new Random().nextInt(treasureCoords.size()));
                } while (map[randomCoord[0]][randomCoord[1]].getDigStatus());

                extraMessage = "Player " + currPlayer.getPlayerNbr() + " får en del av en skatt uppgrävd.";
                map[randomCoord[0]][randomCoord[1]].digEvent();
                digEvent(map[randomCoord[0]][randomCoord[1]], currPlayer, oppPlayer);
                mainView.changeButton(randomCoord, map[randomCoord[0]][randomCoord[1]].getColor());
                break;
            }
            case 3:
                int maxX = gameMap.getXMax();
                int maxY = gameMap.getYMax();
                int randomX;
                int randomY;
                do {
                    randomX = new Random().nextInt(maxX);
                    randomY = new Random().nextInt(maxY);
                } while (map[randomX][randomY].getDigStatus());
                int[] freeCoords = new int[]{randomX, randomY};

                map[freeCoords[0]][freeCoords[1]].digEvent();
                digEvent(map[freeCoords[0]][freeCoords[1]], currPlayer, oppPlayer);
                mainView.changeButton(freeCoords, map[freeCoords[0]][freeCoords[1]].getColor());
                extraMessage = "Player " + currPlayer.getPlayerNbr() + " får en slumpmässig ruta uppgrävd." + freeCoords[0] + " " + freeCoords[1];
                break;

            default:
                break;
        }
    }

    /**
     * Uppdaterar meddelanden i vyn.
     *
     * @param message meddelandet som ska visas
     * 
     * @author Nelly Sajland
     */
    private void eventMessage(String message) {
        if (extraMessage == null) mainView.eventMessage(message);
        else {
            mainView.eventMessage(extraMessage + "\n" + message);
            if (extraTurns == 0) extraMessage = null;
        }
    }
}
