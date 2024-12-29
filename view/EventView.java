package view;

import java.awt.Event;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;


public class EventView implements MessageViewsInterface {
    private MainView mainView;
    public EventView(MainView mainView){
        this.mainView = mainView;
        createEventViewGUI();
    }
    
    public void showMessage (String message) {};

    public void createEventViewGUI(){
        JList eventListGUI = new JList<>();
        JLabel eventListGUILabel = new JLabel("Event list");
        eventListGUILabel.setBounds(550, 0, 100, 50); 
        eventListGUILabel.setFont(new Font("serif", Font.PLAIN, 18));
        
        eventListGUI.setBounds(550, 80, 300, 450);
        mainView.getWindowDrawSpace().add(eventListGUI);
        mainView.getWindowDrawSpace().add(eventListGUILabel);
    }
}
