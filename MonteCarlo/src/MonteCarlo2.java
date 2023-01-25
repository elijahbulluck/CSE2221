import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo2() {
    }

    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        if (((xCoord - 1) * (xCoord - 1)) + ((yCoord - 1) * (yCoord - 1))
                - 1 < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int n) {
        Random rnd = new Random1L();
        int circlePoints = 0;
        for (int z = 0; z < n; z++) {
            double x = rnd.nextDouble() * 2.0;
            double y = rnd.nextDouble() * 2.0;
            if (pointIsInCircle(x, y)) {
                circlePoints++;
            }
        }
        return circlePoints;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        int numPoints = numberOfPointsInCircle(n);
        double estimate = (4 * numPoints) / 100.0 * n;
        output.println("Estimate of percentage: " + estimate + "%");
        input.close();
        output.close();
    }

}