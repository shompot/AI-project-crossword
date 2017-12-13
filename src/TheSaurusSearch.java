import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TheSaurusSearch {
    // VARIABLES
    private String word;
    private ArrayList<String> synonyms;
    private String address;

    // CONSTRUCTOR
    public TheSaurusSearch (String word){
        this.word = word;
        this.synonyms = new ArrayList<String>();
        this.address = "http://www.thesaurus.com/browse/";
    }

    // SETTERS
    public void setWord(String word) { this.word = word; }
    public void setAddress(String address) { this.address = address; }

    // GETTERS
    public String getWord() { return word; }
    public String getAddress() { return address; }
    public ArrayList<String> getSynonyms() { return synonyms; }

    // SEARCH
    public void search () throws IOException{
        String url = this.address+this.word;
        Document documents = Jsoup.connect(url).get();
        String html  = documents.html();
        html = extractSynonymsHTML(html);
        extractSynonyms(html);
    }

    public String extractSynonymsHTML (String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("relevancy-list");

        String text = elements.toString();
        return text;
    }

    public void extractSynonyms (String html){
        Document document = Jsoup.parse(html);

        Elements elements = document.getElementsByTag("a");

        //System.out.println("Here are the synonyms:");
        for (int i= 0; i< elements.size(); i++) {
            Document d = Jsoup.parse(elements.get(i).toString());
            Elements el = d.getElementsByClass("text");
            synonyms.add(el.text());
            //System.out.println(el.text());

        }
        //System.out.println("\nEnd of Synonyms");

    }
    // PRINT
    public String toString (){
        String result = "";
        for (int i=0; i < synonyms.size(); i ++)
            result += synonyms.get(i) + "\n";
        return result;
    }


    public static void main (String[] args) throws IOException{
        TheSaurusSearch s = new TheSaurusSearch("love");
        s.search();
        ArrayList <String> synonyms = s.getSynonyms();
        for (int i=0; i < synonyms.size(); i ++){
            System.out.println((i+1) + ". " + synonyms.get(i));
        }
    }
}
