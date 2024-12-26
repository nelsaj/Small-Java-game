package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import view.GameMapView;

public class StartView extends JFrame {
    
    private MainView mainView;
    
    public StartView(MainView mainView){
        this.mainView = mainView;
    }

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
            }
        });


        mainView.getWindowDrawSpace().add(startBtn);
        mainView.getWindowDrawSpace().add(highScoreBtn);

        
        
    }

}
