import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class main {

    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static final int NUM_OF_HEADERS_TO_SEARCH = 10;

    public static void main(String[] args) throws IOException {

        getWordsForClue("gas in vegas", 4);

    }

    public static ArrayList<String> getWordsForClue(String clue, int charCount) throws IOException{

        ArrayList<String> wordList = new ArrayList<String>();

        Pattern pattern = Pattern.compile("[ ^$][a-zA-Z]{" + charCount + "}[ ^$]");

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
        System.out.println(wordList.size());
        return wordList;
    }
}