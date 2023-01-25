import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code NaturalNumber}.
 *
 * @author Elijah Bulluck
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        //initialize the return value
        NaturalNumber finalVal = new NaturalNumber2();
        //create an if statement that reports the value of the root <number>
        //this way, the value of the numbers needed
        //will be provided when recursing and also if the expression is
        //a single number
        if (exp.label().equals("number")) {
            finalVal.copyFrom(new NaturalNumber2(exp.attributeValue("value")));

        }
        //if the root is labeled "plus" we must add its two children together
        if (exp.label().equals("plus")) {
            //it is given that each operator has 2 nested tags, so we can
            //recursively find the values that need to be added by going through
            //the nested tags
            //its two children will be added together at the end
            finalVal.copyFrom(evaluate(exp.child(0)));
            finalVal.add(new NaturalNumber2(evaluate(exp.child(1))));
        }
        //if the root is labeled "minus" we must subtract its two children
        if (exp.label().equals("minus")) {
            finalVal.copyFrom(evaluate(exp.child(0)));
            NaturalNumber second = new NaturalNumber2(evaluate(exp.child(1)));
            //a NaturalNumber cannot be negative, so we must check if
            //the subtraction being done in this step result in a number greater
            //than or equal to zero
            if (finalVal.compareTo(second) < 0) {
                Reporter.fatalErrorToConsole(
                        "The subtraction of a NN cannot result in a negative number.");
            } else {
                //using the same logic as the "plus" if statement, subtract
                // <minus>'s two children
                finalVal.subtract(new NaturalNumber2(evaluate(exp.child(1))));
            }
        }
        //if the root is labeled "times" we must multiply its two children
        if (exp.label().equals("times")) {
            //recursively find the values that need to be multiplied by
            //using evaluate() for both children
            finalVal.copyFrom(evaluate(exp.child(0)));
            finalVal.multiply(new NaturalNumber2(evaluate(exp.child(1))));
        }
        //if the root is labeled "divide", we must divide <divides> two children
        if (exp.label().equals("divide")) {
            NaturalNumber divisor = new NaturalNumber2(evaluate(exp.child(1)));
            //a number cannot be divided by zero, so we must check if
            //the divisor is zero so an error can be reported
            if (divisor.isZero()) {
                Reporter.fatalErrorToConsole("Divide by zero error.");
            } else {
                //same logic as earlier to divide
                finalVal.copyFrom(evaluate(exp.child(0)));
                finalVal.divide(divisor);
            }
        }
        return finalVal;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
