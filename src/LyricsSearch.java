import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class LyricsSearch {
    // VARIABLES
    private String address;
    private ArrayList<String> result;
    // CONSTRUCTOR
    public LyricsSearch (){
        this.address = "https://search.azlyrics.com/search.php?q=";
    }

    // SETTERS
    public void setAddress(String address) { this.address = address; }

    // GETTERS
    public String getAddress() { return address; }
    public ArrayList<String> getResult() { return result; }

    // SEARCH
    public ArrayList<String> search (String word, int length) throws IOException {
        result = new ArrayList<String>();
        String url = this.address+word;
        try {
            Document documents = Jsoup.connect(url).get();
            String html = documents.html();

            String titleHTML = extractTitleHTML(html);
            extractTitleWords(titleHTML, length);

            String refHTML = extractRefHTML(html);
            ArrayList<String> links = extractRefs(refHTML);
            readFromLinks(links, length);
        }
        catch (Exception e){
            //System.out.print(e.toString());
        }
        return result;
    }

    public String extractRefHTML (String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("table table-condensed");

        String text = elements.toString();
        //System.out.println(text);
        return text;
    }

    public ArrayList<String> extractRefs (String html){
        ArrayList <String> links = new ArrayList<String>();

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("text-left visitedlyr");

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

    public String extractTitleHTML(String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("text-left visitedlyr");

        String text = elements.toString();
        //System.out.println(text);
        return text;

    }
    public void extractTitleWords (String html, int length){

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("a");

        //System.out.println("Here are the synonyms:");
        for (int i= 0; i< elements.size(); i++) {
            String text = elements.get(i).text();

            //System.out.println(text + ":");
            for (String word : text.split("\\s+")){
                if (word.length() == length && (!result.contains(word.toUpperCase()))){
                    boolean isAllLetter = true;
                    for (int j=0; j < length; j ++){
                        if (!Character.isLetter(word.charAt(j))){
                            isAllLetter = false;
                            break;
                        }
                    }
                    if (isAllLetter) {
                        result.add(word.toUpperCase());
                        //System.out.println("\t" + word);
                    }
                }
            }
        }
    }

    public void readFromLinks (ArrayList<String> links, int length) throws  IOException{
        for (int i=0; i < links.size(); i ++){
            Document documents = Jsoup.connect(links.get(i)).get();
            String html  = documents.html();
            readFromOneLink(html, length);
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
                        if (!Character.isLetter(word.charAt(j))) {
                            isAllLetter = false;
                            break;
                        }
                    }
                    if (isAllLetter) {
                        result.add(word.toUpperCase());
                        //System.out.println("\t" + word);
                    }
                }
            }
        }
    }

    public static void main (String[] args) throws IOException{
        LyricsSearch s = new LyricsSearch();
        s.search("love", 5);
        ArrayList<String> result = s.getResult();
        //Collections.sort(result);
        System.out.println("-------------TOTAL RESULT-------------: ");
        for (int i=0; i <result.size(); i++){
            System.out.println(result.get(i)+", ");
        }
        System.out.println();

    }

}
