import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testEqualChars(){
        char a = 'a';
        char b = 'b';
        char c = 'c';
        assertFalse(offByOne.equalChars(a,a));
        assertTrue(offByOne.equalChars(a,b));
        assertTrue(offByOne.equalChars(b,c));
        assertFalse(offByOne.equalChars(a,c));
    }
    // Your tests go here.

}
