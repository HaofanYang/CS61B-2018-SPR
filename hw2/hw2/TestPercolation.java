package hw2;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

public class TestPercolation {
    @Test
    public void testOpen() {
        Percolation test1 = new Percolation(4);
        assertFalse(test1.isOpen(1, 1));
        test1.open(1,1);
        assertTrue(test1.isOpen(1,1));
    }

    @Test
    public void testTwoToOne() {
        Percolation test1 = new Percolation(4);
        int test = test1.twoDToOneD(2, 3);
        int acual = 11;
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
        space.open(5,4);
        space.open(5,6);
        space.open(6, 5);

        space.open(0,5);
        space.open(1,5);
        space.open(2,5);
        space.open(3,5);
        space.open(4,5);


        assertTrue(space.isFull(0,5));
        assertTrue(space.isFull(1,5));
        assertTrue(space.isFull(2,5));
        assertTrue(space.isFull(3,5));
        assertTrue(space.isFull(4,5));
        assertFalse(space.isFull(5,4));
        assertFalse(space.isFull(5,6));
        assertFalse(space.isFull(5,5));
        space.open(5,5);
        assertTrue(space.isFull(5,4));
        assertTrue(space.isFull(5,6));
        assertTrue(space.isFull(5,5));
        assertTrue(space.isFull(6,5));
    }

    @Test
    public void multOpens(){
        Percolation space = new Percolation(10);
        assertEquals(0, space.numberOfOpenSites());
        space.open(1, 1);
        assertEquals(1,space.numberOfOpenSites());
        space.open(1, 2);
        assertEquals(2, space.numberOfOpenSites());
        space.open(1,2);
        assertEquals(2, space.numberOfOpenSites());
    }




    public static void main(String[] args){
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(200, 300, pf);
        ps.show();
    }
}
