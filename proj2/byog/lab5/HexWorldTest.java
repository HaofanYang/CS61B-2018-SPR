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
        int expect1 = 4;
        int actual1 = HexWorld.getNumOfOnes(1, 2);
        assertEquals(expect1, actual1);
        int expect2 = 4;
        int actual2 = HexWorld.getNumOfOnes(7, 4);
        assertEquals(expect2, actual2);
        int expect3 = 7;
        int actual3 = HexWorld.getNumOfOnes(3, 3);
        assertEquals(expect3, actual3);

    }
}
