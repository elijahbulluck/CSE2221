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
public final class ProgramWithIO4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO4() {
    }

    private static Set<Integer> union(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> combo = new Set1L<>();
        Set<Integer> temp = new Set1L<>();
        temp.transferFrom(s1);
        while (temp.size() > 0) {
            Integer x = temp.removeAny();
            s1.add(x);
            if (!combo.contains(x)) {
                combo.add(x);
            }
        }
        temp.transferFrom(s2);
        while (temp.size() > 0) {
            Integer x = temp.removeAny();
            s2.add(x);
            if (!combo.contains(x)) {
                combo.add(x);
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
        Set<Integer> f = new Set1L<>();
        out.print(f.isSubset(s));
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
        Set<Integer> c = union(s, e);
        out.println("After: " + s);
        out.println("After: " + e);
        out.println(c);
        in.close();
        out.close();

    }

}
