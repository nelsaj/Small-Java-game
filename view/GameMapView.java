/**
 * This class is the GUI init for the game map which is a grid of JButtons
 * @author Elliot
*/
package view;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainView;


public class GameMapView {
    private MainView mainView;
    final private int rows = 10; final private int colums = 10;
    private JButton[][] buttonGrid = new JButton[rows][colums];

    /**
     * This is the constructor that init the whole GUI panel
     * @param mainView
     * @author Elliot
    */
    public GameMapView(MainView mainView){
        this.mainView = mainView;
        // createGameMapGUI(10,10);
        createGameMapGUI();
    }
  
    
    /**
     * changeButton changes the color based on the coordinates passed and the color representation in 
     * HEX code and becomes enabled too click on again.
     * @param pos
     * @param color
     * @author Elliot
    */
    public void changeButton (int[] pos, String color) {
        
        buttonGrid[pos[0]][pos[1]].setEnabled(false);
        buttonGrid[pos[0]][pos[1]].setBackground(Color.decode("#"+color));
    }
    
    /**
     * createGameMapGUI inits the whole gamemap grid layout and places the button in the acutall JPanel 
     * and stores the row and colum of each button to get passed on ny a clicked event.
     * @author Elliot
    */
    public void createGameMapGUI(){
        JPanel gameMapGUI = new JPanel(new GridLayout(rows, colums));
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
                        
                        mainView.buttonPressed(new int[]{row, colum});
                    }
                    
                });
                buttonGrid[i][j] = gameMapPostionBtn;
                gameMapGUI.add(gameMapPostionBtn);        
            }
        }
        mainView.getWindowDrawSpace().add(gameMapGUI);
    }
    
    /**
     * popUpEnterName is an input method that takes the winning player name and uses it to insert into highscore
     * @author Elliot
     * @return String
    */
    public String popUpEnterName(){
        JOptionPane popup = new JOptionPane();
        String name = popup.showInputDialog("You won, input your name for the highscorelist: ");
        return name;
    }
    
    /**
     * disablemap disables click events on the whole grid layout and each button
     * @author Elliot
    */
    public void disablemap(){
        for (int y = 0; y < buttonGrid.length; y++) {
            for (int x = 0; x < buttonGrid.length; x++) {
                buttonGrid[y][x].setEnabled(false);
            }
        }
    }

}
