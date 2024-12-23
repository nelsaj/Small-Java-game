package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.StartView;

public class MainView extends JFrame {
    
    private JPanel windowDrawSpace;
    private int windowWidth;
    private int windowHeight;

    public MainView(int windowWidth, int windowHeight){       
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
