package hw2;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

public class TestPercolation {
    @Test
    public void testOpen() {
        Percolation test1 = new Percolation(4);
        test1.open(1,1);
        assertTrue(test1.isOpen(1,1));
    }

    @Test
    public void testTwoToOne() {
        Percolation test1 = new Percolation(4);
        int test = test1.twoDToOneD(2, 3);
        int acual = 14;
        assertEquals(acual, test);
    }

    @Test
    public void testConnect() {
        Percolation space = new Percolation(10);
        space.open(4,5);
        space.open(6,5);
        space.open(5,4);
        space.open(5,6);
        WeightedQuickUnionUF UNION = space.getUNION();
        int origin = space.twoDToOneD(5, 5);
        int left = space.twoDToOneD(4,5);
        int right = space.twoDToOneD(6, 5);
        int top = space.twoDToOneD(5, 4);
        int bot = space.twoDToOneD(5, 6);
        assertFalse(UNION.connected(origin,right));
        assertFalse(UNION.connected(origin,left));
        assertFalse(UNION.connected(origin,top));
        assertFalse(UNION.connected(origin,bot));
        space.open(5,5);
        assertTrue(UNION.connected(origin,right));
        assertTrue(UNION.connected(origin,left));
        assertTrue(UNION.connected(origin,top));
        assertTrue(UNION.connected(origin,bot));
    }

    @Test
    public void testIsFull(){
        Percolation space = new Percolation(10);
        space.open(4,5);
        space.open(6,5);

        space.open(5,0);
        space.open(5,1);
        space.open(5,2);
        space.open(5,3);
        space.open(5,4);
        space.open(5,6);


        assertTrue(space.isFull(5,0));
        assertTrue(space.isFull(5,1));
        assertTrue(space.isFull(5,2));
        assertTrue(space.isFull(5,3));
        assertTrue(space.isFull(5,4));
        assertFalse(space.isFull(4,5));
        assertFalse(space.isFull(6,5));
        assertFalse(space.isFull(5,6));
        space.open(5,5);
        assertTrue(space.isFull(5,5));
        assertTrue(space.isFull(4,5));
        assertTrue(space.isFull(6,5));
        assertTrue(space.isFull(5,6));
    }




    public static void main(String[] args){
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(40, 3000, pf);
        ps.show();
    }
}
