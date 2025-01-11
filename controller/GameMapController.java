package controller;

import java.util.Random;

import model.GameMap;
import model.Player;
import model.Positions.Neutral;
import model.Positions.Position;
import model.Positions.Surprise;
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
    public boolean digEvent (Position gameSpace, Player currentPlayer, Player opponentPlayer) {
        boolean changeTurn = true;

        if (gameSpace instanceof Trap) 
            activateTrap(currentPlayer, opponentPlayer);

        else if(gameSpace instanceof Surprise)
            changeTurn = activateSurprise(currentPlayer, opponentPlayer);

        else if(gameSpace instanceof Treasure)
            treasureCheck(gameSpace, currentPlayer);

        else if (gameSpace instanceof Neutral)
            mainView.eventMessage("");
        
        //mer if
        return changeTurn;
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
                mainView.changeLifesGUI(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getLives()));
        
                mainView.eventMessage("Player "+currentPlayer.getPlayerNbr()+" lost 1 life.");
                break;
        
            default:
                break;
        }
    }
    
    public boolean activateSurprise(Player currPlayer, Player oppPlayer) {
        int randomIndex = new Random().nextInt(4);
        boolean changeTurn = true;

        switch (randomIndex) {
            case 0:
                //add life
                currPlayer.addLives(1);
                mainView.changeLifesGUI(currPlayer.getPlayerNbr(), String.valueOf(currPlayer.getLives()));
        
                mainView.eventMessage("Player "+currPlayer.getPlayerNbr()+" gained 1 life.");
                break;
            case 1:
                //get turns based on lives
                changeTurn = false;

                mainView.eventMessage("Player "+currPlayer.getPlayerNbr()+" got "+currPlayer.getLives()+" more turns.");
                break;
            
            case 2: 
                //part of treasure is dug up
                break;
            case 3:
                //random position is dug

                break;
        
            default:
                break;
        }

        return changeTurn;
    }

    public void checkIfGameDone (Player currPlayer, Player oppPlayer) {
        Player winningPlayer;

        if(currPlayer.getLives()==0) {
            mainView.eventMessage("Player "+currPlayer.getPlayerNbr()+" har slut på liv. Player "+oppPlayer.getPlayerNbr()+" vinner med "+oppPlayer.getScore()+" poäng!!");
            mainView.disableMap();
        }

        TreasureShape[] treasureShapes = gameMap.getTreasureShapes();
        for (int i = 0; i < treasureShapes.length; i++) {
            if(!treasureShapes[i].checkIfComplete()) break;
            if(i == treasureShapes.length-1) {
                if(currPlayer.getScore() > oppPlayer.getScore()) {
                    winningPlayer = currPlayer;
                }
                else {
                    winningPlayer = oppPlayer;
                }

                mainView.eventMessage("Alla skatter hittade. Player "+winningPlayer.getPlayerNbr()+" vinner med "+winningPlayer.getScore()+" poäng!!");
                mainView.disableMap();
            }
        }
    }

}
