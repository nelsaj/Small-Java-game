package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.GameMap;
import model.Player;
import model.Positions.Position;
import model.Positions.TreasureShape;
import view.MainView;

/**
 * Kontrollerar spelets logik och hanterar spelhändelser.
 * 
 * @author Nelly Sajland, Elliot Collins
 */
public class MainController {
    private GameMapController gameMapController;
    private MainView view;
    private Position[][] gameMap;
    private Player player1;
    private Player player2;
    private Player currentTurn;

    /**
     * Skapar en ny instans av MainController och initialiserar spelet.
     * 
     * @author Nelly Sajland, Elliot Collins
     */
    public MainController() {
        view = new MainView(1000, 600, this);
        gameMapController = new GameMapController(view);
        this.gameMap = gameMapController.generateMap();
        this.player1 = new Player(1);
        this.player2 = new Player(2);
        this.currentTurn = player1;
    }

    /**
     * Hanterar när en position blivit uppgrävd.
     * 
     * @param pos positionen som grävts
     * 
     * @author Nelly Sajland
     */
    public void buttonPressed(int[] pos) {
        Position gameSpace = gameMap[pos[0]][pos[1]];
        view.changeButton(pos, gameMap[pos[0]][pos[1]].getColor());
        gameSpace.digEvent();
        boolean changeTurn = gameMapController.digEvent(gameSpace, currentTurn, getOpponentPlayer());

        checkIfGameDone();

        if (changeTurn) {
            if (currentTurn.equals(player1)) currentTurn = player2;
            else currentTurn = player1;
            view.changeCurrTurn(currentTurn.getPlayerNbr());
        }
    }

    /**
     * Hämtar motspelaren till den nuvarande spelaren.
     * 
     * @return motspelaren
     * 
     * @author Nelly Sajland
     */
    private Player getOpponentPlayer() {
        if (currentTurn.equals(player1)) return player2;
        else return player1;
    }

    /**
     * Kontrollerar om spelet är klart och om någon har vunnit.
     * 
     * @author Nelly Sajland, Elliot Collins
     */
    public void checkIfGameDone() {
        Player winningPlayer;
        String winningPlayerName;

        Player oppPlayer = getOpponentPlayer();

        if (currentTurn.getLives() == 0) {
            view.eventMessage("Player " + currentTurn.getPlayerNbr() + " har slut på liv. Player " + oppPlayer.getPlayerNbr() + " vinner med " + oppPlayer.getScore() + " poäng!!");
            view.disableMap();
            winningPlayerName = view.popUpEnterName();
            winningPlayer = oppPlayer;
            view.addPlayerToHighScorelist(winningPlayerName, Integer.toString(winningPlayer.getScore()));
        }

        TreasureShape[] treasureShapes = gameMapController.getMapModel().getTreasureShapes();
        for (int i = 0; i < treasureShapes.length; i++) {
            if (!treasureShapes[i].checkIfComplete()) break;
            if (i == treasureShapes.length - 1) {
                if (currentTurn.getScore() > oppPlayer.getScore()) {
                    winningPlayer = currentTurn;
                } else {
                    winningPlayer = oppPlayer;
                }

                view.eventMessage("Alla skatter hittade. Player " + winningPlayer.getPlayerNbr() + " vinner med " + winningPlayer.getScore() + " poäng!!");
                view.disableMap();
                winningPlayerName = view.popUpEnterName();
                view.addPlayerToHighScorelist(winningPlayerName, Integer.toString(winningPlayer.getScore()));
            }
        }
    }
}
