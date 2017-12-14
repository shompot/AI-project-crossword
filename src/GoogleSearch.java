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
    private String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    private int NUM_OF_HEADERS_TO_SEARCH = 10;
    private ArrayList<String> result;

    // CONSTRUCTOR
    public GoogleSearch (){
    }

    // GETTERS
    public int getNUM_OF_HEADERS_TO_SEARCH() { return NUM_OF_HEADERS_TO_SEARCH; }
    public String getGOOGLE_SEARCH_URL() { return GOOGLE_SEARCH_URL; }

    // METHODS
    public ArrayList<String> search (String clue, int length) throws IOException{
        result = new ArrayList<String>();
        result = getWordsForClue(clue, length);
        searchPages(clue, length);
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
                String word = matcher.group().toUpperCase();

                if (!wordList.contains(word)){
                    wordList.add(word);
                    //System.out.println(word);
                }
            }
        }
        //System.out.println(wordList.size());
        return wordList;
    }
    public ArrayList<String> readLinks (String html){
        //System.out.println("Reading Links");
        ArrayList<String> links = new ArrayList<String>();

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("r");

        //System.out.println(elements.toString());

        int size = elements.size();
        //System.out.println("SIZE: " + size);
        for (int i = 0; i < size; i ++){
            Element link = document.select("a").get(i);
            String absUrl = link.absUrl("href");

            if (!absUrl.isEmpty()) {
                links.add(absUrl);
                //System.out.println(absUrl);
            }
            else
                size++;
        }

        return links;
    }
    public void searchPages (String clue, int length)throws IOException{
        //System.out.println("Searching pages");

        Pattern pattern = Pattern.compile("[ ^$][a-zA-Z]{" + length + "}[ ^$]");

        String searchURL = GOOGLE_SEARCH_URL + "?q=" + clue + "&num=" + NUM_OF_HEADERS_TO_SEARCH;
        Document document = Jsoup.connect(searchURL).get();
        Elements elements = document.getElementsByClass("srg");

        String html = elements.toString();

        //System.out.println(elements.toString());

        ArrayList<String> links = readLinks(html);
        readFromLinks(links, length);
    }

    public void readFromLinks (ArrayList<String> links, int length) throws  IOException{
        for (int i=0; i < links.size(); i ++){
            try {
                Document documents = Jsoup.connect(links.get(i)).get();
                String html  = documents.html();
                readFromOneLink(html, length);
            }
            catch (Exception e){

            }

        }
    }

    public void readFromOneLink (String html, int length){
        Document document = Jsoup.parse(html);
        Elements elements = document.getAllElements();
        String text = elements.text();
        //System.out.println("\t\t\tHere are the words:\n" + text);
        for (String word : text.split("\\s+")){
            if (word.length() == length){
                if (!result.contains(word.toUpperCase())) {
                    boolean isAllLetter = true;
                    for (int j = 0; j < length; j++) {
                        if (Character.toUpperCase(word.charAt(j))>'z' || Character.toUpperCase(word.charAt(j))<'a') {
                            isAllLetter = false;
                            break;
                        }
                    }
                    if (isAllLetter) {
                        result.add(word.toUpperCase());
                        //System.out.println("\t" + word.toUpperCase());
                    }
                }
            }
        }
    }
    public static void main (String [] args) throws IOException{
        GoogleSearch s = new GoogleSearch();
        ArrayList<String> result = s.search("gas in vegas", 4);
        System.out.println(result.toString());
    }
}
