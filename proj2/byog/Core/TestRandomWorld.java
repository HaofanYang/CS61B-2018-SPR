package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestRandomWorld {
    /**
    @Test
    public void testGetRange(){
        int[] input = new int[]{0,1,1,1,1,0,0,0,0,0,1,1,0,0};
        SingleColum output = new SingleColum(input);
        int[][] expect = new int[][]{{1, 4}, {10, 11}};
        int[][] actual = output.getEdgeRange();
        for (int i = 0; i < actual.length; i++){
            assertTrue(expect[i].equals(actual));
        }
    }
    */

    /**
    @Test
    public void testIniEdges(){
        int[] input = new int[]{0,1,1,1,1,0,0,0,0,0,1,1,0,0};
        SingleColum output = new SingleColum(input);
        ArrayList actual = output.iniEdges();
        int[] edges = new int[]{1,4,10,11};
        ArrayList expect = new ArrayList();
        for (int i : edges) {
            expect.add(i);
        }
        for (int i = 0; i < actual.size(); i++){
            assertEquals(actual.get(i),expect.get(i));
        }
    }
    */

    /**
    public static void main(String[] args) {
     TETile[][] world = new TETile[100][100];
     RandomWorld rdw = new RandomWorld(world);
     SingleColum output = rdw.generateFirstCol();
     System.out.print(output);
     }
     */

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(100,100);
        TETile[][] world = new TETile[100][100];
        RandomWorld rdw = new RandomWorld(world);
        world = rdw.getWORLD();
        ter.renderFrame(world);
    }
}
