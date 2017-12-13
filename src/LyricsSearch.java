import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class LyricsSearch {
    // VARIABLES
    private String word;
    private ArrayList<String> result;
    private String address;

    // CONSTRUCTOR
    public LyricsSearch (String word){
        this.word = word;
        this.result = new ArrayList<String>();
        this.address = "https://search.azlyrics.com/search.php?q=";
    }

    // SETTERS
    public void setWord(String word) { this.word = word; }
    public void setAddress(String address) { this.address = address; }

    // GETTERS
    public String getWord() { return word; }
    public String getAddress() { return address; }
    public ArrayList<String> getResult() { return result; }

    // SEARCH
    public void search () throws IOException {
        String url = this.address+this.word;
        Document documents = Jsoup.connect(url).get();
        String html  = documents.html();
        html = extractHTML(html);
        extractWords(html);
    }

    public String extractHTML(String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("table table-condensed");

        String text = elements.toString();
        System.out.println(text);
        return text;

    }
    public void extractWords (String html){
        Document document = Jsoup.parse(html);

        Elements elements = document.getElementsByTag("a");

        //System.out.println("Here are the synonyms:");
        for (int i= 0; i< elements.size(); i++) {
            //Document d = Jsoup.parse(elements.get(i).toString());
            //Elements el = d.getElementsByClass("text");
            //result.add(el.text());
            System.out.println(elements.text());

        }
    }

    public static void main (String[] args) throws IOException{
        LyricsSearch s = new LyricsSearch("love");
        s.search();
    }

}
