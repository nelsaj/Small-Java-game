package view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

public class PlayerView {
    private MainView mainView;
    private JLabel player1NameGUI;
    private JLabel player2NameGUI;
    private JLabel player1PointsGUI;
    private JLabel player2PointsGUI;

    public PlayerView(MainView mainView){
        this.mainView = mainView;
        createPlayerNamesGUI("Player1", "Player2");
        createPlayerPointsCounterGUI("0", "0");
    }

    public void createPlayerNamesGUI(String player1Name, String player2Name){
        player1NameGUI = new JLabel(player1Name);
        player2NameGUI = new JLabel(player2Name);
        player1NameGUI.setFont(new Font("serif", Font.BOLD, 18));
        player2NameGUI.setFont(new Font("serif", Font.PLAIN, 18));

        player1NameGUI.setBounds(50, 0, 100, 50);
        player2NameGUI.setBounds(300, 0, 100, 50);
        mainView.getWindowDrawSpace().add(player1NameGUI);
        mainView.getWindowDrawSpace().add(player2NameGUI);
    }
    public void createPlayerPointsCounterGUI(String player1Points, String player2Points){
        player1PointsGUI = new JLabel(player1Points);
        player2PointsGUI = new JLabel(player2Points);
        player1PointsGUI.setFont(new Font("serif", Font.PLAIN, 18));
        player2PointsGUI.setFont(new Font("serif", Font.PLAIN, 18));

        player1PointsGUI.setBounds(150, 0, 50, 50);
        player2PointsGUI.setBounds(400, 0, 50, 50);
        mainView.getWindowDrawSpace().add(player1PointsGUI);
        mainView.getWindowDrawSpace().add(player2PointsGUI);
    }
    
    public void changePointGUI(int whichPlayer, String newPoints){
        if(whichPlayer == 1){player1PointsGUI.setText(newPoints);}
        if(whichPlayer == 2){player2PointsGUI.setText(newPoints);}
    }
    public void displayCurrTurn(int currPlayer) {
        if(currPlayer == 1) {
            player1NameGUI.setFont(new Font("serif", Font.BOLD, 18));
            player2NameGUI.setFont(new Font("serif", Font.PLAIN, 18));
        } else if(currPlayer == 2) {
            player2NameGUI.setFont(new Font("serif", Font.BOLD, 18));
            player1NameGUI.setFont(new Font("serif", Font.PLAIN, 18));
        }
    }

}
