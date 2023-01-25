import java.util.Arrays;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
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
public final class RecursionPrac {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RecursionPrac() {
    }

    private static int digitSum(int n) {
        int sum = 0;
        if (n == 0) {
            return sum;
        } else {
            int digit = n % 10;
            sum = sum + digit;
            n = n / 10;
            sum = sum + digitSum(n);
        }
        return sum;
    }

    private static void foo(String s, NaturalNumber n) {
        s = "Columbus, " + s;
        n = new NaturalNumber1L(43210);
    }

    private static void bar(int[] a, int i) {
        a[i]++;
        i++;
    }

    private static void foo(NaturalNumber n) {
        n.decrement();
        n = new NaturalNumber1L();
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
        NaturalNumber num = new NaturalNumber1L(10);
        foo(num);
        out.println(num);
        double threeQuarters = 3 * 4;
        out.print(threeQuarters);
        String str = "Ohio";
        int[] array = { 1, 2, 3, 4 };
        int index = 1;
        bar(array, index);
        out.println(Arrays.toString(array));
        in.close();
        out.close();

    }

}
