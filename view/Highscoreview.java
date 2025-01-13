package view;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

public class Highscoreview {
    private MainView mainView;

    public Highscoreview(MainView mainView) {
        this.mainView = mainView;
    }

    public void createHighScoreGUI() {
        String filePath = "./Highscore.txt";
        List<String[]> linesAsArrays = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String playerName;
            String playerPoints;
            while ((line = br.readLine()) != null) {
                String[] stringLineArr = line.split(","); 
                playerName = stringLineArr[0];
                playerPoints = stringLineArr[1];

               System.out.println(playerName);
               System.out.println(playerPoints);
                
                linesAsArrays.add(stringLineArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String[] array : linesAsArrays) {
            String joinedLine = String.join(" ", array);
            listModel.addElement(joinedLine);
        }

        JList<String> highscoreList = new JList<>(listModel);

        JLabel highscoreLabel = new JLabel("---------- Highscore ----------- ");
        highscoreLabel.setBounds(300, 50, 500, 50);
        highscoreLabel.setFont(new Font(null, 0, 30));

        
        highscoreList.setBounds(250, 130, 500, 300);
        mainView.getWindowDrawSpace().add(highscoreLabel);
        mainView.getWindowDrawSpace().add(highscoreList);
    }

   
}
