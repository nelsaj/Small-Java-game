/**
 * This is a class represents the event view which shows the current event that has occured from a button press
 * @author Elliot  
*/
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

public class EventView {
    private MainView mainView;
    private JTextArea guiEventText;
    /**
     * Constructor that init the EventView GUI
     * @param mainView
     * @author Elliot
    */
    public EventView(MainView mainView){
        this.mainView = mainView;
        createEventViewGUI();
    }
    /**
     * showMessage sends the event message to the JTextArea that displays it
     * @param message
     * @author Elliot
    */
    public void showMessage (String message) {guiEventText.setText(message);};
    
    
    /**
     * createEventViewGUI creates the Swing layout and GUI
     * 
     * @author Elliot
    */
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
