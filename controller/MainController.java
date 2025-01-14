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

public class MainController {
    private GameMapController gameMapController;

    private MainView view;

    private Position[][] gameMap;
    private Player player1;
    private Player player2;
    private Player currentTurn;
 
    public MainController(){
        view = new MainView(1000,600, this);
        gameMapController = new GameMapController(view);

        this.gameMap = gameMapController.generateMap();
        this.player1 = new Player(1); this.player2 = new Player(2);
        this.currentTurn = player1;
    }

    public void buttonPressed (int[] pos) {
        Position gameSpace = gameMap[pos[0]][pos[1]];
        
        view.changeButton(pos, gameMap[pos[0]][pos[1]].toString());
        gameSpace.digEvent();
        boolean changeTurn = gameMapController.digEvent(gameSpace, currentTurn, getOpponentPlayer());

        checkIfGameDone();
        
        if(changeTurn) {
            if(currentTurn.equals(player1)) currentTurn = player2;
            else currentTurn = player1;
            view.changeCurrTurn(currentTurn.getPlayerNbr());
        }
    }

    private Player getOpponentPlayer () {
        if(currentTurn.equals(player1)) return player2;
        else return player1;
    }

    public void checkIfGameDone () {
        Player winningPlayer;
        String winningPlayerName;

        Player oppPlayer = getOpponentPlayer();
        
        if(currentTurn.getLives()==0) {
            view.eventMessage("Player "+currentTurn.getPlayerNbr()+" har slut på liv. Player "+oppPlayer.getPlayerNbr()+" vinner med "+oppPlayer.getScore()+" poäng!!");
            view.disableMap();
            winningPlayerName = view.popUpEnterName();
            winningPlayer = oppPlayer;
            addPlayerToHighScorelist(winningPlayerName, Integer.toString(winningPlayer.getScore()));
        }

        TreasureShape[] treasureShapes = gameMapController.getMapModel().getTreasureShapes();
        for (int i = 0; i < treasureShapes.length; i++) {
            if(!treasureShapes[i].checkIfComplete()) break;
            if(i == treasureShapes.length-1) {
                if(currentTurn.getScore() > oppPlayer.getScore()) {
                    winningPlayer = currentTurn;
                }
                else {
                    winningPlayer = oppPlayer;
                }

                view.eventMessage("Alla skatter hittade. Player "+winningPlayer.getPlayerNbr()+" vinner med "+winningPlayer.getScore()+" poäng!!");
                view.disableMap();
                winningPlayerName = view.popUpEnterName();
                addPlayerToHighScorelist(winningPlayerName, Integer.toString(winningPlayer.getScore()));
            }
        }
    }

    public void addPlayerToHighScorelist(String name, String points) {
        String filePath = "./Highscore.txt";
        ArrayList<String[]> linesAsArrays = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] stringLineArr = line.split(",");
                linesAsArrays.add(stringLineArr);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error reading the high score file.");
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
            for (int i = 0; i < 10; i++) {
                if(i >= linesAsArrays.size()){
                    break;
                }   
                String insertStringIntoHighscore = linesAsArrays.get(i)[0] + "," + linesAsArrays.get(i)[1] + "\n";
                fileWriter.write(insertStringIntoHighscore);  
            }
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Something went wrong while writing the high score file.");
        }
    }
}
