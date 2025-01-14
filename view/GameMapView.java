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

    public GameMapView(MainView mainView){
        this.mainView = mainView;
        // createGameMapGUI(10,10);
        createGameMapGUI();
    }
    
    //Sparar denna ifallatt

    // public void createGameMapGUI( int rows, int colums){
    //     JPanel gameMapGUI = new JPanel(new GridLayout(10, 10));
    //     gameMapGUI.setBounds(50, 80, 450, 450);
    //     for (int i = 0; i < rows; i++) {
    //         for (int j = 0; j < colums; j++) {
    //             JButton gameMapPostionBtn = new JButton();
    //             String positionDataForBtn = i + "," + j;
    //             gameMapPostionBtn.setActionCommand(positionDataForBtn);
    //             gameMapPostionBtn.addActionListener(new ActionListener() {

    //                 @Override
    //                 public void actionPerformed(ActionEvent e) {
    //                     int row = Integer.parseInt(e.getActionCommand().split(",")[0]);
    //                     int colum = Integer.parseInt(e.getActionCommand().split(",")[1]);
    //                     System.out.println(row + ", " + colum);
    //                     // funkar inte på mac :( vvv
    //                     gameMapPostionBtn.setBackground(new Color(123)); // test color
    //                     gameMapPostionBtn.setEnabled(false);

    //                     //testing
    //                     mainView.buttonPressed(new int[]{row, colum});
    //                 }
                    
    //             });
    //             gameMapGUI.add(gameMapPostionBtn);        
    //         }
    //     }
    //     mainView.getWindowDrawSpace().add(gameMapGUI);
    // }

    public void changeButton (int[] pos, String color) {
        // funkar inte på mac :( vvv
        //buttonGrid[pos[0]][pos[1]].setBackground(new Color(123)); // test color
        buttonGrid[pos[0]][pos[1]].setEnabled(false);

        //buttonGrid[pos[0]][pos[1]].setText(letter);
        buttonGrid[pos[0]][pos[1]].setBackground(Color.decode("#"+color));
    }
    
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
                        System.out.println(row + ", " + colum);
                        
                        //testing
                        mainView.buttonPressed(new int[]{row, colum});
                    }
                    
                });
                buttonGrid[i][j] = gameMapPostionBtn;
                gameMapGUI.add(gameMapPostionBtn);        
            }
        }
        mainView.getWindowDrawSpace().add(gameMapGUI);
    }
    public String popUpEnterName(){
        JOptionPane popup = new JOptionPane();
        String name = popup.showInputDialog("You won, input your name for the highscorelist: ");
        return name;
    }
    public void disablemap(){
        for (int y = 0; y < buttonGrid.length; y++) {
            for (int x = 0; x < buttonGrid.length; x++) {
                buttonGrid[y][x].setEnabled(false);
            }
        }
    }
}
