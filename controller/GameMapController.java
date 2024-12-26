package controller;

import model.Player;
import model.Positions.Position;
import model.Positions.Treasure;
import view.EventView;
import view.GameMapView;
import view.MainView;

public class GameMapController {

    GameMapView gameMapView;
    EventView eventView;

    GameMapController(MainView mainView) {
        // gameMapView = new GameMapView();
        // eventView = new EventView();
        this.mainView = mainView;
    }

    //TODO: View o event + switch?
    public void trap1 (Player player) {
        final int removerPoints = 1;
        player.removePoints(removerPoints);

        mainView.eventMessage("Player "+player.getPlayerNbr()+" lost "+removerPoints+" points.");
    } 
    public void trap2 (Player pointTaker, Player pointGiver) {
        int points = pointGiver.getScore() / 3; 
        pointGiver.removePoints(points); 
        pointTaker.setScore(pointTaker.getScore() + points); 
        
        mainView.eventMessage("Player "+pointTaker.getPlayerNbr()+" recieved "+points+" points from player "+pointGiver.getPlayerNbr()+".");
    }
    public void trap3 (Player player) {
        player.removeLives(1);
        
        mainView.eventMessage("Player "+player.getPlayerNbr()+" lost 1 life.");
    }
    
    
    public void surprise1 (Player player) {
        player.setLives(player.getLives() + 1);
        
        mainView.eventMessage("Player "+player.getPlayerNbr()+" gained 1 life.");
    }
    public void surprise2 (Player player) {
        mainView.eventMessage("Player "+player.getPlayerNbr()+" got "+player.getLives()+" more turns.");
    }
    public void surprise3 (Player player, Position[][] gameMap) {
        // int[][] treasureCoords; 
        // for (int row = 0; row < gameMap.length; row++) {
        //     for (int col = 0; col < gameMap[row].length; col++) {
        //         if(gameMap[row][col] instanceof Treasure) treasureCoords
        //     }
        // }

        //mainView.eventMessage("Player "+player.getPlayerNbr()+" got "+player.getLives()+" more turns.");
    }
}
