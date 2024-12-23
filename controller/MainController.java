package controller;

import view.MainView;

public class MainController {
    private MainView view;
    
    public MainController(){
        view = new MainView(1000,500);
    }
}
