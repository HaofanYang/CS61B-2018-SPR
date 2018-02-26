package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRandomWorld {


    @Test
    public void testAddWall() {
        int[] expect = new int[]{1,1,1,1,1,0,0,0,0};
        int[] actual = new int[]{0,0,0,0,0,0,0,0,0};
        RandomWorld.addWall(actual, 0, 5);
        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], actual[i]);
        }
    }
    public static void main(String[] args) {
        TETile[][] world = new TETile[100][100];
        RandomWorld rdw = new RandomWorld(world);
        int[] output = rdw.generateFirstRow();
        for (int i : output) {
            System.out.print(i + " ");
        }
    }
}
