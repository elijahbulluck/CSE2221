import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIO3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO3() {
    }

    private static Set<Integer> intersection(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> combo = new Set1L<>();
        for (Integer e : s1) {
            if (s2.contains(e)) {
                combo.add(e);
            }
        }
        return combo;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Set<Integer> s = new Set1L<>();
        s.add(33);
        s.add(223);
        s.add(2);
        s.add(1);
        s.add(8);
        out.println("Before: " + s);
        Set<Integer> e = new Set1L<>();
        e.add(232);
        e.add(21);
        e.add(223);
        e.add(8);
        out.println("Before: " + e);
        Set<Integer> c = intersection(s, e);
        out.println("After: " + s);
        out.println("After: " + e);
        out.println(c);
        in.close();
        out.close();

    }

}
