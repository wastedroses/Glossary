import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Kardiatou Ly
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
        // no code needed here
    }

    /**
     * A comparator that's been made for the queue, so it's able to be sorted
     * alphabetically.
     *
     */
    //alphabetical comparator
    private static class alphabetical implements Comparator<String> {
        @Override
        public int compare(String x, String y) {
            return x.compareTo(y);
        }
    }

    /**
     * Creating the initial glossary page with all of the words, and listing
     * them in alphabetical order with bullet points.
     *
     * @param words
     *            The map that's been sorted in alphabetical order
     * @param out
     *            SimpleWriter Out function
     */
    public static void listCreation(Queue<String> words, SimpleWriter out) {

        //creating the webpage and title
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Glossary </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2> Glossary </h2>");
        out.println("<hr>");
        out.println("<h3> Index </h3>");
        out.println("<ul>");
        //for each loop that iterates through a map and creates a list using
        //that map
        int length = words.length();
        for (int i = 0; i < length; i++) {
            out.println("<li><a href='" + words.front() + ".html'>"
                    + words.front() + "</a></li>");
            String x = words.dequeue();
            words.enqueue(x);
        }

        //closing tags

        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Creates a webpage with the word and definition, the word is big, red and
     * italicized, while the definition is listed below and a "return to index".
     *
     * @param wordAndDef
     *            a map with both the words and definition.
     * @param word
     *            a string for the word that is being used as webpage
     *
     *
     */

    public static void wordWebpage(Map<String, String> wordAndDef,
            String word) {

        //for each loop that iterates through a map
        for (Map.Pair<String, String> wordOrDef : wordAndDef) {
            //creating SimpleWriter for webpage
            //SimpleWriter out = new SimpleWriter1L(
            //word + "/" + wordOrDef.key() + ".html");
            //creating the webpage
            SimpleWriter out = new SimpleWriter1L(wordOrDef.key() + ".html");
            out.println("<head>");
            out.println("<title>" + wordOrDef.key() + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>");
            out.println("<b><i><font color = 'red'>" + wordOrDef.key()
                    + "</font></i></b>");
            //creating definition
            out.println("</h2>");
            out.println("<blockquote>" + wordOrDef.value() + "</blockquote>");
            out.println("<hr>");
            out.println("<p>");
            out.println("Return to <a href='" + word + "'> Back to Index</a>");
            out.println("</p>");
            out.println("</body>");
            out.println("</html>");
            out.close();

        }

    }

    /**
     * Using a map and queue to create an alphabetical map.
     *
     * @param input
     *            SimpleReader input to read txt file & map.
     * @return Map<String, String> that is alphabetically ordered.
     */

    public static Queue<String> wordAndDef(Map<String, String> stringMap,
            SimpleReader input) {

        //declaring variables
        Queue<String> terms = new Queue1L<>();
        //while input isn't done yet,
        while (!input.atEOS()) {
            String term = input.nextLine();
            StringBuilder definition = new StringBuilder();
            String empty;
            //while the input isn't exmpty
            while (!(empty = input.nextLine()).isEmpty()) {
                definition.append(empty).append(' ');
            }
            //adding terms to map and queue
            stringMap.add(term, definition.toString());
            terms.enqueue(term);
        }
        //calling comparator method
        Comparator<String> alphabet = new alphabetical();
        terms.sort(alphabet);
        //new sorted map
        System.out.println(terms);
        return terms;
    }

    /**
     * Main method.
     *
     * @param args
     *
     */
    public static void main(String[] args) {
        //creating variables and letting SimpleReaders/Writers go into their
        //respective places when looking at the .txt file and webpage.
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        out.print("Input the path of the glossary file: ");
        String input = in.nextLine();
        out.print("Enter the output folder path: ");
        String output = in.nextLine();
        SimpleReader file = new SimpleReader1L(input);
        Map<String, String> glossary = new Map1L<>();
        Queue<String> words = wordAndDef(glossary, file);
        file.close();
        SimpleWriter index = new SimpleWriter1L(output);
        listCreation(words, index);
        index.close();
        wordWebpage(glossary, output);

        //Closing SimpleWriters/Readers
        in.close();
        out.close();
    }
}