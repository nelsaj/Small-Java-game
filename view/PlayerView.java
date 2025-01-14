
/**
 * PlayerView is the class representing the PlayerView and its diffrent
 * states such as the incrementaion and decrementation 
 * of lives.
 * @author Elliot
*/
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
    private JLabel player1LifesGUI;
    private JLabel player2LifesGUI;
    
    /**
     * This constructor inits the GUI part of the playerView
     * @param mainView
     * @author Elliot
    */
    public PlayerView(MainView mainView){
        this.mainView = mainView;
        createPlayerNamesGUI("Player1", "Player2");
        createPlayerPointsCounterGUI("0", "0");
        CreateLifesGUI("3", "3");
    }

    /**
     * createPlayerNamesGUI inits the GUI part of actual player names
     * @param player1Name
     * @param player2Name
     * @author Elliot
    */
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
    
    /**
     * createPlayerPointsCounterGUI inits the GUI part of the points system 
     * @param player1Points
     * @param player2Points
     * @author Elliot
    */
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
     
    /**
     * changePointGUI inits the GUI part of changing the gui points
     * @param whichPlayer
     * @param newPoints
     * @author Elliot
    */
    public void changePointGUI(int whichPlayer, String newPoints){
        if(whichPlayer == 1){player1PointsGUI.setText(newPoints);}
        if(whichPlayer == 2){player2PointsGUI.setText(newPoints);}
    }
    
     /**
     * displayCurrTurn inits the GUI part of showing whos turn it is in the game
     * @param currPlayer
     * @author Elliot
    */
    public void displayCurrTurn(int currPlayer) {
        if(currPlayer == 1) {
            player1NameGUI.setFont(new Font("serif", Font.BOLD, 18));
            player2NameGUI.setFont(new Font("serif", Font.PLAIN, 18));
        } else if(currPlayer == 2) {
            player2NameGUI.setFont(new Font("serif", Font.BOLD, 18));
            player1NameGUI.setFont(new Font("serif", Font.PLAIN, 18));
        }
    }
    
    /**
     * CreateLifesGUI inits the GUI part of creating the lifes GUI.
     * @param player1Lifes
     * @param player2Lifes
     * @author Elliot
    */
    public void CreateLifesGUI(String player1Lifes, String player2Lifes){
        player1LifesGUI = new JLabel(player1Lifes);
        player2LifesGUI = new JLabel(player2Lifes);

        player1LifesGUI.setFont(new Font("serif", Font.PLAIN, 18));
        player2LifesGUI.setFont(new Font("serif", Font.PLAIN, 18));

        player1LifesGUI.setBounds(150, 30, 50, 50);
        player2LifesGUI.setBounds(400, 30, 50, 50);

        mainView.getWindowDrawSpace().add(player1LifesGUI);
        mainView.getWindowDrawSpace().add(player2LifesGUI);

        JLabel livesTextLabel1 = new JLabel("Lifes ");
        JLabel livesTextLabel2 = new JLabel("Lifes ");

        livesTextLabel1.setFont(new Font("serif", Font.PLAIN, 18));
        livesTextLabel2.setFont(new Font("serif", Font.PLAIN, 18));

        livesTextLabel1.setBounds(50, 30, 50, 50);
        livesTextLabel2.setBounds(300, 30, 50, 50);
        
        mainView.getWindowDrawSpace().add(livesTextLabel1);
        mainView.getWindowDrawSpace().add(livesTextLabel2);
    }
    
    /**
     * changeLifesGUI inits the GUI part of changing the gui lifes
     * @param whichPlayer
     * @param newLifes
     * @author Elliot
    */
    public void changeLifesGUI(int whichPlayer, String newLifes){
        if(whichPlayer == 1){player1LifesGUI.setText(newLifes);}
        if(whichPlayer == 2){player2LifesGUI.setText(newLifes);}
    }
    
}
