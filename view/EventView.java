package view;

import java.awt.Event;

public class EventView implements MessageViewsInterface {
    private MainView mainView;
    public EventView(MainView mainView){
        this.mainView = mainView;
    }
    public void showMessage (String message) {};
}
