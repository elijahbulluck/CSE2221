import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Elijah Bulluck
 *
 */
public final class HelloJack {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HelloJack() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader keyboardIn = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        String name = keyboardIn.nextLine();
        out.println("Hello, " + name);
        keyboardIn.close();
        out.close();
    }

}
