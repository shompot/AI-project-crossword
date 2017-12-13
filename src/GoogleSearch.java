import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleSearch {
    // VARIABLES
    public String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public int NUM_OF_HEADERS_TO_SEARCH = 10;

    // CONSTRUCTOR
    public GoogleSearch (){
    }

    // GETTERS
    public int getNUM_OF_HEADERS_TO_SEARCH() { return NUM_OF_HEADERS_TO_SEARCH; }
    public String getGOOGLE_SEARCH_URL() { return GOOGLE_SEARCH_URL; }

    // METHODS
    public ArrayList<String> search (String clue, int length) throws IOException{
        ArrayList<String> result = getWordsForClue(clue, length);
        return result;
    }
    public ArrayList<String> getWordsForClue(String clue, int length) throws IOException {

        ArrayList<String> wordList = new ArrayList<String>();

        Pattern pattern = Pattern.compile("[ ^$][a-zA-Z]{" + length + "}[ ^$]");

        String searchURL = GOOGLE_SEARCH_URL + "?q=" + clue + "&num=" + NUM_OF_HEADERS_TO_SEARCH;
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        Elements results = doc.select("h3.r > a, span.st");

        for (Element result : results) { // This one gathers headers and texts in one header, one text fashion.
            String text = result.text();
            Matcher matcher = pattern.matcher(text);

            while(matcher.find()) {
                String word = matcher.group().toLowerCase();

                if (!wordList.contains(word)){
                    wordList.add(word);
                    System.out.println(word);
                }
            }
        }
        //System.out.println(wordList.size());
        return wordList;
    }
    public static void main (String [] args) throws IOException{
        GoogleSearch s = new GoogleSearch();
        ArrayList<String> result = s.search("gas in vegas", 4);
    }
}
