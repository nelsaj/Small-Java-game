package controller;

import model.GameMap;
import model.Positions.Position;
import view.MainView;

public class MainController {
    private GameMapController gameMapController;

    private MainView view;
    private Position[][] gameMap;
    
    public MainController(){
        view = new MainView(1000,600, this);
        gameMapController = new GameMapController(view);
        this.gameMap = gameMapController.generateMap();
    }

    public void buttonPressed (int[] pos) {
        gameMap[pos[0]][pos[1]].digEvent();
        //FIXA MER HÄR
        view.changeButton(pos, gameMap[pos[0]][pos[1]].toString());
    }
}
