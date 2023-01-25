import components.map.Map;
import components.map.Map1L;
import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class ProgramWithIO2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIO2() {
    }

    private static void changeValue(Map<String, Integer> m) {
        Map<String, Integer> temp = m.newInstance();
        temp.transferFrom(m);
        SimpleReader input = new SimpleReader1L();
        while (temp.size() > 0) {
            Map.Pair<String, Integer> k = temp.removeAny();
            System.out.println("Map: " + k.key() + ", " + k.value());
            System.out.print("Would you like to change the value? (y / n): ");
            String ans = input.nextLine();
            if (ans.equals("y")) {
                System.out.print("Enter the new value: ");
                String str = input.nextLine();
                int newVal = Integer.parseInt(str);
                m.add(k.key(), newVal);
            } else {
                m.add(k.key(), k.value());
            }
        }
        input.close();
    }

    private static void removeEquals(Map<String, String> m) {
        Map<String, String> temp = m.newInstance();
        temp.transferFrom(m);
        while (temp.size() > 0) {
            Map.Pair<String, String> k = temp.removeAny();
            if (!k.key().equals(k.value())) {
                m.add(k.key(), k.value());
            }
        }
    }

    private static void removeMaxMin(Map<String, NaturalNumber> m) {
        NaturalNumber min = new NaturalNumber2(Integer.MAX_VALUE);
        NaturalNumber max = new NaturalNumber2(0);
        Map<String, NaturalNumber> temp = m.newInstance();
        temp.transferFrom(m);
        while (temp.size() > 0) {
            Map.Pair<String, NaturalNumber> k = temp.removeAny();
            if (k.value().compareTo(min) < 0) {
                min = new NaturalNumber2(k.value());
            }
            if (k.value().compareTo(max) > 0) {
                max = new NaturalNumber2(k.value());
            }
            m.add(k.key(), k.value());
        }
        temp.transferFrom(m);
        while (temp.size() > 0) {
            Map.Pair<String, NaturalNumber> j = temp.removeAny();
            if (j.value().compareTo(min) != 0
                    && j.value().compareTo(max) != 0) {
                m.add(j.key(), j.value());
            }
        }
    }

    private static int leftMost(NaturalNumber n) {
        int digit = n.divideBy10();
        int left = digit;
        if (!n.isZero()) {
            left = leftMost(n);
        }
        n.multiplyBy10(digit);
        return left;
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
        NaturalNumber num = new NaturalNumber2(435);
        out.println(num);
        int left = leftMost(num);
        out.println(num);
        out.println(left);
        String abc = "abc";
        out.println(abc.substring(1));

        Map<String, Integer> s = new Map1L<>();
        s.add("one", 1);
        s.add("two", 2);
        s.add("three", 3);
        s.add("four", 4);
        out.println("Before: " + s);
        out.println("After: " + s);
        Map<String, String> str = new Map1L<>();
        str.add("one", "one");
        str.add("two", "ed");
        str.add("three", "ee");
        str.add("four", "four");
        out.println("Before: " + str);
        removeEquals(str);
        out.println("After: " + str);
        Map<String, NaturalNumber> nn = new Map1L<>();
        nn.add("small", new NaturalNumber2(23));
        nn.add("medium", new NaturalNumber2(480));
        nn.add("largest", new NaturalNumber2(334313));
        out.println("Before: " + nn);
        removeMaxMin(nn);
        out.println("After: " + nn);
        in.close();
        out.close();
    }

}
