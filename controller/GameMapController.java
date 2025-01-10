package controller;

import java.util.Random;

import model.GameMap;
import model.Player;
import model.Positions.Position;
import model.Positions.Trap;
import model.Positions.Treasure;
import model.Positions.TreasureShape;
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
        return gameMap.makeRandomMap();
    }

    //vet inte om den är bäst här
    public void digEvent (Position gameSpace, Player currentPlayer, Player opponentPlayer) {
        if (gameSpace instanceof Trap) 
            activateTrap(currentPlayer, opponentPlayer);
        else if(gameSpace instanceof Treasure)
            treasureCheck(gameSpace, currentPlayer);
        //mer if
    }

    public void treasureCheck (Position gameSpace, Player player) {
        TreasureShape[] treasureShapes = gameMap.getTreasureShapes();

        for (TreasureShape treasureShape : treasureShapes) {
            // hitta treasure shape
            if(treasureShape.getTreasureID() == ((Treasure) gameSpace).getTreasureID()) {
                // markera som grävd
                treasureShape.digged();
                mainView.eventMessage("Player "+player.getPlayerNbr()+" grävde upp en del av en skatt!");

                // kolla om klar
                if(treasureShape.checkIfComplete()) {
                    int points = treasureShape.getSize(); 
                    player.addPoints(points); 
                    mainView.changePoints(player.getPlayerNbr(), String.valueOf(player.getScore()));
                    mainView.eventMessage("Player "+player.getPlayerNbr()+" grävde upp en skatt! Plus "+points+" poäng.");
                }
                break;
            }
        }

    }

    public void activateTrap(Player currentPlayer, Player opponentPlayer) {
        int randomIndex = new Random().nextInt(3);
        randomIndex = 1;

        switch (randomIndex) {
            // remove points
            case 0:
                final int removerPoints = 1;
                currentPlayer.removePoints(removerPoints);
    
                mainView.changePoints(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getScore()));
                mainView.eventMessage("Player "+currentPlayer.getPlayerNbr()+" lost "+removerPoints+" points.");
                break;
            
            //give points
            case 1:
                //FIXA TILL
                int points = currentPlayer.getScore() / 2; 
                currentPlayer.removePoints(points); 
                opponentPlayer.addPoints(points); 
            
                mainView.changePoints(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getScore()));
                mainView.changePoints(opponentPlayer.getPlayerNbr(), String.valueOf(opponentPlayer.getScore()));
                mainView.eventMessage("Player "+opponentPlayer.getPlayerNbr()+" recieved "+points+" points from player "+currentPlayer.getPlayerNbr()+".");
                break;

            // remove life
            case 2:
                currentPlayer.removeLives(1);
        
                // fixa sen
                mainView.eventMessage("Player "+currentPlayer.getPlayerNbr()+" lost 1 life.");
                break;
        
            default:
                break;
        }
    }

    public void checkIfGameDone (Player currPlayer, Player oppPlayer) {
        if(currPlayer.getLives()==0) mainView.eventMessage("Player "+currPlayer.getPlayerNbr()+" har slut på liv. Player "+oppPlayer.getPlayerNbr()+" vinner med "+oppPlayer.getScore()+" poäng!!");

        TreasureShape[] treasureShapes = gameMap.getTreasureShapes();
        for (int i = 0; i < treasureShapes.length; i++) {
            if(!treasureShapes[i].checkIfComplete()) break;
            if(i == treasureShapes.length-1) {
                Player winningPlayer;
                if(currPlayer.getScore() > oppPlayer.getScore()) {
                    winningPlayer = currPlayer;
                }
                else {
                    winningPlayer = oppPlayer;
                }

                mainView.eventMessage("Alla skatter hittade. Player "+winningPlayer.getPlayerNbr()+" vinner med "+winningPlayer.getScore()+" poäng!!");
            }
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
