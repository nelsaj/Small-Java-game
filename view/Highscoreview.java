
/**
 * This is klass represents the highscore GUI 
 * @author Elliot
*/
package view;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Highscoreview {
    private MainView mainView;

    /**
     * This is the constructor that inits the mainView attribut
     * @param mainView
     * @author Elliot
    */
    public Highscoreview(MainView mainView) {
        this.mainView = mainView;
    }
    
    /**
     * createHighScoreGUI creates a JList with the highscore lines of the Highscore.txt file that has stored winning players
     * @author Elliot
    */
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

   /**
     * Lägger till spelaren till highscorelistan.
     * 
     * @param name spelarens namn
     * @param points spelarens poäng
     * 
     * @author Elliot Collins
     */
    public void addPlayerToHighScorelist(String name, String points) {
        String filePath = "./Highscore.txt";
        ArrayList<String[]> linesAsArrays = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] stringLineArr = line.split(",");
                linesAsArrays.add(stringLineArr);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainView, "Error reading the high score file.");
            return;
        }

        String[] newWinningPlayer = {name, points};
        linesAsArrays.add(newWinningPlayer);

        linesAsArrays.sort((a, b) -> Integer.compare(
                Integer.parseInt(b[1].trim()),
                Integer.parseInt(a[1].trim())
        ));

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < 10; i++) {
                if (i >= linesAsArrays.size()) {
                    break;
                }
                String insertStringIntoHighscore = linesAsArrays.get(i)[0] + "," + linesAsArrays.get(i)[1] + "\n";
                fileWriter.write(insertStringIntoHighscore);
            }
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainView, "Something went wrong while writing the high score file.");
        }
    }
}
