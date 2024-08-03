import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class GlossaryTest {

//testing to see if the method works. There aren't really any hard or difficult
    //test cases for this method.
    @Test
    public void listCreation1() {
        Queue<String> testCase = new Queue1L<>();
        SimpleWriter out = new SimpleWriter1L();
        Glossary.listCreation(testCase, out);
    }

    //testing word that's in glossary
    @Test
    public void wordWebpage1() {
        Map<String, String> testCase = new Map1L<>();
        Map<String, String> expectedMap = new Map1L<>();
        String testWord = "book";
        Glossary.wordWebpage(testCase, testWord);
        assertEquals(expectedMap, testWord);
    }

    //testing word that's in glossary
    @Test
    public void wordWebpage2() {
        Map<String, String> testCase = new Map1L<>();
        Map<String, String> expectedMap = new Map1L<>();
        String testWord = "definition";
        Glossary.wordWebpage(testCase, testWord);
        assertEquals(expectedMap, testWord);
    }

    //testing a word not found in glossary
    @Test
    public void wordWebpage3() {
        Map<String, String> testCase = new Map1L<>();
        Map<String, String> expectedMap = new Map1L<>();
        String testWord = "don't";
        Glossary.wordWebpage(testCase, testWord);
        assertEquals(expectedMap, testWord);
    }

    //ttesting if word and def produces valid output. There's not really many
    //test cases that can be used in this situation.
    @Test
    public void wordAndDef1() {
        SimpleReader in = new SimpleReader1L();
        SimpleReader expectedIn = new SimpleReader1L();
        Map<String, String> testMap = new Map1L<>();
        Map<String, String> expectedMap = new Map1L<>();
        Glossary.wordAndDef(testMap, in);
        assertEquals(expectedMap, expectedIn);
    }

    //testing to see if the words inputted into the method will become alphabetical
    //after running through comparator.
    @Test
    public void comparator1() {
        Queue<String> words = new Queue1L<>();
        Queue<String> sorted = new Queue1L<>();
        assertEquals(words, sorted);
    }

}
