package byog.Core.RandomWorld;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.Random;

public class TestRandomMase {
    public static void main(String[] args) {
        Random rd = new Random();
        TERenderer ter = new TERenderer();
        ter.initialize(60,40);
        TETile[][] world = new TETile[60][40];
        RandomMaze randomMaze = new RandomMaze(rd, world);
        ter.renderFrame(world);
    }
}
