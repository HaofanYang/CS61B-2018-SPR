package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

public class HexWorldTest {
    @Test
    public void generateHexagonTest() {
        int[][] expect = new int[][]{{0,0,1,1,0,0},{0,1,1,1,1,0},{1,1,1,1,1,1},
                {1,1,1,1,1,1},{1,1,1,1,1,1},{0,1,1,1,1,0},{0,0,1,1,0,0}};
        int[][] actual = HexWorld.generateHexagon(3);
        assertArrayEquals(expect, actual);
    }

    @Test
    public void getNumOfOnesTest(){
        int expect1 = 6;
        int actual1 = HexWorld.getNumOfOnes(2, 4);
        assertEquals(expect1, actual1);
        int expect2 = 4;
        int actual2 = HexWorld.getNumOfOnes(8, 4);
        assertEquals(expect2, actual2);
        int expect3 = 8;
        int actual3 = HexWorld.getNumOfOnes(6, 4);
        assertEquals(expect3, actual3);

    }
}
