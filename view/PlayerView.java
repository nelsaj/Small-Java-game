package view;

import java.awt.Font;

import javax.swing.JLabel;

public class PlayerView {
    private MainView mainView;
    public PlayerView(MainView mainView){
        this.mainView = mainView;
        createPlayerNamesGUI("Player1", "Player2");
        createPlayerPointsCounterGUI("0", "0");
    }

    public void createPlayerNamesGUI(String player1Name, String player2Name){
        JLabel player1NameGUI = new JLabel(player1Name);
        JLabel player2NameGUI = new JLabel(player2Name);
        player1NameGUI.setFont(new Font("serif", Font.PLAIN, 18));
        player2NameGUI.setFont(new Font("serif", Font.PLAIN, 18));

        player1NameGUI.setBounds(50, 0, 100, 50);
        player2NameGUI.setBounds(300, 0, 100, 50);
        mainView.getWindowDrawSpace().add(player1NameGUI);
        mainView.getWindowDrawSpace().add(player2NameGUI);
    }
    public void createPlayerPointsCounterGUI(String player1Points, String player2Points){
        JLabel player1PointsGUI= new JLabel(player1Points);
        JLabel player2PointsGUI = new JLabel(player2Points);
        player1PointsGUI.setFont(new Font("serif", Font.PLAIN, 18));
        player2PointsGUI.setFont(new Font("serif", Font.PLAIN, 18));

        player1PointsGUI.setBounds(150, 0, 50, 50);
        player2PointsGUI.setBounds(400, 0, 50, 50);
        mainView.getWindowDrawSpace().add(player1PointsGUI);
        mainView.getWindowDrawSpace().add(player2PointsGUI);
    }
    
    public void addPointGUI(int whichPlayer){
        if(whichPlayer == 1){
            
        }
        if(whichPlayer == 2){
            
        }
    }

}
