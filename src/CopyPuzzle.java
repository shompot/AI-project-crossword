
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CopyPuzzle {
    public static void main(String[] args) throws IOException{
        String[] clues= getClues();
    }
    public static String[] getClues() throws IOException{
        String[] clues = new String[10];
        String url = "https://www.nytimes.com/crosswords/game/mini";
        Document documents = Jsoup.connect(url).get();
        String html  = documents.html();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("Clue-li--1JoPu");
        for (int i= 0; i< elements.size(); i++) {
            clues[i] = elements.get(i).text();
            System.out.println(clues[i]);
        }
        

        return clues;
    }
    public static void getBlockedGrids() throws IOException{
        ArrayList<Integer> blockedGrids = new ArrayList<Integer>();
        String url = "https://www.nytimes.com/crosswords/game/mini";
        Document document = Jsoup.connect(url).get();
        String html  = document.html();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag("g");
        for (int i= 0; i< elements.size(); i++) {
            if(elements.get(i).hasClass("Cell-cell--1p4gH")){
                blockedGrids.add(1);
            }
            else
                blockedGrids.add(0);

        }
        System.out.println(blockedGrids.toString());

    }
