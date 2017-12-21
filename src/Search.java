import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static final int NUM_OF_HEADERS_TO_SEARCH = 200;

    public ArrayList<String> acrossList = new ArrayList<String>();
    public ArrayList<String> downList = new ArrayList<String>();
    public int[] numbers;
    public char[] solution;
    public CrosswordGUI cw;

    Search(ArrayList<String> acrossList, ArrayList<String> downList, int[] numbers, char[] solution, CrosswordGUI cw){
        this.acrossList = acrossList;
        this.downList = downList;
        this.numbers = numbers;
        this.solution = solution;
        this.cw = cw;
    }

    public char[] getSolution() {
        return solution;
    }

    public void dontMakeMeDisappointed() throws IOException {
        ArrayList<String> clues = new ArrayList<String >(10);
        clues.add(""); clues.add(""); clues.add(""); clues.add(""); clues.add("");
        int[] downOrder = {0, 0, 0, 0, 0};
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (downOrder[i] == 0 && this.numbers[j*5+i] != 0){
                    downOrder[i] = this.numbers[j*5+i];
                }
            }
        }
        System.out.println("Downorder:");
        for (int i : downOrder){
            System.out.print(i + ", ");
        }
        for (int i=0; i<5; i++){//Fill down clues
            String clue = this.downList.get(i);
            int j = Integer.parseInt(clue.substring(0, 1));
            clue = clue.substring(3, clue.length());
            int indexOfClue = 0;
            for (int k=0; k<5; k++){
                if (downOrder[k] == j){
                    indexOfClue = k;
                    System.out.println(indexOfClue);
                }
            }
            clues.add(indexOfClue, clue);
        }
        for (String s : this.acrossList){
            clues.add(s.substring(3, s.length()));
        }
        for (int i=0; i<clues.size(); i++){
            if (clues.get(i) == ""){
                clues.remove(i);
                i--;
            }
        }
        System.out.println("Clues in my order:");
        for (String s : clues){
            System.out.println(s);
        }

        System.out.println("Solution Arr:");
        for (char c : getSolution()){
            System.out.println(c + ", ");
        }
        char[] solutionsArr = getSolution();
        char[][] solutions = new char[5][5];
        int[] charCounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (solutionsArr[i*5+j] == ' '){
                    solutions[j][i] = '@';
                } else {
                    System.out.print(j + " incremented from " + charCounts[j] + "to ");
                    charCounts[j]++;
                    System.out.println(charCounts[j] + ".");
                    System.out.print((i+5) + " incremented from " + charCounts[i+5] + " to ");
                    charCounts[i+5]++;
                    System.out.println(charCounts[i+5] + ".");
                    solutions[j][i] = Character.toLowerCase(solutionsArr[i*5+j]);
                }
            }
        }

        printPuzzle(solutions);

        System.out.println("Printing charCounts");
        for (int i=0; i<charCounts.length; i++){
            System.out.println(charCounts[i] + ", ");
        }

        System.out.println();
        //solution, in my order
        //Bu ikisini combine algosuna ver, snapshotlari al.
        cw.getLog().append( "\nNow, looking for answers online. Patience is key here.");
        ArrayList<ArrayList<String>> wordListList = getWordsForClues(clues, charCounts);
        ArrayList<CharGrid> snapShots = solve(solutions, wordListList);
    }

    public ArrayList<ArrayList<String>> getWordsForClues (ArrayList<String> clues, int[] charCounts) throws IOException {
        ArrayList<ArrayList<String>> wordListList = new ArrayList<ArrayList<String>>();
        for (int i=0; i<clues.size(); i++){
            ArrayList<String> wordList = new ArrayList<String>();
            cw.getLog().append( "\nChecking Google for clue #" + i + "...");
            wordList.addAll(getWordsForClue(clues.get(i), charCounts[i]));
            cw.getLog().append( "\nChecking Lyrics for clue #" + i + "...");
            LyricsSearch ls = new LyricsSearch();
            wordList.addAll(ls.search(clues.get(i), charCounts[i]));
            cw.getLog().append( "\nChecking Thesaurus for clue #" + i + "...");
            TheSaurusSearch ts = new TheSaurusSearch();
            wordList.addAll(ts.search(clues.get(i), charCounts[i]));
            wordListList.add(wordList);
        }
        return wordListList;
    }

    public static ArrayList<String> getWordsForClue(String clue, int charCount) throws IOException{

        ArrayList<String> wordList = new ArrayList<String>();

        Pattern pattern = Pattern.compile("[ ^$][a-zA-Z]{" + charCount + "}[ ^$]");

        String searchURL = GOOGLE_SEARCH_URL + "?q=" + clue + "&num=" + NUM_OF_HEADERS_TO_SEARCH;
        try{
            Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
            Elements results = doc.select("h3.r > a, span.st");

            for (Element result : results) { // This one gathers headers and texts in one header, one text fashion.
                String text = result.text();
                Matcher matcher = pattern.matcher(text);

                while(matcher.find()) {
                    String word = matcher.group().toLowerCase();

                    if (!wordList.contains(word)){
                        wordList.add(word);
                    }
                }
            }
        } catch (Exception e){
            // :(
        }

        return wordList;
    }

    public static ArrayList<CharGrid> solve(char[][] answer, ArrayList<ArrayList<String>> wordListList){

        ArrayList<CharGrid> snapShots = new ArrayList<CharGrid>();

        char[][] solution = new char[5][5]; //This is going to be modified and eventually be our solution.
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if (answer[i][j] == '@'){
                    solution[i][j] = '@';
                } else {
                    solution[i][j] = '.';
                }
            }
        }
        //@ is for denoting black boxes.

        //Attack for the clue that has most known letters. if letter numbers are equal, go for the one with least words in its wordlist.
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

            //Find index of clue with most known letters and take corresponding arraylist.
            int index = 0;
            for (int i=1; i<knownLetters.length; i++){
                if (knownLetters[i]>knownLetters[index]){
                    index = i;
                }
            }

            //Initializing regex and answerWord
            String regex = "";
            String answerWord =  "";

            //Proving values to regex and answerWord
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


            for (String s : wordListList.get(index)){
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()){ //Yanlızca bunun icinde snapShotsa ekleme yap. Bunun icinde iki kez ekleme yap.
                    String word = matcher.group();
                    snapShots.add(generateSnapshot(solution, index, word));
                    if (word == answerWord){ //%100 Match!
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
                    snapShots.add(generateSnapshot(solution, index, regex));
                }
            }

            knownLetters[index] = -1; //This clue is attacked and wont be attacked again. This line is what it means.

            // the string regex changed and learned over time. Let's put regex into our current solution.
            // In short, the code below updates solution[][].
            modifySolution(solution, index, regex);

        } while (!Arrays.equals(knownLetters, allAttacked));

        return snapShots;

    }

    public static CharGrid generateSnapshot(char[][] solution, int index, String regex){ //This does not modify solution and shouldn't.
        char[][] snapshot = new char[5][5];
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                snapshot[i][j] = solution[i][j];
            }
        }
        modifySolution(snapshot, index, regex);
        System.out.println("Snapshot generated:");
        printPuzzle(snapshot);
        System.out.println();
        return new CharGrid(snapshot);
    }

    public static void modifySolution(char[][] solution, int index, String regex){
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
    }

    public static void printPuzzle(char[][] puzzle){
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                System.out.print(puzzle[y][x]);
            }
            System.out.println();
        }
    }

}
