import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolvePuzzleV2 {
    // VARIABLES
    private ArrayList<ArrayList<String>> wordLists;
    private Crossword crossword;
    private ArrayList<String> clues;
    private ArrayList<String> solution;
    private ArrayList<String> logs;
    //private String

    // CONSTRUCTOR
    public SolvePuzzleV2(){
        crossword = new Crossword();
        wordLists = new ArrayList<ArrayList<String>>();
        clues = new ArrayList<String>();
        solution = new ArrayList<String>();
        logs= new ArrayList<String>();
        System.out.println("Solve Puzzle object is created");
    }

    // SETTERS

    // GETTERS
    public ArrayList<String> getLog() { return logs; }

    // METHODS
    public void openCrossword () throws IOException{
        System.out.println("Retrieving crossword");
        crossword.readGridFromFile("crosswords/December 12, 2017.html");
        //crossword.readGridFromUrl();
    }
    public void readWordLists () throws IOException{
        System.out.println("Getting Clues from the Crossword");
        // get clues
        clues.addAll(crossword.getAcrossHintsOnly());
        clues.addAll(crossword.getDownHintsOnly());

        System.out.println("Getting Solution from the Crossword");
        // get solution
        solution.addAll(crossword.getAcrossSolution());
        solution.addAll(crossword.getDownSolution());


        // create searching instances
        GoogleSearch googleSearch = new GoogleSearch();
        DictionarySearch dictSearch = new DictionarySearch();
        TheSaurusSearch theSaurusSearch = new TheSaurusSearch();
        LyricsSearch lyricsSearch = new LyricsSearch();

        // search each clue one by one
        for (int i=0; i <clues.size(); i ++){
            ArrayList<String> result = new ArrayList<String>();
            System.out.println("Searching possible solutions for word number " + (i+1));
            String[] words = clues.get(i).split("\\s+");
            //for (String word: clues.get(i).split("\\s+")) {
            if (words.length==1){
                System.out.println("\tSearching in TheSaurus");
                result.addAll(theSaurusSearch.search(words[0], solution.get(i).length()));
                System.out.println("\t\t\tSaved words found in TheSaurus");
            }
            System.out.println("\tSearching in Dictionary");
            result.addAll(dictSearch.search(clues.get(i), solution.get(i).length()));
            System.out.println("\t\t\tSaved words found in Dictionary");
            System.out.println("\tSearchng in Google");
            result.addAll(googleSearch.search(clues.get(i), solution.get(i).length()));
            System.out.println("\t\t\tSaved words found in Google");
            System.out.println("\tSearching in Lyrics");
            result.addAll(lyricsSearch.search(clues.get(i), solution.get(i).length()));
            System.out.println("\t\t\tSaved words found in Lyrics");

            wordLists.add(i,result);
        }

    }

    public ArrayList<String> getFirstAcross() throws IOException{
        System.out.println("Opening the Crossword");
        this.openCrossword();
        System.out.println("Reading the Words");
        this.readWordLists();
        System.out.println("Done Reading the Rords");
        return wordLists.get(0);
    }

    // MAIN
    public static void main(String[] args) throws IOException{
        SolvePuzzleV2 solvePuzzle = new SolvePuzzleV2();
        ArrayList<String> solutionForFirstAcross = solvePuzzle.getFirstAcross();


    }
}
