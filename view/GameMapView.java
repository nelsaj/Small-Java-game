package view;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainView;


public class GameMapView {
    private MainView mainView;
    public GameMapView(MainView mainView){
        this.mainView = mainView;
        createGameMapGUI(10,10);
    }
    
    public void createGameMapGUI( int rows, int colums){
        JPanel gameMapGUI = new JPanel(new GridLayout(10, 10));
        gameMapGUI.setBounds(50, 80, 450, 450);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                JButton gameMapPostionBtn = new JButton();
                String positionDataForBtn = i + "," + j;
                gameMapPostionBtn.setActionCommand(positionDataForBtn);
                gameMapPostionBtn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = Integer.parseInt(e.getActionCommand().split(",")[0]);
                        int colum = Integer.parseInt(e.getActionCommand().split(",")[1]);
                        System.out.println(row + ", " + colum);
                        gameMapPostionBtn.setBackground(new Color(123)); // test color
                        gameMapPostionBtn.setEnabled(false);
                    }
                    
                });
                gameMapGUI.add(gameMapPostionBtn);        
            }
        }
        mainView.getWindowDrawSpace().add(gameMapGUI);
    }
}
