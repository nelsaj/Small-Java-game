package model;

import java.util.ArrayList;
import java.util.Random;

import model.Positions.Neutral;
import model.Positions.Position;
import model.Positions.Surprise;
import model.Positions.Trap;
import model.Positions.Treasure;
import model.Positions.TreasureShape;

public class GameMap {
    final int x = 10; final int y = 10;
    final Position[][] map = new Position[y][x];
    final int trapAmount = 15; final int surpriseAmount = 10;
    final int[][][] allowedShapes = TreasureShape.getAllowedShapes();
    final int treasureAmount = allowedShapes.length; // (9)

    TreasureShape[] treasureShapes;
    ArrayList <int[]> treasureCoords;

    public GameMap () {
        treasureCoords = new ArrayList<int[]>();
        makeRandomMap();
    }

    private void makeRandomMap () {
        //hitta random ställe att fylla ut
        for (int i = 0; i < treasureAmount; i++) {
            int randomX; int randomY;
            String randomDirection;

            // randomisera tills coord är okej
            do {
                randomX = new Random().nextInt(x);
                randomY = new Random().nextInt(y);

                String[] directions = {"vertical", "horizontal"};
                randomDirection = directions[new Random().nextInt(2)];
            }
            while (!treasureChecker(i, randomX, randomY, randomDirection));

            // sätter ut skatter
            for (int row = 0; row < allowedShapes[i].length; row++) {
                for (int col = 0; col < allowedShapes[i][row].length; col++) {
                    //riktning
                    int xpos; int ypos;
                    if(randomDirection == "vertical") {xpos = randomX+col; ypos = randomY+row;}
                    else {xpos = randomX+row; ypos = randomY+col;}

                    if(allowedShapes[i][row][col] == 1) {
                        map[xpos][ypos] = new Treasure(i); 
                        treasureCoords.add(new int[]{xpos, ypos});
                    }

                }
            }
        }

        //fyller tomma positioner med fällor och överraskningar
        fillPosition(new Trap(), trapAmount);
        fillPosition(new Surprise(), surpriseAmount);

        // gör resten neutrala
        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                if(map[row][col]==null) map[row][col] = new Neutral(); else continue;
            }
        }

        // skapa treasureshapes
        makeTreasureShapes(treasureAmount, allowedShapes);
    }
    private boolean treasureChecker (int i, int randomX, int randomY, String randomDirection) {
        for (int row = 0; row < allowedShapes[i].length; row++) {
            for (int col = 0; col < allowedShapes[i][row].length; col++) {
                // om 0 ska det vara tomt, behöver inte kollas
                if(allowedShapes[i][row][col] == 0) continue;

                //riktning
                int xpos; int ypos;
                if(randomDirection == "vertical") {xpos = randomX+col; ypos = randomY+row;}
                else {xpos = randomX+row; ypos = randomY+col;}

                // överskrid inte i
                // måste vara ledig
                if(xpos >= x || ypos >= y || map[xpos][ypos]!=null) {return false;}
            }
        }
        //om man kommit hit var det inga problem
        return true;
    }
    private void fillPosition (Position position, int amount) {
        // första loop bestämmer hur många
        for (int i = 0; i < amount; i++) {
            int randomX; int randomY;
            // randomisera tills ledig
            do {randomX = new Random().nextInt(x); randomY = new Random().nextInt(y);} 
            while(map[randomY][randomX]!=null);

            // lägg till
            map[randomY][randomX] = position;
        }
    }
    private void makeTreasureShapes (int treasureAmount, int[][][] shapes) {
        treasureShapes = new TreasureShape[treasureAmount];
        for (int i = 0; i < treasureAmount; i++) {
            int size = 0;

            for (int row = 0; row < shapes[i].length; row++) {
                for (int col = 0; col < shapes[i][row].length; col++) {
                    if(shapes[i][row][col] == 1) size++;
                }
            }

            treasureShapes[i] = new TreasureShape(i, size);
        }
    }

    public TreasureShape[] getTreasureShapes () {return treasureShapes;}
    public Position[][] getMap () {return map;}
    public int getYMax () {return y;}
    public int getXMax () {return x;}
    public ArrayList <int[]> getTreasureCoords() {return treasureCoords;}
}
