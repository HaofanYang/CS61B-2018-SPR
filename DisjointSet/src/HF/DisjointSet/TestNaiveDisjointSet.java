package HF.DisjointSet;

import static org.junit.Assert.*;

import HF.DisjointSet.DisjointSet;
import HF.DisjointSet.NaiveDisjointSet;
import org.junit.Test;

public class TestNaiveDisjointSet {
    @Test
    public void test1(){
        DisjointSet a = new NaiveDisjointSet(10);
        a.connect(4, 5);
        a.connect(4,6);
        assertTrue(a.isConnect(4,5));
        assertTrue(a.isConnect(5,6));
        assertFalse(a.isConnect(4,8));
    }
}
