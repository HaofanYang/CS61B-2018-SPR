package byog.Core;
import org.junit.Test;
import static org.junit.Assert.*;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class TestRoom {

    /**
    @Test
    public void TestIsAtcorner(){
        Room room1 = new Room(10, 25, 12, 23);
        int[] corner1 = new int[]{0, 0};
        int[] corner2 = new int[]{0, 24};
        int[] corner3 = new int[]{9, 0};
        int[] corner4 = new int[]{9, 24};
        assertTrue(room1.atCorner(corner1));
        assertTrue(room1.atCorner(corner2));
        assertTrue(room1.atCorner(corner3));
        assertTrue(room1.atCorner(corner4));
    }*/


    public static void main(String[] args) {
        TERenderer tile = new TERenderer();
        tile.initialize(60, 60);
        TETile[][] world = new TETile[60][60];
        for (int i = 0; i < world.length; i++){
            for (int k = 0; k < world[0].length; k++) {
                world[i][k] = Tileset.NOTHING;
            }
        }
        Room room1 = new Room(10, 25, 12, 23);
        room1.addRoom(world);
        tile.renderFrame(world);
    }




}
