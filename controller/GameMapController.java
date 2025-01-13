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
import view.EventView;
import view.GameMapView;
import view.MainView;

public class GameMapController {
    MainView mainView;
    GameMap gameMap;
    Position[][] map;

    int extraTurns;
    String extraMessage;

    GameMapController(MainView mainView) {
        this.gameMap = new GameMap();
        this.mainView = mainView;

        extraTurns = 0;
    }

    public Position[][] generateMap () {
        gameMap.makeRandomMap(); map = gameMap.getMap();
        return map;
    }
    public GameMap getMapModel () {
        return gameMap;
    }

    //vet inte om den är bäst här
    public boolean digEvent (Position gameSpace, Player currentPlayer, Player opponentPlayer) {
        if(extraTurns > 0) extraTurns--;

        if (gameSpace instanceof Trap) 
            activateTrap(currentPlayer, opponentPlayer);

        else if(gameSpace instanceof Surprise)
            activateSurprise(currentPlayer, opponentPlayer);

        else if(gameSpace instanceof Treasure)
            treasureCheck(gameSpace, currentPlayer);

        else if (gameSpace instanceof Neutral)
            eventMessage("");
        
        return !(extraTurns > 0);
    }

    public void treasureCheck (Position gameSpace, Player player) {
        TreasureShape[] treasureShapes = gameMap.getTreasureShapes();

        for (TreasureShape treasureShape : treasureShapes) {
            // hitta treasure shape
            if(treasureShape.getTreasureID() == ((Treasure) gameSpace).getTreasureID()) {
                // markera som grävd
                treasureShape.digged();
                eventMessage("Player "+player.getPlayerNbr()+" grävde upp en del av en skatt!");

                // kolla om klar
                if(treasureShape.checkIfComplete()) {
                    int points = treasureShape.getSize(); 
                    player.addPoints(points); 
                    mainView.changePoints(player.getPlayerNbr(), String.valueOf(player.getScore()));
                    eventMessage("Player "+player.getPlayerNbr()+" grävde upp en skatt! Plus "+points+" poäng.");
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
                eventMessage("Player "+currentPlayer.getPlayerNbr()+" förlorade poäng.");
                break;
            
            //give points
            case 1:
                int points = (int) currentPlayer.getScore() / 2; 

                currentPlayer.removePoints(points); 
                opponentPlayer.addPoints(points); 
            
                mainView.changePoints(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getScore()));
                mainView.changePoints(opponentPlayer.getPlayerNbr(), String.valueOf(opponentPlayer.getScore()));
                eventMessage("Player "+opponentPlayer.getPlayerNbr()+" fick "+points+" poäng från player "+currentPlayer.getPlayerNbr()+".");
                break;

            // remove life
            case 2:
                currentPlayer.removeLives(1);
                mainView.changeLifesGUI(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getLives()));
        
                eventMessage("Player "+currentPlayer.getPlayerNbr()+" förlorade 1 liv.");
                break;
        
            default:
                break;
        }
    }
    
    public void activateSurprise(Player currPlayer, Player oppPlayer) {
        int randomIndex = new Random().nextInt(4);

        switch (randomIndex) {
            case 0:
                //add life
                currPlayer.addLives(1);
                mainView.changeLifesGUI(currPlayer.getPlayerNbr(), String.valueOf(currPlayer.getLives()));
        
                eventMessage("Player "+currPlayer.getPlayerNbr()+" fick 1 liv.");
                break;
            case 1:
                //get turns based on lives
                extraTurns = currPlayer.getLives();

                String message = "Player "+currPlayer.getPlayerNbr()+" fick extra drag. "+currPlayer.getLives()+" stycken.";
                eventMessage(message);
                extraMessage = message;

                break;
                
                case 2: {
                //part of treasure is dug up
                ArrayList <int[]> treasureCoords = gameMap.getTreasureCoords();
                int[] randomCoord;
                do {
                    randomCoord = treasureCoords.get(new Random().nextInt(treasureCoords.size()));
                } while(map[randomCoord[0]][randomCoord[1]].getDigStatus());

                extraMessage = "Player "+currPlayer.getPlayerNbr()+" får en del av en skatt uppgrävd.";

                map[randomCoord[0]][randomCoord[1]].digEvent();
                digEvent(map[randomCoord[0]][randomCoord[1]], currPlayer, oppPlayer);
                mainView.changeButton(randomCoord, map[randomCoord[0]][randomCoord[1]].toString());

                break;
                }
                
                case 3:
                //random position is dug
                int[] freeCoords = gameMap.getFreeCoords();
                
                map[freeCoords[0]][freeCoords[1]].digEvent();
                digEvent(map[freeCoords[0]][freeCoords[1]], currPlayer, oppPlayer);
                mainView.changeButton(freeCoords, map[freeCoords[0]][freeCoords[1]].toString());
                
                extraMessage = "Player "+currPlayer.getPlayerNbr()+" får en slumpmässig ruta uppgrävd."+freeCoords[0]+" "+freeCoords[1];
                break;
        
            default:
                break;

            }
    }

    private void eventMessage (String message) {
        if(extraMessage==null) mainView.eventMessage(message);
        else {
            mainView.eventMessage(extraMessage+"\n"+message);
            if(extraTurns == 0) extraMessage = null;
        }
    }
}
