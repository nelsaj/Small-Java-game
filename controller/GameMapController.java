package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

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
                eventMessage("Player "+currentPlayer.getPlayerNbr()+" lost "+removerPoints+" points.");
                break;
            
            //give points
            case 1:
                int points = (int) currentPlayer.getScore() / 2; 

                currentPlayer.removePoints(points); 
                opponentPlayer.addPoints(points); 
            
                mainView.changePoints(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getScore()));
                mainView.changePoints(opponentPlayer.getPlayerNbr(), String.valueOf(opponentPlayer.getScore()));
                eventMessage("Player "+opponentPlayer.getPlayerNbr()+" recieved "+points+" points from player "+currentPlayer.getPlayerNbr()+".");
                break;

            // remove life
            case 2:
                currentPlayer.removeLives(1);
                mainView.changeLifesGUI(currentPlayer.getPlayerNbr(), String.valueOf(currentPlayer.getLives()));
        
                eventMessage("Player "+currentPlayer.getPlayerNbr()+" lost 1 life.");
                break;
        
            default:
                break;
        }
    }
    
    public void activateSurprise(Player currPlayer, Player oppPlayer) {
        int randomIndex = new Random().nextInt(4);
        randomIndex = 1;

        switch (randomIndex) {
            case 0:
                //add life
                currPlayer.addLives(1);
                mainView.changeLifesGUI(currPlayer.getPlayerNbr(), String.valueOf(currPlayer.getLives()));
        
                eventMessage("Player "+currPlayer.getPlayerNbr()+" gained 1 life.");
                break;
            case 1:
                //get turns based on lives
                extraTurns = currPlayer.getLives();

                String message = "Player "+currPlayer.getPlayerNbr()+" fick extra drag. "+currPlayer.getLives()+" stycken.";
                eventMessage(message);
                extraMessage = message;

                break;
                
                case 2: 
                //part of treasure is dug up
                int treasureAmount = gameMap.getTreasureAmount();
                TreasureShape randomTreasureShape;

                do {
                    randomTreasureShape = gameMap.getTreasureShapes()[new Random().nextInt(treasureAmount)];
                } while(randomTreasureShape.checkIfComplete());
                
                
                
                break;
                case 3:
                //random position is dug
                int x = gameMap.getXMax(); int y = gameMap.getYMax();
                int randomX; int randomY;
                do {
                    randomX = new Random().nextInt(x); randomY = new Random().nextInt(y);
                } while(map[randomX][randomY].getDigStatus());
                
                extraMessage = "Player "+currPlayer.getPlayerNbr()+" får en random ruta uppgrävd."+randomX+" "+randomY;

                map[randomX][randomY].digEvent();
                digEvent(map[randomX][randomY], currPlayer, oppPlayer);
                mainView.changeButton(new int[]{randomX, randomY}, map[randomX][randomY].toString());
                
                break;
        
            default:
                break;

            }
    }

    public void checkIfGameDone (Player currPlayer, Player oppPlayer) {
        Player winningPlayer;
        String winningPlayerName;
        if(currPlayer.getLives()==0) {
            mainView.eventMessage("Player "+currPlayer.getPlayerNbr()+" har slut på liv. Player "+oppPlayer.getPlayerNbr()+" vinner med "+oppPlayer.getScore()+" poäng!!");
            mainView.disableMap();
            winningPlayerName = mainView.popUpEnterName();
            winningPlayer = oppPlayer;
            addPlayerToHighScorelist(winningPlayerName, Integer.toString(winningPlayer.getScore()));
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
                winningPlayerName = mainView.popUpEnterName();
                addPlayerToHighScorelist(winningPlayerName, Integer.toString(winningPlayer.getScore()));
            }
        }
    }

    private void eventMessage (String message) {
        if(extraMessage==null) mainView.eventMessage(message);
        else {
            mainView.eventMessage(extraMessage+"\n"+message);
            if(extraTurns == 0) extraMessage = null;
        }
    }

    
    public void addPlayerToHighScorelist(String name, String points) {
        String filePath = "./Highscore.txt";
        List<String[]> linesAsArrays = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] stringLineArr = line.split(",");
                linesAsArrays.add(stringLineArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainView, "Error reading the high score file.");
            return;
        }
    
        String[] newWinningPlayer = {name, points};
        linesAsArrays.add(newWinningPlayer);
    
        linesAsArrays.sort((a, b) -> Integer.compare(
            Integer.parseInt(b[1].trim()), 
            Integer.parseInt(a[1].trim())
        ));

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (String[] ele : linesAsArrays) {
                String insertStringIntoHighscore = ele[0] + "," + ele[1] + "\n";
                fileWriter.write(insertStringIntoHighscore);
            }
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainView, "Something went wrong while writing the high score file.");
        }
    }

}
