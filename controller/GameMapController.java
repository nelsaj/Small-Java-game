package controller;

import model.Player;
import model.Positions.Position;
import view.EventView;
import view.GameMapView;

public class GameMapController {

    GameMapView gameMapView;
    EventView eventView;

    GameMapController() {
        gameMapView = new GameMapView();
        eventView = new EventView();
    }

    //TODO: View o event + switch?
    public void trap1 (Player player) {
        final int removerPoints = 1;
        player.removePoints(removerPoints);

        eventView.showMessage("Player "+player.getPlayerNbr()+" lost "+removerPoints+" points.");
    } 
    public void trap2 (Player pointTaker, Player pointGiver) {
        int points = pointGiver.getScore() / 3; 
        pointGiver.removePoints(points); 
        pointTaker.setScore(pointTaker.getScore() + points); 
        
        eventView.showMessage("Player "+pointTaker.getPlayerNbr()+" recieved "+points+" points from player "+pointGiver.getPlayerNbr()+".");
    }
    public void trap3 (Player player) {
        player.removeLives(1);
        
        eventView.showMessage("Player "+player.getPlayerNbr()+" lost 1 life.");
    }
    
    
    public void surprise1 (Player player) {
        player.setLives(player.getLives() + 1);
        
        eventView.showMessage("Player "+player.getPlayerNbr()+" gained 1 life.");
    }
    public void surprise2 (Player player) {
        eventView.showMessage("Player "+player.getPlayerNbr()+" got "+player.getLives()+" more turns.");
    }
    public void surprise3 (Player player, Position[][] gameMap) {
        // int[][] treasureCoords;
        // for (int row = 0; row < gameMap.length; row++) {
        //     for (int col = 0; col < gameMap[row].length; col++) {
        //         if(gameMap[row][col])
        //     }
        // }

        //eventView.showMessage("Player "+player.getPlayerNbr()+" got "+player.getLives()+" more turns.");
    }
}
