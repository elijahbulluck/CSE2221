import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Elijah Bulluck
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";
        //this is similar to the IntegerRoot lab, just with NaturalNumbers
        //create the variables for too low and too high
        NaturalNumber low = new NaturalNumber2(0);
        NaturalNumber tooHigh = new NaturalNumber2(n);
        //add one to tooHigh to replicate n + 1 in IntegerRoot lab
        tooHigh.increment();
        //cannot directly compare the difference of tooHigh - low in the
        //while loop, so do it outside of the loop
        NaturalNumber highMinusLow = new NaturalNumber2(tooHigh);
        highMinusLow.subtract(low);
        //create midPoint variable to be used inside loop
        NaturalNumber midPoint = new NaturalNumber2();
        //use compareTo method to compare the difference of high and low to 1
        //like in the IntegerRoot lab
        while (highMinusLow.compareTo(new NaturalNumber2(1)) > 0) {
            //find mid point by adding low and high together and dividing by 2
            midPoint.copyFrom(tooHigh);
            midPoint.add(low);
            midPoint.divide(new NaturalNumber2(2));
            //create guess and set it equal to midPoint
            NaturalNumber guess = new NaturalNumber2(midPoint);
            //raise guess to the rth power so it can be used in compareTo method
            guess.power(r);
            //if the guess is higher than n, that means that the root is lower
            //than the midPoint, so set tooHigh equal to the midPoint
            if (guess.compareTo(n) > 0) {
                tooHigh.copyFrom(midPoint);
                // if the guess is lower than n, that means that the root is
                //higher than the midPoint, so set the low point equal to the midPoint
                //this will also set the correct root equal to low when it is found
            } else {
                low.copyFrom(midPoint);
            }
            //update the while loop by subtracting the new high and
            //low values
            highMinusLow.copyFrom(tooHigh);
            highMinusLow.subtract(low);
        }
        //when the root is found, it will be copied to n
        n.copyFrom(low);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
