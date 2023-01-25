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
public final class ProgramWithIO6 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO6() {
    }

    private static void removeNums(Set<Integer> s1, int req) {
        Set<Integer> temp = s1.newInstance();
        temp.transferFrom(s1);
        while (temp.size() > 0) {
            Integer x = temp.removeAny();
            if (x < req) {
                s1.add(x);
            }
        }
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
        removeNums(s, 32);
        out.println("After: " + s);
        in.close();
        out.close();

    }

}
