import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 *
 * @author Elijah Bulluck
 *
 */
public class GlossaryTest {

    /**
     * Test of getTerms from Glossary.java.
     */
    @Test
    public void getTermsTest() {
        //if we output the terms, we can see if they were right or wrong
        SimpleReader fileReader = new SimpleReader1L("terms.txt");
        SimpleWriter out = new SimpleWriter1L();
        Map<String, String> m = new Map1L<String, String>();
        Glossary.getTerms(fileReader, m);

        //use example from class of safe map loop
        Map<String, String> temp = m.newInstance();
        temp.transferFrom(m);
        int count = 0;
        while (temp.size() > 0) {
            Map.Pair<String, String> p = temp.removeAny();
            out.println(p.key() + ", " + p.value());
            //add the key and value back to the map
            m.add(p.key(), p.value());
            count++;
        }
        out.close();
        fileReader.close();
    }

    /**
     * Test of createArr method from Glossary class.
     */
    @Test
    public void createArrTest1() {
        Map<String, String> m = new Map1L<String, String>();
        //create a map with out of alphabetical order key's so they can
        //be sorted by the createArr method
        m.add("third", "value doesn't matter");
        m.add("first", "ignore");
        m.add("second", "ignore");
        String[] arr = Glossary.createArr(m);
        //create expected array
        String[] arrExpected = { "first", "second", "third" };
        assertEquals(arrExpected[0], arr[0]);
        assertEquals(arrExpected[1], arr[1]);
        assertEquals(arrExpected[2], arr[2]);
    }

    /**
     * Second test of createArr method from Glossary class. (routine)
     */
    @Test
    public void createArrTest2() {
        Map<String, String> m = new Map1L<String, String>();
        //create a map that's in order to see if the order won't change
        //be sorted by the createArr method
        m.add("first", "value doesn't matter");
        m.add("second", "ignore");
        m.add("third", "ignore");
        String[] arr = Glossary.createArr(m);
        //create expected array
        String[] arrExpected = { "first", "second", "third" };
        assertEquals(arrExpected[0], arr[0]);
        assertEquals(arrExpected[1], arr[1]);
        assertEquals(arrExpected[2], arr[2]);
    }

    /**
     * Third test of createArr method from Glossary class. (routine)
     */
    @Test
    public void createArrTest3() {
        Map<String, String> m = new Map1L<String, String>();
        //create a map that's in order to see if the number will be first and
        //the rest will be sorted alphabetically by the createArr method
        m.add("alphabetical", "value doesn't matter");
        m.add("3", "ignore");
        m.add("order", "ignore");
        String[] arr = Glossary.createArr(m);
        //create expected array
        String[] arrExpected = { "3", "alphabetical", "order" };
        assertEquals(arrExpected[0], arr[0]);
        assertEquals(arrExpected[1], arr[1]);
        assertEquals(arrExpected[2], arr[2]);
    }

    /**
     * First test of nextWordOrSeparator method from Glossary class. (routine)
     */
    @Test
    public void nextWordOrSeparatorTest1() {
        final String separatorStr = " \t, ";
        //add the separator characters to the separatorSet that will be used
        //in nextWordOrSeparator method
        Set<Character> separatorSet = new Set1L<>();
        for (int i = 0; i < separatorStr.length(); i++) {
            if (!separatorSet.contains(separatorStr.charAt(i))) {
                separatorSet.add(separatorStr.charAt(i));
            }
        }
        String testStr = "   word";
        String wordOrSep = Glossary.nextWordOrSeparator(testStr, 0,
                separatorSet);
        //if the method starts at position 0, the correct string should be
        //the three spaces at the beginning and should not include the word
        String correctStr = "   ";
        assertEquals(correctStr, wordOrSep);
    }

    /**
     * Second test of nextWordOrSeparator method from Glossary class. (routine)
     */
    @Test
    public void nextWordOrSeparatorTest2() {
        final String separatorStr = " \t, ";
        //add the separator characters to the separatorSet that will be used
        //in nextWordOrSeparator method
        Set<Character> separatorSet = new Set1L<>();
        for (int i = 0; i < separatorStr.length(); i++) {
            if (!separatorSet.contains(separatorStr.charAt(i))) {
                separatorSet.add(separatorStr.charAt(i));
            }
        }
        String testStr = "word      ";
        String wordOrSep = Glossary.nextWordOrSeparator(testStr, 0,
                separatorSet);
        //if the method starts at position 0, the max word or separator would
        //be "word", and not pick up any of the spaces at the end
        String correctStr = "word";
        assertEquals(correctStr, wordOrSep);
    }

    /**
     * Third test of nextWordOrSeparator method from Glossary class. (routine)
     */
    @Test
    public void nextWordOrSeparatorTest3() {
        final String separatorStr = " \t, ";
        //add the separator characters to the separatorSet that will be used
        //in nextWordOrSeparator method
        Set<Character> separatorSet = new Set1L<>();
        for (int i = 0; i < separatorStr.length(); i++) {
            if (!separatorSet.contains(separatorStr.charAt(i))) {
                separatorSet.add(separatorStr.charAt(i));
            }
        }
        String testStr = ",,,, test three";
        String wordOrSep = Glossary.nextWordOrSeparator(testStr, 0,
                separatorSet);
        //the correct string will be the separators at the beginning
        String correctStr = ",,,, ";
        assertEquals(correctStr, wordOrSep);
    }

    /**
     * Fourth test of nextWordOrSeparator method from Glossary class. (routine)
     */
    @Test
    public void nextWordOrSeparatorTest4() {
        final String separatorStr = " \t, ";
        //add the separator characters to the separatorSet that will be used
        //in nextWordOrSeparator method
        Set<Character> separatorSet = new Set1L<>();
        for (int i = 0; i < separatorStr.length(); i++) {
            if (!separatorSet.contains(separatorStr.charAt(i))) {
                separatorSet.add(separatorStr.charAt(i));
            }
        }
        String testStr = "word one     ";
        String wordOrSep = Glossary.nextWordOrSeparator(testStr, 5,
                separatorSet);
        //if the method starts at position 5, it should only include the word
        //that starts at position 5 and leave the separators at the end out
        String correctStr = "one";
        assertEquals(correctStr, wordOrSep);
    }

}
