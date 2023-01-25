import java.util.Arrays;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program that takes an input file and creates a glossary out of HTML files.
 *
 * @author Elijah Bulluck
 *
 */
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    //using nextWordOrSeparator from previous lab
    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int end = position;
        //create a boolean to see if the first character
        //is a separator or not
        boolean sepOrNot = separators.contains(text.charAt(position));
        //according to whether the first character was a separator
        //or not, create a loop that goes through text and
        //makes the substring larger according to whether the next char
        //is the same as the first one (separator or word)
        while (end < text.length()
                && separators.contains(text.charAt(end)) == sepOrNot) {
            //increase the end position of the substring
            //since the requirements were met
            end++;
        }
        //return the substring of text that is the longest word or separator
        String maxWoS = text.substring(position, end);
        return maxWoS;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";
        //replaces charSet, so clear it and replace it with the new
        //set of characters
        charSet.clear();
        //add to charSet if the character of the String str isn't a duplicate
        for (int i = 0; i < str.length(); i++) {
            if (!charSet.contains(str.charAt(i))) {
                charSet.add(str.charAt(i));
            }
        }

    }

    /**
     * Generates the Strings of pairs of terms and definitions in the input file
     * into a map, sorted alphabetically.
     *
     * @param file
     *            input file
     * @param m
     *            the map that will contain the terms and definitions
     * @replaces strMap
     * @ensures strMap = terms, definitions (of file)
     */
    public static void getTerms(SimpleReader file, Map<String, String> m) {
        String term = "";
        String definition = "";
        String firstLine = " ";
        while (!file.atEOS()) {
            //input file has the term on one line followed by
            //the definition, so the first line is equal to the term
            term = file.nextLine();
            //one of the definitions is more than one line, so separate
            //every line with a loop to make sure every line is being
            //picked up
            //reset definition in loop so it doesn't add every definition
            //together
            firstLine = " ";
            definition = "";
            while (!firstLine.equals("")) {
                firstLine = file.nextLine();
                definition = definition + " " + firstLine;
            }
            m.add(term, definition);
        }
    }

    /**
     * Creates an array of the keys from map and sorts them alphabetically.
     *
     * @param m
     *            map that will be used for arr
     *
     * @return a new, alphabetically sorted array
     */
    public static String[] createArr(Map<String, String> m) {
        String[] arr = new String[m.size()];
        Map<String, String> temp = m.newInstance();
        temp.transferFrom(m);
        int count = 0;
        //use example from class of safe map loop
        while (temp.size() > 0) {
            Map.Pair<String, String> p = temp.removeAny();
            //set position in array equal to key value
            arr[count] = p.key();
            //add the key and value back to the map
            m.add(p.key(), p.value());
            count++;
        }
        //sort array into alphabetical order using Arrays.sort
        Arrays.sort(arr);
        return arr;
    }

    /**
     * Updates given map to alphabetical order.
     *
     * @param arr
     *            given alphabetical arr
     *
     * @param m
     *            map that will be updated
     * @param s
     *            set of separators to be used in nextWordOrSeparator
     * @updates m
     */
    private static void updateMapAlphabetically(String[] arr,
            Map<String, String> m) {
        //update map to alphabetical order
        Map<String, String> copy = new Map1L<String, String>();
        for (int i = 0; i < arr.length; i++) {
            copy.add(arr[i], m.value(arr[i]));
        }
        m.transferFrom(copy);
    }

    /**
     * Updates given map's definitions to include links to other terms.
     *
     * @param arr
     *            given alphabetical arr
     *
     * @param m
     *            map that will be updated
     * @param s
     *            set of separators to be used in nextWordOrSeparator
     * @updates m
     */
    private static void updateDefinitions(String[] arr, Map<String, String> m,
            Set<Character> s) {
        //create a copy for the link update
        Map<String, String> copy2 = new Map1L<String, String>();
        //create a set for the terms
        Set<String> setTerms = new Set1L<String>();
        String newDef = "";
        int pos = 0;
        //add the terms to the set of terms
        for (int j = 0; j < arr.length; j++) {
            setTerms.add(arr[j]);
        }
        newDef = "";
        for (int k = 0; k < setTerms.size(); k++) {
            pos = 0;
            newDef = "";
            String defStr = m.value(arr[k]);
            //separate the definition by word using nextWordOrSeparator
            while (pos < defStr.length()) {
                String str = nextWordOrSeparator(defStr, pos, s);
                //if the string is a separator, add it to the new definition
                //with no changes
                if (s.contains(str.charAt(0))) {
                    newDef = newDef + str;
                    //if the word is in the set of terms,
                    //make the word a hyperlink
                } else if (setTerms.contains(str)) {
                    newDef = newDef + "<a href=\"" + str + ".html\">" + str
                            + "</a>";
                    //add the rest to the new definition with no changes
                } else {
                    newDef = newDef + str;
                }
                // move to the next word
                pos = pos + str.length();
            }
            //add the new definition to the copy array that will eventually
            //be transferred to m
            copy2.add(arr[k], newDef);
        }
        m.transferFrom(copy2);
    }

    /**
     * Outputs the index page with the terms and links to the definitions.
     *
     *
     * @param arr
     *            the names of the terms
     * @param out
     *            the output stream
     * @updates out.content
     * @requires out.is_open
     * @ensures out.content = #out.content * [the terms with links]
     */
    private static void indexPage(String[] arr, SimpleWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Glossary</h1>");
        out.println("<h2>Index</h2>");
        out.println("<ul>");
        //create a loop that will output each term with a hyperlink
        for (int i = 0; i < arr.length; i++) {
            String term = arr[i];
            out.println("<li><a href =\"" + term + ".html" + "\">" + term
                    + "</a></li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Outputs each definition into a new HTML file.
     *
     * @param m
     *            the map of the terms and their definitions
     * @param arr
     *            array of terms
     * @param folder
     *            the folder that the HTML files will go into
     * @updates out.content
     * @ensures <pre>
     * {@code out.content = #out.content * [new HTML pages for the definitions]}
     * </pre>
     */
    private static void definitionPage(Map<String, String> m, String[] arr,
            String folder) {
        String strFile = "";
        //create a loop so a new html file is made for each term
        for (int i = 0; i < arr.length; i++) {
            strFile = arr[i];
            SimpleWriter file = new SimpleWriter1L(
                    folder + "/" + strFile + ".html");
            file.println("<html>");
            file.println("<head>");
            file.println("<title>" + strFile + "</title>");
            file.println("</head>");
            file.println("<body>");
            file.println("<h2><b><i><font color=\"red\">" + strFile
                    + "</font></i></b></h2>");
            //print the definition to the file
            file.println("<p>" + m.value(strFile) + "</p>");
            //make a link so the user can return to the index page
            file.println("<div><p>Return to <a href=\"index.html\">index.</a>"
                    + "</p></div>");
            file.println("</body>");
            file.println("</html>");
            //close the file
            file.close();
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
        //user must enter the name of an input file
        out.print("Enter the name of the input file: ");
        String file = in.nextLine();
        //create file with name that the user input
        SimpleReader inFile = new SimpleReader1L(file);
        out.print("Enter the name of the output folder: ");
        String folder = in.nextLine();
        Map<String, String> g = new Map1L<String, String>();
        //map must be filled with terms from input file, use getTerms method
        getTerms(inFile, g);
        //createArr method will put the terms from the map in alphabetical order
        String[] termArr = createArr(g);
        String separatorStr = " \t, ";
        Set<Character> separatedDef = new Set1L<Character>();
        //generateElements will go through every character one-by-one
        //and add it to the set
        generateElements(separatorStr, separatedDef);
        //updateMap will put the map in alphabetical order and check if
        //any terms are found within the definitions
        updateMapAlphabetically(termArr, g);
        updateDefinitions(termArr, g, separatedDef);
        //put the html's in the folder that was typed in by the user by adding
        //the name of the folder before the index file
        SimpleWriter index = new SimpleWriter1L(folder + "/index.html");
        //use indexPage to output the glossary onto the index file
        indexPage(termArr, index);
        //use definitionPage to output the definitions into new html files
        //that will link to the index file
        definitionPage(g, termArr, folder);
        //close SimpleReader's and SimpleWriter
        in.close();
        out.close();
        inFile.close();
    }

}
