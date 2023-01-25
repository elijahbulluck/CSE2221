import java.util.Arrays;

import components.sequence.Sequence;
import components.sequence.Sequence1L;
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
public final class ProgramWithIO5 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO5() {
    }

    private static int smallest(Sequence<Integer> s) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            Integer x = s.entry(i);
            if (x < min) {
                min = x;
            }
        }
        return min;
    }

    private static int sumOfInts(Sequence<Integer> s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer x = s.entry(i);
            sum = sum + x;
        }
        return sum;
    }

    private static boolean numIsPres(Sequence<Integer> s, int num) {
        boolean present = false;
        for (int i = 0; i < s.length(); i++) {
            Integer x = s.entry(i);
            if (x == num) {
                present = true;
            }
        }
        return present;
    }

    private static double averageOfInts(Sequence<Integer> s) {
        double sum = 0.0;
        for (int i = 0; i < s.length(); i++) {
            Integer x = s.entry(i);
            sum = sum + x;
        }
        sum = sum / s.length();
        return sum;
    }

    private static void alphStr(Sequence<String> s, String str) {
        Sequence<String> temp = s.newInstance();
        temp.transferFrom(s);
        temp.add(0, str);
        String[] arr = new String[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            arr[i] = temp.entry(i);
        }
        Arrays.sort(arr);
        int pos = 0;
        for (int i = 0; i < temp.length(); i++) {
            String test = temp.entry(i);
            int j = 0;
            while (j < arr.length) {
                if (arr[j].equals(test)) {
                    s.add(i, test);
                } else {
                    j++;
                }
            }
        }

    }

    private static void addZeroes(Sequence<Integer> s) {
        Sequence<Integer> temp = s.newInstance();
        temp.transferFrom(s);
        for (int i = 0; i < temp.length() + 1; i++) {
            s.add(i, 0);
        }
        int l = temp.length();
        int i = 0;
        while (l > 0 && i < temp.length()) {
            s.add(l, temp.entry(i));
            l--;
            i++;
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
        Sequence<Integer> e = new Sequence1L<>();
        e.add(0, 10);
        e.add(1, 10);
        addZeroes(e);
        out.println("Added zeroes: " + e);
        out.println("Before: " + e);
        int min = smallest(e);
        out.println("After: " + e);
        out.println("Min: " + min);
        int sum = sumOfInts(e);
        out.println("Sum: " + sum);
        boolean pres = numIsPres(e, 10);
        boolean pres2 = numIsPres(e, 31321);
        out.println("Should be true, false: " + pres + ", " + pres2);
        double avg = averageOfInts(e);
        out.println("Average: " + avg);
        Sequence<String> idk = new Sequence1L<>();
        idk.add(0, "b");
        idk.add(1, "a");
        idk.add(2, "t");
        out.println("String seq before: " + idk);
        String str = "foty";
        alphStr(idk, str);
        out.println("After: " + idk);
        in.close();
        out.close();

    }

}
