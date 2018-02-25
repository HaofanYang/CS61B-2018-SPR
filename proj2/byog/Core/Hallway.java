package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.HashSet;

public class Hallway {
    /** generate a Hallway with length l, position X, Y, and number of exits */
    protected int[] leftBottomPos;
    protected int[] rightTopPos;
    protected HashSet<Exit> exits = new HashSet<Exit>(); // set of Exits' coordinates  (defined within "representation")
    protected int[][] representation; // the 2D array that represent the hallway, where 1 represents wall and 0 represents floor.

    public Hallway(int xPos, int yPos) {
        leftBottomPos = new int[]{xPos, yPos};

    }

}
