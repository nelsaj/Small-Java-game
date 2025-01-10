package view;

import javax.swing.JFrame;
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
        new StartView(this).createStartView();
        
        this.controller = controller;       
    }

    public void createGameView(){
        gameMapView = new GameMapView(this);
        playerView = new PlayerView(this);
        eventView = new EventView(this);
        startView = new StartView(this);
    }

    public void eventMessage(String message) {eventView.showMessage(message);}

    public void buttonPressed (int[] pos) {
        controller.buttonPressed(pos);
    }
    //letter temporary
    public void changeButton (int[] pos, String letter) {
        gameMapView.changeButton(pos, letter);
    }
    public void changePoints(int whichPlayer, String newPoints){
        playerView.changePointGUI(whichPlayer, newPoints);
    }
    public void changeCurrTurn(int currPlayer) {
        playerView.displayCurrTurn(currPlayer);
    }

    public void clearWindow(){
        this.getWindowDrawSpace().removeAll();
        this.getWindowDrawSpace().revalidate();
        this.getWindowDrawSpace().repaint();
    }
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }
    public int getWindowWidth() {
        return windowWidth;
    }
    public int getWindowHeight() {
        return windowHeight;
    }
    public JPanel getWindowDrawSpace() {
        return windowDrawSpace;
    }

    
}
