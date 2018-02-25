package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Hallway {
    /** generate a Hallway with length l, position X, Y, and number of exits */
    private int[] leftBottomPos;
    private int[] rightTopPos;
    public Exit[] wallCoordinates; // coordinates of "wall" elements (defined within "representation")
    public int[][] representation; // the 2D array that represent the hallway, where 1 represents wall and 0 represents floor.


}
