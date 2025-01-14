/**
 * StartView is the class representing the start of the application and its diffrent
 * states such as the two choices a player can make in the GUI
 * @author Elliot
*/
package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import view.GameMapView;

public class StartView extends JFrame {
    
    private MainView mainView;

     /**
     * This constructor inits the attribute mainView
     * @param mainView
     * @author Elliot
    */
    public StartView(MainView mainView){
        this.mainView = mainView;
    }
    /**
     * createStartView inits the GUI part of the StartView
     * @author Elliot
    */
    public void createStartView(){
        JButton startBtn = new JButton("Start a new game");
        JButton highScoreBtn = new JButton("Highscore");

        int btnWidth = 150;
        int btnHeight = 50;
        
        startBtn.setBounds(((mainView.getWidth() / 2 ) - btnWidth + 80), ((mainView.getHeight() / 2 ) - btnHeight), btnWidth, btnHeight);
        highScoreBtn.setBounds(((mainView.getWidth() / 2 ) - btnWidth + 80), ((mainView.getHeight() / 2 ) - btnHeight + (btnHeight * 2)), btnWidth, btnHeight);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Start clicked");
                mainView.clearWindow();
                mainView.createGameView();
            }
        });

        highScoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("highscore clicked");
                mainView.clearWindow();
                mainView.createHighscoreView();
            }
        });


        mainView.getWindowDrawSpace().add(startBtn);
        mainView.getWindowDrawSpace().add(highScoreBtn);

        
        
    }

}
