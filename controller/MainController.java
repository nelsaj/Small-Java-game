package controller;

import model.GameMap;
import model.Player;
import model.Positions.Position;
import model.Positions.TreasureShape;
import view.MainView;

public class MainController {
    private GameMapController gameMapController;

    private MainView view;

    private Position[][] gameMap;
    private Player player1;
    private Player player2;
    private Player currentTurn;
    //currentTurn här eller i player??

    
    public MainController(){
        view = new MainView(1000,600, this);
        gameMapController = new GameMapController(view);

        this.gameMap = gameMapController.generateMap();
        this.player1 = new Player(1); this.player2 = new Player(2);
        this.currentTurn = player1;
    }

    public void buttonPressed (int[] pos) {
        Position gameSpace = gameMap[pos[0]][pos[1]];
        //FIXA MER HÄR
        view.changeButton(pos, gameMap[pos[0]][pos[1]].toString());
        gameSpace.digEvent();
        gameMapController.digEvent(gameSpace, currentTurn, getOpponentPlayer());

        gameMapController.checkIfGameDone(currentTurn, getOpponentPlayer());
        
        //change turn
        if(true) {
            if(currentTurn.equals(player1)) currentTurn = player2;
            else currentTurn = player1;
            view.changeCurrTurn(currentTurn.getPlayerNbr());
        }
    }

    private Player getOpponentPlayer () {
        if(currentTurn.equals(player1)) return player2;
        else return player1;
    }
}
