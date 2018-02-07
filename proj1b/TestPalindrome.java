import com.sun.tools.classfile.CharacterRangeTable_attribute;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String test1 = "wooooooooooooooooooooooooooow";
        String test2 = "array";
        String test3 = "radar";
        assertTrue("Method failed for " + test1
                , palindrome.isPalindrome(test1));
        assertFalse("Method failed for " + test2,
                palindrome.isPalindrome(test2));
        assertTrue("Method failed for " + test3,
                palindrome.isPalindrome(test3));
    }

    @Test
    public void testIsPalindromeOffByOne(){
        CharacterComparator cc = new OffByOne();
        String test1 = "wooooooooooooooooooooooooooow";
        String test2 = "array";
        String test3 = "radar";
        String test4 = "flake";
        String test5 = "oop";
        assertFalse(palindrome.isPalindrome(test1, cc));
        assertFalse(palindrome.isPalindrome(test2, cc));
        assertFalse(palindrome.isPalindrome(test3, cc));
        assertTrue(palindrome.isPalindrome(test4, cc));
        assertTrue(palindrome.isPalindrome(test5, cc));
    }

    @Test
    public void testIsPalindromeOffByN(){
        CharacterComparator offByThree = new OffByN(3);
        String test1 = "acsfd";
        String test2 = "ooooo";
        assertTrue(palindrome.isPalindrome(test1, offByThree));
        assertFalse(palindrome.isPalindrome(test2, offByThree));
    }
}
