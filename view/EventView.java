package view;

import java.awt.Event;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class EventView implements MessageViewsInterface {
    private MainView mainView;
    private JTextArea guiEventText;
    public EventView(MainView mainView){
        this.mainView = mainView;
        createEventViewGUI();
    }
    
    public void showMessage (String message) {guiEventText.setText(message);};
    
    public void createEventViewGUI(){
        
        guiEventText = new JTextArea("");
        guiEventText.setLineWrap(true);
        guiEventText.setOpaque(false);
        guiEventText.setEditable(false);
        guiEventText.setBounds(550, 200, 400, 50); 
        guiEventText.setFont(new Font("serif", Font.PLAIN, 18));
        mainView.getWindowDrawSpace().add(guiEventText);

    }
}
