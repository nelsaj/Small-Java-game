package model;

import java.util.ArrayList;
import java.util.Random;

import model.Positions.Neutral;
import model.Positions.Position;
import model.Positions.Surprise;
import model.Positions.Trap;
import model.Positions.Treasure;
import model.Positions.TreasureShape;

/**
 * Genererar ett randomiserat spelplan som består av Position[][].
 * 
 * @author Nelly Sajland
 */
public class GameMap {
    final int x = 10; final int y = 10;
    final Position[][] map = new Position[y][x];
    final int trapAmount = 15; final int surpriseAmount = 10;
    final int[][][] allowedShapes = TreasureShape.getAllowedShapes();
    final int treasureAmount = allowedShapes.length; // (9)

    private TreasureShape[] treasureShapes;
    private ArrayList<int[]> treasureCoords;

    /**
     * Skapar en GameMap genom att anropa makeRandomMap.
     * 
     * @author Nelly Sajland
     */
    public GameMap() {
        treasureCoords = new ArrayList<int[]>();
        makeRandomMap();
    }

    /**
     * Fyller slumpmässigt spelplanen med skatter, fällor, överraskningar och neutrala positioner.
     * 
     * @author Nelly Sajland
     */
    private void makeRandomMap() {
        // Fill the map with treasures
        for (int i = 0; i < treasureAmount; i++) {
            int randomX; int randomY;
            String randomDirection;

            do {
                randomX = new Random().nextInt(x);
                randomY = new Random().nextInt(y);

                String[] directions = {"vertical", "horizontal"};
                randomDirection = directions[new Random().nextInt(2)];
            } while (!treasureChecker(i, randomX, randomY, randomDirection));

            for (int row = 0; row < allowedShapes[i].length; row++) {
                for (int col = 0; col < allowedShapes[i][row].length; col++) {
                    int xpos; int ypos;
                    if (randomDirection.equals("vertical")) {
                        xpos = randomX + col;
                        ypos = randomY + row;
                    } else {
                        xpos = randomX + row;
                        ypos = randomY + col;
                    }

                    if (allowedShapes[i][row][col] == 1) {
                        map[xpos][ypos] = new Treasure(i);
                        treasureCoords.add(new int[] {xpos, ypos});
                    }
                }
            }
        }

        fillPosition(new Trap(), trapAmount);
        fillPosition(new Surprise(), surpriseAmount);

        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                if (map[row][col] == null) map[row][col] = new Neutral();
            }
        }

        makeTreasureShapes(treasureAmount, allowedShapes);
    }

    /**
     * Kontrollerar om en skatt kan placeras på en position
     *
     * @param i index för TreasureShape
     * @param randomX slumpmässiga x-kordinaten 
     * @param randomY slumpmässiga y-kordinaten 
     * @param randomDirection vertikal eller horisontell
     * @return true om skatten kan placeras, false om den inte går att placera på positionen
     * 
     * @author Nelly Sajland
     */
    private boolean treasureChecker(int i, int randomX, int randomY, String randomDirection) {
        for (int row = 0; row < allowedShapes[i].length; row++) {
            for (int col = 0; col < allowedShapes[i][row].length; col++) {
                if (allowedShapes[i][row][col] == 0) continue;

                int xpos, ypos;
                if (randomDirection.equals("vertical")) {
                    xpos = randomX + col;
                    ypos = randomY + row;
                } else {
                    xpos = randomX + row;
                    ypos = randomY + col;
                }

                if (xpos >= x || ypos >= y || map[xpos][ypos] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Fyller slumpmässigt spelplanen med en angiven mängd positionstyp på lediga platser.
     *
     * @param position positionstypen
     * @param amount mängden av positionstypen
     * 
     * @author Nelly Sajland
     */
    private void fillPosition(Position position, int amount) {
        for (int i = 0; i < amount; i++) {
            int randomX, randomY;
            do {
                randomX = new Random().nextInt(x);
                randomY = new Random().nextInt(y);
            } while (map[randomY][randomX] != null);

            map[randomY][randomX] = position;
        }
    }

    /**
     * Initierar TreasureShapes baserat på skatter på spelplanen.
     *
     * @param treasureAmount mängden skatter
     * @param shapes använda skattformerna
     * 
     * @author Nelly Sajland
     */
    private void makeTreasureShapes(int treasureAmount, int[][][] shapes) {
        treasureShapes = new TreasureShape[treasureAmount];
        for (int i = 0; i < treasureAmount; i++) {
            int size = 0;

            for (int row = 0; row < shapes[i].length; row++) {
                for (int col = 0; col < shapes[i][row].length; col++) {
                    if (shapes[i][row][col] == 1) size++;
                }
            }

            treasureShapes[i] = new TreasureShape(i, size);
        }
    }

    /**
     * Hämtar en array med skattformer.
     *
     * @return en array av TreasureShape-objekt
     * 
     * @author Nelly Sajland
     */
    public TreasureShape[] getTreasureShapes() {
        return treasureShapes;
    }

    /**
     * Hämtar spelplanen.
     *
     * @return en 2D-array som representerar kartan
     * 
     * @author Nelly Sajland
     */
    public Position[][] getMap() {
        return map;
    }

    /**
     * Hämtar den maximala y för spelplanen.
     *
     * @return storleken på y
     * 
     * @author Nelly Sajland
     */
    public int getYMax() {
        return y;
    }

    /**
     * Hämtar den maximala x för spelplanen.
     *
     * @return storleken på x
     * 
     * @author Nelly Sajland
     */
    public int getXMax() {
        return x;
    }

    /**
     * Hämtar koordinaterna för skatterna på kartan.
     *
     * @return en ArrayList med skattkoordinater
     * 
     * @author Nelly Sajland
     */
    public ArrayList<int[]> getTreasureCoords() {
        return treasureCoords;
    }

}
