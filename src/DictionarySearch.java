import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DictionarySearch {

    // VARIABLES
    private String fileAddress;

    // CONSTRUCTOR
    public DictionarySearch (){
        this.fileAddress = "dictionary.txt";
    }
    // SETTERS
    // GETTERS
    public String getFileAddress() { return fileAddress; }

    // SEARCH
    public ArrayList<String> search(String clue, int length) throws IOException {
       ArrayList <String> words = searchRelativeOnes(clue, length);
       return  words;
    }
    //method for searching clues
    //input text = clue
    public ArrayList <String> searchRelativeOnes(String text, int length) throws IOException {
        ArrayList<String> answers= new ArrayList<String>();
        String line;
        text = text.toLowerCase();
        FileReader fr = new FileReader(fileAddress);
        BufferedReader br = new BufferedReader(fr);
        String[] words = text.split(" ");//splitting a clue
        while ((line = br.readLine()) != null) {
            int fromIndex = 0;
            int index = -1;
            if(words.length>=2) {//if clue length is more than 2 it will divide clue and
                for (int k =0; k<words.length/2+1; k=k+2) {

                    text = words[k] + " " +words[k+1];
                }
            }
            while ((index = line.toLowerCase().indexOf(text, fromIndex)) != -1) {
                String answer = line.toString().substring(line.lastIndexOf(" ")+1);
                String[] parts = answer.split("\\t");
                String lastWord = parts[parts.length - 1];
                if (lastWord.length() == length) {
                    answers.add(lastWord.toUpperCase());
                }
                fromIndex = index + text.length();
            }
        }

        fr.close();
        br.close();

        return answers;
    }

    public static void main (String[] args) throws IOException{
        DictionarySearch d = new DictionarySearch();
        ArrayList<String> words = d.search("One who's deep in the weeds of policy", 4);;
        for (int i=0; i < words.size(); i ++){
            System.out.println(words.get(i));
        }
    }
}
