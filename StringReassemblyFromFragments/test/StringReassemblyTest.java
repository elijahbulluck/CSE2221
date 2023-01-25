import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

/**
 * A JUnit test file for testing StringReassembly.
 *
 * @author Elijah Bulluck
 *
 */
public class StringReassemblyTest {

    /**
     * Routine case of combination, overlap at the end of the first case.
     */
    @Test
    public void combinationTest1() {
        String one = "ABCDEFG";
        String two = "FGHIJK";
        String correct = "ABCDEFGHIJK";
        int overlap = 2;
        String test = StringReassembly.combination(one, two, overlap);
        assertEquals(correct, test);
    }

    /**
     * Boundary case of combination, smallest possible.
     */
    @Test
    public void combinationTest2() {
        String one = "a";
        String two = "a";
        String correct = "a";
        int overlap = 1;
        String test = StringReassembly.combination(one, two, overlap);
        assertEquals(correct, test);
    }

    /**
     * Routine case of combination.
     */
    @Test
    public void combinationTest3() {
        String one = "Favorite";
        String two = "eSong";
        String correct = "FavoriteSong";
        int overlap = 1;
        String test = StringReassembly.combination(one, two, overlap);
        assertEquals(correct, test);
    }

    /**
     * Routine case of combination, same string.
     */
    @Test
    public void combinationTest4() {
        String one = "same";
        String two = "same";
        String correct = "same";
        int overlap = 4;
        String test = StringReassembly.combination(one, two, overlap);
        assertEquals(correct, test);
    }

    /**
     * Routine case of combination, substring added (should not be added).
     */
    @Test
    public void addToSetAvoidingSubstringsTest1() {
        Set<String> test = new Set1L<>();
        test.add("basket");
        test.add("ball");
        test.add("horse");
        String s = "all";
        Set<String> correct = new Set1L<>();
        correct.add("basket");
        correct.add("ball");
        correct.add("horse");
        StringReassembly.addToSetAvoidingSubstrings(test, s);
        assertEquals(correct, test);
    }

    /**
     * Routine case of combination, s should be added.
     */
    @Test
    public void addToSetAvoidingSubstringsTest2() {
        Set<String> test = new Set1L<>();
        test.add("basket");
        test.add("ball");
        test.add("horse");
        String s = "jay";
        Set<String> correct = new Set1L<>();
        correct.add("basket");
        correct.add("ball");
        correct.add("horse");
        correct.add("jay");
        StringReassembly.addToSetAvoidingSubstrings(test, s);
        assertEquals(correct, test);
    }

    /**
     * Routine case of combination, s should be added and "basket" and "ball"
     * should be removed.
     */
    @Test
    public void addToSetAvoidingSubstringsTest3() {
        Set<String> test = new Set1L<>();
        test.add("basket");
        test.add("ball");
        test.add("corn");
        String s = "basketball";
        Set<String> correct = new Set1L<>();
        correct.add("basketball");
        correct.add("corn");
        StringReassembly.addToSetAvoidingSubstrings(test, s);
        assertEquals(correct, test);
    }

}
