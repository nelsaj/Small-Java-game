package view;

import javax.swing.JFrame;
import view.StartView;

public class MainView extends JFrame {
    
    
    public MainView(){       
        super("Game");
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700 );
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        new StartView(this).createStartView();
    }
}
