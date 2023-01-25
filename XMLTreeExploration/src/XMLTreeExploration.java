import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Elijah Bulluck
 *
 */
public final class XMLTreeExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeExploration() {
    }

    /**
     * Output information about the middle child of the given {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose middle child is to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires <pre>
     * [the label of the root of xt is a tag]  and
     * [xt has at least one child]  and  out.is_open
     * </pre>
     * @ensures <pre>
     * out.content = #out.content * [the label of the middle child
     *  of xt, whether the root of the middle child is a tag or text,
     *  and if it is a tag, the number of children of the middle child]
     * </pre>
     */
    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {
        if (xt.numberOfChildren() == 0) {
            out.print("Invalid string.");
        } else {
            int middle = xt.numberOfChildren() / 2;
            String root = xt.label();
            if (xt.isTag()) {
                out.print(root + " is a tag.");
                int child = xt.child(middle).numberOfChildren();
                out.print(child);
            } else {
                out.print(root + " is text.");
                String midChild = xt.child(middle).toString();
            }
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
        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/extras/instructions/xmltree-model/columbus-weather.xml");
        boolean isTag = xml.isTag();
        int c = xml.numberOfChildren();
        out.print(c);
        String tag = xml.label();
        // out.println(isTag);
        // out.println(tag);
        XMLTree astronomy = xml.child(0).child(0).child(9);
        boolean attribute = astronomy.hasAttribute("midday");
        // out.println(attribute);
        XMLTree channel = xml.child(0).child(0);
        printMiddleNode(channel, out);
        //out.print(xml);
        //xml.display();
        in.close();
        out.close();
    }

}
