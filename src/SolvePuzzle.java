import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolvePuzzle {

    // VARIABLES
    private ArrayList<ArrayList<String>> wordLists;
    private Crossword crossword;
    private ArrayList<String> clues;
    private ArrayList<String> solution;
<<<<<<< HEAD
    private JTextArea solveLog;
    public JPanel solvePanel;
=======
    private ArrayList<String> logs;
    //private String
>>>>>>> 068c717bf819b0515d07c86ebc03b900d0407ab0

    // CONSTRUCTOR
    public SolvePuzzle(){
        crossword = new Crossword();
        wordLists = new ArrayList<ArrayList<String>>();
        clues = new ArrayList<String>();
        solution = new ArrayList<String>();
        logs= new ArrayList<String>();
        logs.add("Solve Puzzle object is created");
    }

    // SETTERS

    // GETTERS
<<<<<<< HEAD
    public Crossword getCrossword()
    {
        return this.crossword;
    }
=======
    public ArrayList<String> getLog() { return logs; }

>>>>>>> 068c717bf819b0515d07c86ebc03b900d0407ab0
    // METHODS
    public void openCrossword () throws IOException{
        logs.add("Retrieving crossword");
        crossword.readGridFromFile("crosswords/December 12, 2017.html");
        //crossword.readGridFromUrl();
    }
   public void readWordLists () throws IOException{
<<<<<<< HEAD

=======
        logs.add("Getting Clues from the Crossword");
>>>>>>> 068c717bf819b0515d07c86ebc03b900d0407ab0
        // get clues
        clues.addAll(crossword.getDownHintsOnly());
        clues.addAll(crossword.getAcrossHintsOnly());

        logs.add("Getting Solution from the Crossword");
        // get solution
        solution.addAll(crossword.getDownSolution());
        solution.addAll(crossword.getAcrossSolution());

       // create searching instances
        GoogleSearch googleSearch = new GoogleSearch();
        DictionarySearch dictSearch = new DictionarySearch();
        TheSaurusSearch theSaurusSearch = new TheSaurusSearch();
        LyricsSearch lyricsSearch = new LyricsSearch();

        // search each clue one by one
       for (int i=0; i <clues.size(); i ++){
           ArrayList<String> result = new ArrayList<String>();
           String[] words = clues.get(i).split("\\s+");
           //for (String word: clues.get(i).split("\\s+")) {
            if (words.length==1){
                logs.add("Searching in TheSaurus");
               result.addAll(theSaurusSearch.search(words[0], solution.get(i).length()));
           }
           logs.add("Searching in Dictionary");
           result.addAll(dictSearch.search(clues.get(i), solution.get(i).length()));
           logs.add("Searchng in Google");
           result.addAll(googleSearch.search(clues.get(i), solution.get(i).length()));
           logs.add("Searching in Lyrics");
           result.addAll(lyricsSearch.search(clues.get(i), solution.get(i).length()));

           wordLists.add(result);
       }

   }

    public char[][] solve() throws IOException{
<<<<<<< HEAD
        MessageConsole mc = new MessageConsole(solveLog);
        mc.redirectOut();
        mc.redirectErr(Color.RED, null);
        mc.setMessageLines(5000);
=======
>>>>>>> 068c717bf819b0515d07c86ebc03b900d0407ab0
        openCrossword();
        readWordLists();
        logs.add("Trying the retrieved words");
        char [] solutionArr = crossword.getSolutionArr();

        char [][] answer = new char [5][5];

        char[][] solution = new char[5][5];
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (solutionArr[i*5+j] == ' '){
                    answer[i][j] = '@';
                    solution[i][j] = '@';
                } else {
                    answer[i][j] = Character.toLowerCase(solutionArr[i*5+j]);
                    solution[i][j] = '.';
                }
            }
        }
        //@ is for denoting black boxes.

        //Attack for the clue that has most known letters. if letter numbers are equal, go for the one with least words in its wordList.
        //ALWAYS, first 5 is the vertical ones, the other five is horizontal ones. left to right, top to bottom.
        int[] knownLetters = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //-1 means that that clue has been attacked before and wont be attacked again.
        int[] allAttacked = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}; //to stop the loop.
        do {
            for (int i=0; i<5; i++){ //evaluating the vertical words.
                int letterCount = 0;
                int atCount = 0;
                for (int j=0; j<5; j++){
                    if (solution[i][j] == '@') {
                        atCount++;
                    } else if (solution[i][j] >= 'a' && solution[i][j] <= 'z'){
                        letterCount++;
                    }
                }
                if (knownLetters[i] != -1){
                    if (atCount + letterCount == 5){
                        knownLetters[i] = -1;
                    } else {
                        knownLetters[i] = letterCount;
                    }
                }
            }

            for (int i=0; i<5; i++){ //evaluating the horizontal words.
                int letterCount = 0;
                int atCount = 0;
                for (int j=0; j<5; j++){
                    if (solution[j][i] == '@') {
                        atCount++;
                    } else if (solution[j][i] >= 'a' && solution[j][i] <= 'z'){
                        letterCount++;
                    }
                }
                if (knownLetters[i+5] != -1){
                    if (atCount + letterCount == 5){
                        knownLetters[i+5] = -1;
                    } else {
                        knownLetters[i+5] = letterCount;
                    }
                }
            }

            //Find index of clue with most known letters and take corresponding arrayList.
            int index = 0;
            for (int i=1; i<knownLetters.length; i++){
                if (knownLetters[i]>knownLetters[index]){
                    System.out.println("WWW");
                    index = i;
                }
            }

            String regex = "";
            String answerWord =  "";
            if (index < 5){
                for (int i=0; i<5; i++){
                    regex = (solution[index][i] == '@' ? regex : regex + solution[index][i]);
                    answerWord = (answer[index][i] == '@' ? answerWord : answerWord + answer[index][i]);
                }
            } else {
                for (int i=0; i<5; i++){
                    regex = (solution[i][index-5] == '@' ? regex : regex + solution[i][index-5]);
                    answerWord = (answer[i][index-5] == '@' ? answerWord : answerWord + answer[i][index-5]);
                }
            }
            for (String s : wordLists.get(index)){
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()){ //Found something that matches! Check it against the answer!
                    String word = matcher.group();
                    if (word == answerWord){ //match!
                        regex = word;
                    } else { //not a match, but lets keep the matching letter in mind.
                        char[] regexCharArray = regex.toCharArray();
                        for (int i=0; i<word.length(); i++){ //If a letter is equal, update your regex. So that your next trials are informed.
                            if (word.charAt(i) == answerWord.charAt(i)){
                                regexCharArray[i] = word.charAt(i);
                            }
                        }
                        regex = new String(regexCharArray);
                    }
                }
            }

            knownLetters[index] = -1; //This clue is attacked and wont be attacked again. This line is what it means.

            // the string regex changed and learned over time. Let's put regex into our current solution.
            // In short, the code below updates solution[][].
            char[] charRegex = regex.toCharArray();
            int curr = 0; //
            if (index < 5) {
                for (int i=0; i<5; i++){
                    if (solution[index][i] != '@'){
                        solution[index][i] = charRegex[curr];
                        curr++;
                    }
                }
            } else {
                for (int i=0; i<5; i++){
                    if (solution[i][index-5] != '@'){
                        solution[i][index-5] = charRegex[curr];
                        curr++;
                    }
                }
            }

            //printPuzzle(solution);

        } while (!Arrays.equals(knownLetters, allAttacked));

        return solution;
    }

   // MAIN
    public static void main(String[] args) throws IOException{
        SolvePuzzle solvePuzzle = new SolvePuzzle();
        char [][] solution = solvePuzzle.solve();

        System.out.println("Solution is: ");

        for (int i=0; i < 5; i ++) {
            for (int j=0; j < 5; j++)
                System.out.print(solution[i][j]);
            System.out.println();
        }

    }
}
