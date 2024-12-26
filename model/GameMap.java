package model;

import java.util.Random;

import model.Positions.Neutral;
import model.Positions.Position;
import model.Positions.Surprise;
import model.Positions.Trap;
import model.Positions.Treasure;

public class GameMap {

    public Position[][] generateMap () {
        // Position[][] gameMap1 = 
        // {
        //     {new Neutral(), new Neutral(), new Neutral(), new Trap(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Surprise(), new Neutral()},
        //     {new Treasure(0, 2), new Treasure(0, 2), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral()},
        //     {new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Treasure(2, 4), new Neutral()},
        //     {new Surprise(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Treasure(2, 4), new Treasure(2, 4)},
        //     {new Neutral(), new Treasure(3, 4), new Treasure(3, 4), new Treasure(3, 4), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Trap(), new Treasure(2, 4)},
        //     {new Neutral(), new Neutral(), new Treasure(3, 4), new Trap(), new Neutral(), new Neutral(), new Surprise(), new Neutral(), new Neutral(), new Neutral()},
        //     {new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Treasure(4, 4), new Treasure(4, 4)},
        //     {new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Treasure(4, 4), new Treasure(4, 4)},
        //     {new Treasure(6, 2), new Neutral(), new Neutral(), new Neutral(), new Trap(), new Neutral(), new Neutral(), new Treasure(5, 1), new Neutral(), new Neutral()},
        //     {new Treasure(6, 2), new Neutral(), new Surprise(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral()}
        // };

        //TODO: Gör unika
        // lite okonventionell men tror det blir bäst så här
        Position[][][] gameMaps = 
        {
            {
                {new Neutral(), new Neutral(), new Neutral(), new Trap(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Surprise(), new Neutral()},
                {new Treasure(0, 2), new Treasure(0, 2), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral()},
                {new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Treasure(2, 4), new Neutral()},
                {new Surprise(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Treasure(2, 4), new Treasure(2, 4)},
                {new Neutral(), new Treasure(3, 4), new Treasure(3, 4), new Treasure(3, 4), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Trap(), new Treasure(2, 4)},
                {new Neutral(), new Neutral(), new Treasure(3, 4), new Trap(), new Neutral(), new Neutral(), new Surprise(), new Neutral(), new Neutral(), new Neutral()},
                {new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Treasure(4, 4), new Treasure(4, 4)},
                {new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Treasure(4, 4), new Treasure(4, 4)},
                {new Treasure(6, 2), new Neutral(), new Neutral(), new Neutral(), new Trap(), new Neutral(), new Neutral(), new Treasure(5, 1), new Neutral(), new Neutral()},
                {new Treasure(6, 2), new Neutral(), new Surprise(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral()}
            },
            {
                {new Neutral(), new Neutral(), new Neutral(), new Trap(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Surprise(), new Neutral()},
                {new Treasure(0, 2), new Treasure(0, 2), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral()},
                {new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Treasure(2, 4), new Neutral()},
                {new Surprise(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Treasure(2, 4), new Treasure(2, 4)},
                {new Neutral(), new Treasure(3, 4), new Treasure(3, 4), new Treasure(3, 4), new Neutral(), new Neutral(), new Treasure(1, 3), new Neutral(), new Trap(), new Treasure(2, 4)},
                {new Neutral(), new Neutral(), new Treasure(3, 4), new Trap(), new Neutral(), new Neutral(), new Surprise(), new Neutral(), new Neutral(), new Neutral()},
                {new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Treasure(4, 4), new Treasure(4, 4)},
                {new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Neutral(),new Neutral(), new Neutral(), new Treasure(4, 4), new Treasure(4, 4)},
                {new Treasure(6, 2), new Neutral(), new Neutral(), new Neutral(), new Trap(), new Neutral(), new Neutral(), new Treasure(5, 1), new Neutral(), new Neutral()},
                {new Treasure(6, 2), new Neutral(), new Surprise(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral(), new Neutral()}
            }
        };

        return gameMaps[new Random().nextInt(2)];
    };
}
