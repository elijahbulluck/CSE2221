import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class IntegerDivision {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private IntegerDivision() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        final double microsPerDay = 24.0 * 60.0 * 60.0 * 1000.0 * 1000.0;
        final double millisPerDay = 24.0 * 60.0 * 60.0 * 1000.0;
        out.println(microsPerDay / millisPerDay);
        out.close();
    }

}
