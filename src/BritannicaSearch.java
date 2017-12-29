import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BritannicaSearch {
    // VARIABLES
    private String address;
    private ArrayList<String> result;
    // CONSTRUCTOR
    public BritannicaSearch (){
        this.address = "https://www.britannica.com/search?query=";
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

            html = extractHTML(html);
            extractWords(html, length);

        }
        catch (Exception e){
            //System.out.print(e.toString());
        }
        return result;
    }

    public String extractHTML(String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("results");

        String text = elements.toString();
        //System.out.println(text);
        return text;

    }
    public void extractWords (String html, int length){

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("li");

        //System.out.println("Here are the synonyms:");
        for (int i= 0; i< elements.size(); i++) {
            String text = elements.get(i).text();

            System.out.println(text + ":");
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
                        System.out.println("\t" + word);
                    }
                }
            }
        }
    }


    public static void main (String[] args) throws IOException{
        BritannicaSearch s = new BritannicaSearch();
        s.search("love", 5);
        ArrayList<String> result = s.getResult();
        System.out.println("-------------TOTAL RESULT-------------: ");
        for (int i=0; i <result.size(); i++){
            System.out.println(result.get(i)+", ");
        }
        System.out.println();

    }

}
