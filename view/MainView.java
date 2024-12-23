package view;

import javax.swing.JFrame;

public class MainView extends JFrame {
    
    public MainView(){       
        super("Game");
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700 );
        this.setVisible(true);
        
    }
}
