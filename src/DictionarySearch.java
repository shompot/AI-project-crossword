import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DictionarySearch {

    // VARIABLES
    private String clue;
    private ArrayList<String> words;

    // CONSTRUCTOR
    private DictionarySearch (String clue){
        this.clue = clue;
    }

    // SETTERS
    public void setClue(String clue) {
        this.clue = clue;
    }

    // GETTERS
    public String getClue() { return clue; }
    public ArrayList<String> getWords() { return words; }

    // SEARCH
    public void search() throws IOException {
       words = searchRelativeOnes(clue);
    }
    //method for searching clues
    //input text = clue
    public ArrayList <String> searchRelativeOnes(String text) throws IOException {
        ArrayList<String> answers= new ArrayList<String>();
        String line;
        text = text.toLowerCase();
        FileReader fr = new FileReader("dictionary.txt");
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
                if (lastWord.length()<=5) {
                    answers.add(lastWord);
                }
                fromIndex = index + text.length();
            }
        }

        fr.close();
        br.close();

        return answers;
    }

    public static void main (String[] args) throws IOException{
        DictionarySearch d = new DictionarySearch("One who's deep in the weeds of policy");
        d.search();
        ArrayList<String> words = d.getWords();
        for (int i=0; i < words.size(); i ++){
            System.out.println(words.get(i));
        }
    }
}
