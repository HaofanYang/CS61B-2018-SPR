package byog.Core.RandomWorld;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.Random;

public class TestRandomMase {
    public static void main(String[] args) {
        Random rd = new Random();
        TERenderer ter = new TERenderer();
        ter.initialize(100,70);
        TETile[][] world = new TETile[100][70];
        RandomMaze randomMaze = new RandomMaze(rd, world);
        ter.renderFrame(world);
    }
}
