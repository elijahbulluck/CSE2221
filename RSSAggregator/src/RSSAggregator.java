import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program that converts an XML file full of multiple RSS feeds into a web page
 * with links to the RSS feeds.
 *
 * @author Elijah Bulluck
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";
        //(from RSSProcessing lab)
        //returns the index of the tag if it is found (if statement),
        //and if not, return -1
        int j = -1;
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).label().equals(tag)) {
                j = i;
            }
        }
        return j;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";
        //start table row
        out.println("<tr>");
        //look for date and if not present, writes "No date available in the table
        int numDate = getChildElement(item, "pubDate");
        if (numDate == -1) {
            out.println("<td>No date available</td>");
        } else {
            //make if-statement for children because their still may be
            //a date tag, but it may have no text
            if (item.child(numDate).numberOfChildren() > 0) {
                out.println("<td>" + item.child(numDate).child(0).label()
                        + "</td>");
            } else {
                out.println("<td>No date available");
            }
        }
        //do the same as above for source with the addition of an attribute
        int numSource = getChildElement(item, "source");
        if (numSource == -1) {
            out.println("<td>No source available</td>");
        } else {
            //print the source with the url and optional source if there
            if (item.child(numSource).numberOfChildren() > 0) {
                out.println("<td><a href=\""
                        + item.child(numSource).attributeValue("url") + "\">"
                        + item.child(numSource).child(0).label() + "</td>");
                //if there's no url, print the source with no link
            } else {
                out.println("<td><a href=\""
                        + item.child(numSource).attributeValue("url")
                        + "\"></td>");
            }
        }
        //print title OR description depending on which is found
        int titleNum = getChildElement(item, "title");
        int descNum = getChildElement(item, "description");
        int linkNum = getChildElement(item, "link");
        //start off with no title and then add to it if conditions are met
        String newsRow = "No title available";
        if (titleNum != -1) {
            //now check if title or description have a text child and if not,
            //keep the newRow the same
            if (item.child(titleNum).numberOfChildren() > 0) {
                newsRow = item.child(titleNum).child(0).label();
            } else if (item.child(descNum).numberOfChildren() > 0) {
                newsRow = item.child(descNum).child(0).label();
            } else {
                newsRow = "No title available";
            }
            //use else if because description should only be printed
            // if titleNum == -1 and description exists
        } else if (descNum != -1) {
            //check if description has a text child
            if (item.child(descNum).numberOfChildren() > 0) {
                newsRow = item.child(descNum).child(0).label();
            } else {
                newsRow = "No title available";
            }
        }
        //prints the link if one is present
        if (linkNum != -1) {
            out.println("<th><a href=\"" + item.child(linkNum).child(0).label()
                    + "\">" + newsRow + "</th>");
        }
        //close the row
        out.println("</tr>");
    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {
        //create an XMLTree for the url
        XMLTree feedXML = new XMLTree1(url);
        //check if rss is valid
        if (feedXML.label().equals("rss") && feedXML.hasAttribute("version")
                && feedXML.attributeValue("version").equals("2.0")) {
            XMLTree channel = feedXML.child(0);
            //now print html file
            //will input the current RSS feed into another file
            String title = "";
            int titleNum = getChildElement(channel, "title");

            String desc = "";
            int descNum = getChildElement(channel, "description");
            int linkNum = getChildElement(channel, "link");
            //title is assumed to already be a tag, so check if title has a text
            //child, if not print "Empty Title"
            if (channel.child(titleNum).numberOfChildren() > 0) {
                title = channel.child(titleNum).child(0).label();
            } else {
                title = "Empty Title";
            }
            //check if description has a text child and if not, print "No description"
            if (channel.child(descNum).numberOfChildren() > 0) {
                desc = channel.child(descNum).child(0).label();
            } else {
                desc = "No description";
            }
//print out html output like how contract says to
            //print out html output like how contract says to
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + title + "</title>");
            out.println("</head>");
            out.println("<body>");
            //output the link
            out.println(
                    "<h1><a href = " + channel.child(linkNum).child(0).label()
                            + "\">" + title + "</a></h1>");
            out.println("<p>" + desc + "</p>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th><strong>Date</strong></th>");
            out.println("<th><strong>Source</strong></th>");
            out.println("<th><strong>News</strong></th>");
            out.println("</tr>");
            //create a loop for the item tables that goes through all of the
            //children of channel to create the tables
            for (int i = 0; i < channel.numberOfChildren(); i++) {
                if (channel.child(i).label().equals("item")) {
                    //use processItem method to make the tables
                    processItem(channel.child(i), out);
                }
            }
            //close the RSS feed
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
        out.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.print("Enter the name of a valid XML file: ");
        String xmlFile = in.nextLine();
        XMLTree xml = new XMLTree1(xmlFile);
        /*
         * user must enter name of html file
         */
        out.print("Enter the name of an html file: ");
        String fileName = in.nextLine();
        SimpleWriter file = new SimpleWriter1L(fileName);
        //get the title from the xml file for the title of the html file
        String titleName = xml.attributeValue("title");
        file.println("<html>");
        file.println("<head>");
        file.println("<title>" + titleName + "</title>");
        file.println("</head>");
        file.println("<body>");
        file.println("<h1>" + titleName + "</h1>");
        //create a loop that will loop until it gets through all of the
        //RSS files in <feeds>
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).label().equals("feed")) {
                //turns the title of the current RSS into a link
                file.println("<div><a href =\""
                        + xml.child(i).attributeValue("file") + "\">"
                        + xml.child(i).attributeValue("name") + "</a></div>");
                //create a new file, XMLTree, and SimpleWriter for each RSS feed
                String rssURL = xml.child(i).attributeValue("url");
                String rssFile = xml.child(i).attributeValue("file");
                SimpleWriter rssOut = new SimpleWriter1L(rssFile);
                //now use new processFeed method for the rss urls
                processFeed(rssURL, rssFile, rssOut);
            }
        }
        //close XML file
        file.println("</body>");
        file.println("</html>");
        in.close();
        out.close();
        file.close();

    }

}
