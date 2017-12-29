import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TheSaurusSearch {
    // VARIABLES
    private String address;

    // CONSTRUCTOR
    public TheSaurusSearch (){
        this.address = "http://www.thesaurus.com/browse/";
    }

    // SETTERS
    public void setAddress(String address) { this.address = address; }

    // GETTERS
    public String getAddress() { return address; }

    // SEARCH
    public ArrayList<String> search (String word, int length) throws IOException{

        ArrayList<String> synonyms = new ArrayList<String>();

        String[] words = word.split("\\s+");
        //for (String word: clues.get(i).split("\\s+")) {
        if (words.length>1){
            return synonyms;
        }

        String url = this.address + word;
        try {
            Document documents = Jsoup.connect(url).get();
            String html = documents.html();
            // check for misspelling
            if (isSpelledCorrectly(html)) {
                html = extractSynonymsHTML(html);
                synonyms = extractSynonyms(html, length);
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return synonyms;
    }

    public boolean isSpelledCorrectly (String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("misspelled");

        if (elements.isEmpty())
            return true;
        else
            return false;
    }
    public String extractSynonymsHTML (String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("relevancy-list");

        String text = elements.toString();
        return text;
    }

    public ArrayList<String> extractSynonyms (String html, int length){
        ArrayList<String> synonyms = new ArrayList<String>();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("a");

        //System.out.println("Here are the synonyms:");
        for (int i= 0; i< elements.size(); i++) {
            Document d = Jsoup.parse(elements.get(i).toString());
            Elements el = d.getElementsByClass("text");
            String word = el.text();
            if (word.length() == length)
                synonyms.add(word.toUpperCase());
            //System.out.println(el.text());

        }
        //System.out.println("\nEnd of Synonyms");
        return synonyms;

    }

    public static void main (String[] args) throws IOException{
        TheSaurusSearch s = new TheSaurusSearch();
        ArrayList <String> synonyms = s.search("appetizer", 3);
        for (int i=0; i < synonyms.size(); i ++){
            System.out.println((i+1) + ". " + synonyms.get(i));
        }
    }
}
