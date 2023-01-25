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
public final class ProgramSkeleton {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramSkeleton() {
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
        int[] a = { 4, 3, 2, 1 };
        boolean order = false;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < a[i + 1]) {
                order = true;
            }
        }
        out.print(order);
        in.close();
        out.close();
    }

}
