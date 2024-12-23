package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import view.MainView;

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

        mainView.add(startBtn);
        mainView.add(highScoreBtn);
        
    }

}
