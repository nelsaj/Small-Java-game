package controller;

import java.util.Random;

import model.GameMap;
import model.Player;
import model.Positions.Position;
import model.Positions.Trap;
import model.Positions.Treasure;
import view.EventView;
import view.GameMapView;
import view.MainView;

public class GameMapController {
    MainView mainView;
    GameMap gameMap;

    GameMapController(MainView mainView) {
        this.gameMap = new GameMap();
        this.mainView = mainView;
    }

    public Position[][] generateMap () {
        return gameMap.generateMap();
    }

    //vet inte om den är bäst här
    public void digEvent (Position gameSpace, Player currentPlayer, Player opponentPlayer) {
        if (gameSpace instanceof Trap) 
            activateTrap(currentPlayer, opponentPlayer);
    }

    public void activateTrap(Player currentPlayer, Player opponentPlayer) {
        int randomIndex = new Random().nextInt(3);

        switch (randomIndex) {
            // remove points
            case 0:
                final int removerPoints = 1;
                currentPlayer.removePoints(removerPoints);
    
                mainView.eventMessage("Player "+currentPlayer.getPlayerNbr()+" lost "+removerPoints+" points.");
                break;
            
                //give points
            case 1:
                int points = currentPlayer.getScore() / 3; 
                currentPlayer.removePoints(points); 
                opponentPlayer.setScore(currentPlayer.getScore() + points); 
            
                mainView.eventMessage("Player "+opponentPlayer.getPlayerNbr()+" recieved "+points+" points from player "+currentPlayer.getPlayerNbr()+".");
                break;

            // remove life
            case 2:
                currentPlayer.removeLives(1);
        
                mainView.eventMessage("Player "+currentPlayer.getPlayerNbr()+" lost 1 life.");
                break;
        
            default:
                break;
        }
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
