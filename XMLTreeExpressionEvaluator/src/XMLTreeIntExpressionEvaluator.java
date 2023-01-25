import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Elijah Bulluck
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        //initialize the return value
        int finalVal = 0;
        //create an if statement that reports the value of the root <number>
        //this way, the value of the numbers needed
        //will be provided when recursing and also if the expression is
        //a single number
        if (exp.label().equals("number")) {
            finalVal = Integer.parseInt(exp.attributeValue("value"));
        }
        //if the root is labeled "plus" we must add its two children together
        if (exp.label().equals("plus")) {
            //it is given that each operator has 2 nested tags, so we can
            //recursively find the values that need to be added by going through
            //the nested tags
            //its two children will be added together at the end
            finalVal = evaluate(exp.child(0)) + evaluate(exp.child(1));
        }
        //if the root is labeled "minus" we must subtract its two children
        if (exp.label().equals("minus")) {
            //using the same logic as the "plus" if statement, subtract
            // <minus>'s two children
            finalVal = evaluate(exp.child(0)) - evaluate(exp.child(1));
        }
        //if the root is labeled "times" we must multiply its two children
        if (exp.label().equals("times")) {
            //recursively find the values that need to be multiplied by
            //using evaluate() for both children
            finalVal = evaluate(exp.child(0)) * evaluate(exp.child(1));
        }
        //if the root is labeled "divide", we must divide <divides> two children
        if (exp.label().equals("divide")) {
            //same logic as earlier to divide
            finalVal = evaluate(exp.child(0)) / evaluate(exp.child(1));
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
