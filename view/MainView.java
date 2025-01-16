
/**
 * MainView is the class representing the whole window and its diffrent states
 * @author Elliot
*/
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.MainController;
import view.StartView;

public class MainView extends JFrame {
    private MainController controller;
    
    private JPanel windowDrawSpace;
    private int windowWidth;
    private int windowHeight;

    private GameMapView gameMapView;
    private EventView eventView;
    private PlayerView playerView;
    private StartView startView;
    private Highscoreview highscoreview;

    /**
     * Construktor that inits the window and the drawing space in that window
     * @author Elliot
     * @param windowWidth
     * @param windowHeight
     * @param controller
     */
    public MainView(int windowWidth, int windowHeight, MainController controller){      
        super("Game");
        this.setWindowWidth(windowWidth);
        this.setWindowHeight(windowHeight);
        
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setSize(getWindowWidth(),getWindowHeight());
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        
        windowDrawSpace = new JPanel();
        windowDrawSpace.setSize(getWindowWidth(), getWindowHeight());
        windowDrawSpace.setVisible(true);
        windowDrawSpace.setLayout(null);
        
        this.add(windowDrawSpace);
        startView = new StartView(this);
        startView.createStartView();
        
        this.controller = controller;       
    }

    /**
     * createGameView inits the diffrent parts of the game view such as eventView and playerView.
     * @author Elliot
     */
    public void createGameView(){
        gameMapView = new GameMapView(this);
        playerView = new PlayerView(this);
        eventView = new EventView(this);
        startView = new StartView(this);

        JButton backButton = new JButton("Tillbaka till start meny");
        backButton.setBounds(800, 500, 100, 50);
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Start clicked");
                clearWindow();
                startView.createStartView();
            }
        });
        this.getWindowDrawSpace().add(backButton);
    }
    
     /**
     * createHighscoreView inits the whole highscore view
     * @author Elliot
     */
    public void createHighscoreView(){
        highscoreview = new Highscoreview(this);
        highscoreview.createHighScoreGUI();
    }
     
    /**
     * eventMessage is the method that changes the eventView Text that shows the player what has happend
     * @author Elliot
     * @param message
     */
    public void eventMessage(String message) {eventView.showMessage(message);}
    
    /**
     * buttonPressed is the method that inits the buttonPress functionality on the actuall gameMap
     * @author Elliot
     * @param pos
     */
    public void buttonPressed (int[] pos) {
        controller.buttonPressed(pos);
    }
       
    /**
     * changeButton is the method that inits the change of color when a button is press on the gameMap.
     * @author Elliot
     * @param pos
     * @param color
     */
    public void changeButton (int[] pos, String color) {
        gameMapView.changeButton(pos, color);
    }
    /**
     * changePoints is the method that inits the change of points when a player finds a treasure
     * and takes in which player as a int representation and a string of new points to be added in a 
     * integer format.
     * 
     * @author Elliot
     * @param whichPlayer
     * @param newPoints
     */
    public void changePoints(int whichPlayer, String newPoints){
        playerView.changePointGUI(whichPlayer, newPoints);
    }
   
     /**
     * buttonPressed is the method that inits the change of current player turn and takes in an
     * integer representing one player or the other.
     * 
     * @author Elliot
     * @param whichPlayer
     * @param newPoints
     */
    public void changeCurrTurn(int currPlayer) {
        playerView.displayCurrTurn(currPlayer);
    }
    /**
     * ClearWindow clears the window of all components
     * @author Elliot
    */
    public void clearWindow(){
        this.getWindowDrawSpace().removeAll();
        this.getWindowDrawSpace().revalidate();
        this.getWindowDrawSpace().repaint();
    }
    /**
     * setWindowWidth sets the windows width
     * @param windowWidth
     * @author Elliot
    */
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }
     /**
     * setWindowheight sets the windows height
     * @param windowheight
     * @author Elliot
    */
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }
    /**
     * getWindowWidth gets the windows width
     * @author Elliot
     * @return int
    */
    public int getWindowWidth() {
        return windowWidth;
    }
    /**
     * getWindowHeight gets the windows height
     * @author Elliot
     * @return int
    */
    public int getWindowHeight() {
        return windowHeight;
    }
    /**
     * getWindowDrawSpace gets the windows drawing context 
     * @author Elliot
     * @return JPanel
    */
    public JPanel getWindowDrawSpace() {
        return windowDrawSpace;
    }
    /**
     * changeLifesGUI changes the lifes on the player
     * @author Elliot
     * @param whichPlayer
     * @param lifeString
    */
    public void changeLifesGUI(int whichPlayer, String lifeString){
        playerView.changeLifesGUI(whichPlayer, lifeString);
    }
    /**
     * disableMap disables the whole gameMap
     * @author Elliot
     */
    public void disableMap(){
        gameMapView.disablemap();
    }
    /**
     * popUpEnterName inits the popUpName functionality taking player names inputs
     * @author Elliot
     * @return String
     */
    public String popUpEnterName(){
        return gameMapView.popUpEnterName();    
    }

    
}
