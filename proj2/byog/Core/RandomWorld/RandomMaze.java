package byog.Core.RandomWorld;

import byog.TileEngine.TETile;
import java.util.Random;

public class RandomMaze {
    private SingleColumn[] representation;
    private int HEIGHT;
    private int WIDTH;
    private Random RANDOM;
    private TETile[][] WORLD;

    public RandomMaze(Random rd, TETile[][] world) {
        WORLD = world;
        WIDTH = world.length;
        HEIGHT = world[0].length;
        representation = new SingleColumn[WIDTH];
        RANDOM = rd;
        iniFirstColumn();
        iniRestColumns();
        addToWorld();
    }

    private void addToWorld(){
        for(int i = 0; i < WIDTH; i++) {
            representation[i].addToWorld(WORLD[i]);
        }

    }

    private void iniFirstColumn(){
        representation[0] = new SingleColumn(HEIGHT, RANDOM);
    }

    private void iniRestColumns(){
        for (int i = 1; i < WIDTH; i++) {
            representation[i] = new SingleColumn(representation[i - 1]);
        }
    }


}
