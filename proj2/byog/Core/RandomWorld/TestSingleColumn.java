package byog.Core.RandomWorld;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class TestSingleColumn {
    @Test
    public void testSelectExits(){
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            input.add(i);
        }
        ArrayList output = SingleColumn.selectEdges(input, 3);
        assertEquals(6, output.size());
        System.out.println(input);
        System.out.println(output);
    }

    @Test
    public void testSelectOutOf(){
        HashSet a = SingleColumn.selectOutOf(3, 5);
        assertEquals(3, a.size());
        for (Object i : a) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void testSelectEdges(){
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 10; i > 0; i--) {
            input.add(i);
        }
        ArrayList<Integer> output = SingleColumn.selectEdges(input, 2);
        for (int i = 0; i < output.size() - 1; i++){
            assertTrue(output.get(i) < output.get(i + 1));
        }
        System.out.println(input);
        System.out.println(output);
    }

    public static void main(String[] args) {
        Random rd = new Random();
        SingleColumn col = new SingleColumn(30, rd);
        System.out.println(col);
        ArrayList edges = col.getEdges();
        System.out.println(edges);
    }
}
