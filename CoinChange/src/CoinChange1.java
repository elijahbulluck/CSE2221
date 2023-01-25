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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
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

        out.print("Enter an amount of change: ");
        int coins = in.nextInteger();
        int dollars = 0;
        int halfDollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        if (coins % 100 != 0) {
            dollars = coins / 100;
            coins = coins - coins + (coins % 100);
        }
        if (coins % 50 != 0) {
            halfDollars = coins / 50;
            coins = coins - coins + (coins % 50);
        }
        if (coins % 25 != 0) {
            dollars = coins / 25;
            coins = coins - coins + (coins % 25);
        }
        if (coins % 10 != 0) {
            dollars = coins / 10;
            coins = coins - coins + (coins % 10);
        }
        if (coins % 5 != 0) {
            dollars = coins / 5;
            coins = coins - coins + (coins % 5);
        }
        else {
            pennies = coins % 1;
        }
        }
        out.println(dollars + " dollars, " + halfDollars + " half dollars, "
                + quarters + " quarters, " + dimes + " dimes, " + nickels
                + " nickels," + pennies + " pennies");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
