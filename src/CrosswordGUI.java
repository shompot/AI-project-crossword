import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shiha on 11/11/2017.
 */

public class CrosswordGUI {

    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static final int NUM_OF_HEADERS_TO_SEARCH = 200;
    //variables, constants, objects
    //crossword fetching options
    public static final String[] options = { "Today", "Oct 24, 2017", "Nov 8, 2017", "Nov 14, 2017", "Nov 15, 2017",
                                                "Dec 12, 2017", "Dec 13, 2017"};
    //Crossword Panel
    private JPanel CWPanel;
    //Crossword Grid
    //Grid Panels that have the buttons and text areas
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private JPanel box4;
    private JPanel box5;
    private JPanel box6;
    private JPanel box7;
    private JPanel box8;
    private JPanel box9;
    private JPanel box10;
    private JPanel box11;
    private JPanel box12;
    private JPanel box13;
    private JPanel box14;
    private JPanel box15;
    private JPanel box16;
    private JPanel box17;
    private JPanel box18;
    private JPanel box19;
    private JPanel box20;
    private JPanel box21;
    private JPanel box22;
    private JPanel box23;
    private JPanel box24;
    private JPanel box25;
    //Disabled JButtons that "hold" the numbers of the white squares
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    //Enabled JTextAreas that are within the white squares within the crossword grids
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private JTextArea textArea6;
    private JTextArea textArea7;
    private JTextArea textArea8;
    private JTextArea textArea9;
    private JTextArea textArea10;
    private JTextArea textArea11;
    private JTextArea textArea12;
    private JTextArea textArea13;
    private JTextArea textArea14;
    private JTextArea textArea15;
    private JTextArea textArea16;
    private JTextArea textArea17;
    private JTextArea textArea18;
    private JTextArea textArea19;
    private JTextArea textArea20;
    private JTextArea textArea21;
    private JTextArea textArea22;
    private JTextArea textArea23;
    private JTextArea textArea24;
    private JTextArea textArea25;
    //Hints
    //Disabled JTextAreas where hints are listed
    private JTextArea downHints;
    private JTextArea acrossHints;
    //Log
    //JTextArea for the log that tracks the software development
    private JTextArea log;
    //Prompt Buttons
    //JButtons to prompt getting the grid and the hints
    private JButton getCrossword;
    private JButton getHints;

    //ArrayLists and Arrays
    private ArrayList<String> acrossList = new ArrayList<String>();
    private ArrayList<String> downList = new ArrayList<String>();
    private ArrayList<JPanel> panelList = new ArrayList<JPanel>();
    private ArrayList<JButton> buttonlist = new ArrayList<JButton>();;
    private ArrayList<JTextArea> textlist = new ArrayList<JTextArea>();;
    public int[] colors;
    public int[] numbers;
    private char[] solution;
    private ArrayList<String> acrossSolution;
    private ArrayList<String> downSolution;
    //Color
    private Color dark = new Color(123,86,78);

    //Constructor
    public CrosswordGUI()
    {
        //Display Starting Project in the log
        log.append( "\n Welcome! Starting project.");
        //To limit the text area to only 1 letter
        //uses LimitLines Document class and DocumentSize Filter
        AbstractDocument doc1=(AbstractDocument)textArea1.getDocument();
        doc1.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc2=(AbstractDocument)textArea2.getDocument();
        doc2.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc3=(AbstractDocument)textArea3.getDocument();
        doc3.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc4=(AbstractDocument)textArea4.getDocument();
        doc4.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc5=(AbstractDocument)textArea5.getDocument();
        doc5.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc6=(AbstractDocument)textArea6.getDocument();
        doc6.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc7=(AbstractDocument)textArea7.getDocument();
        doc7.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc8=(AbstractDocument)textArea8.getDocument();
        doc8.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc9=(AbstractDocument)textArea9.getDocument();
        doc9.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc10=(AbstractDocument)textArea10.getDocument();
        doc10.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc11=(AbstractDocument)textArea11.getDocument();
        doc11.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc12=(AbstractDocument)textArea12.getDocument();
        doc12.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc13=(AbstractDocument)textArea13.getDocument();
        doc13.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc14=(AbstractDocument)textArea14.getDocument();
        doc14.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc15=(AbstractDocument)textArea15.getDocument();
        doc15.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc16=(AbstractDocument)textArea16.getDocument();
        doc16.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc17=(AbstractDocument)textArea17.getDocument();
        doc17.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc18=(AbstractDocument)textArea18.getDocument();
        doc18.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc19=(AbstractDocument)textArea19.getDocument();
        doc19.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc20=(AbstractDocument)textArea20.getDocument();
        doc20.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc21=(AbstractDocument)textArea21.getDocument();
        doc21.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc22=(AbstractDocument)textArea22.getDocument();
        doc22.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc23=(AbstractDocument)textArea23.getDocument();
        doc23.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc24=(AbstractDocument)textArea24.getDocument();
        doc24.setDocumentFilter(new DocumentSizeFilter(1));
        AbstractDocument doc25=(AbstractDocument)textArea25.getDocument();
        doc25.setDocumentFilter(new DocumentSizeFilter(1));
        //action listener for the get crossword button
        getCrossword.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //call fillGrid() method
                try {
                    fillGrid();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //get hints button action listener
        getHints.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //call fillHint() method
                fillHints();
            }
        });
    }

    //Getters and Setters
    //GETTERS
    //getButtonList: fills list of buttons that hold numbers
    public ArrayList<JButton> getButtonlist()
    {
        buttonlist.add( button1);
        buttonlist.add( button2);
        buttonlist.add( button3);
        buttonlist.add( button4);
        buttonlist.add( button5);
        buttonlist.add( button6);
        buttonlist.add( button7);
        buttonlist.add( button8);
        buttonlist.add( button9);
        buttonlist.add( button10);
        buttonlist.add( button11);
        buttonlist.add( button12);
        buttonlist.add( button13);
        buttonlist.add( button14);
        buttonlist.add( button15);
        buttonlist.add( button16);
        buttonlist.add( button17);
        buttonlist.add( button18);
        buttonlist.add( button19);
        buttonlist.add( button20);
        buttonlist.add( button21);
        buttonlist.add( button22);
        buttonlist.add( button23);
        buttonlist.add( button24);
        buttonlist.add( button25);
        return buttonlist;
    }
    //get text list: fills list of text areas that are editable squares
    public ArrayList<JTextArea> getTextlist() {
        textlist.add(textArea1);
        textlist.add(textArea2);
        textlist.add(textArea3);
        textlist.add(textArea4);
        textlist.add(textArea5);
        textlist.add(textArea6);
        textlist.add(textArea7);
        textlist.add(textArea8);
        textlist.add(textArea9);
        textlist.add(textArea10);
        textlist.add(textArea11);
        textlist.add(textArea12);
        textlist.add(textArea13);
        textlist.add(textArea14);
        textlist.add(textArea15);
        textlist.add(textArea16);
        textlist.add(textArea17);
        textlist.add(textArea18);
        textlist.add(textArea19);
        textlist.add(textArea20);
        textlist.add(textArea21);
        textlist.add(textArea22);
        textlist.add(textArea23);
        textlist.add(textArea24);
        textlist.add(textArea25);
        return textlist;
    }
    //getpanellist: fills list with panels that hold the editable squares and their corresponding numbers
    public ArrayList<JPanel> getPanellist() {
        panelList.add(box1);
        panelList.add(box2);
        panelList.add(box3);
        panelList.add(box4);
        panelList.add(box5);
        panelList.add(box6);
        panelList.add(box7);
        panelList.add(box8);
        panelList.add(box9);
        panelList.add(box10);
        panelList.add(box11);
        panelList.add(box12);
        panelList.add(box13);
        panelList.add(box14);
        panelList.add(box15);
        panelList.add(box16);
        panelList.add(box17);
        panelList.add(box18);
        panelList.add(box19);
        panelList.add(box20);
        panelList.add(box21);
        panelList.add(box22);
        panelList.add(box23);
        panelList.add(box24);
        panelList.add(box25);
        return panelList;
    }
    public int[] getColors()
    {
        return this.colors;
    }
    public int[] getNumbers()
    {
        return this.numbers;
    }
    public JTextArea getDownHints()
    {
        return this.downHints;
    }
    public JTextArea getAcrossHints()
    {
        return this.acrossHints;
    }
    public JTextArea getLog()
    {
        return this.log;
    }
    public ArrayList<String> getAcrossList()
    {
        return this.acrossList;
    }
    public ArrayList<String> getDownList()
    {
        return this.downList;
    }
    public ArrayList<String> getAcrossSolution() { return acrossSolution; }
    public ArrayList<String> getDownSolution() { return downSolution; }
    public char[] getSolution () { return this.solution; }
    //SETTERS
    public void setColors( int[] colors)
    {
        this.colors = colors;
    }
    public void setNumbers( int[] numbers)
    {
        this.numbers = numbers;
    }
    public void setAcrossList( ArrayList<String> acrossList)
    {
        this.acrossList = acrossList;
    }
    public void setDownList( ArrayList<String> downList)
    {
        this.downList = downList;
    }
    public void setSolution (char[] solution) {this.solution = solution;}
    public void setAcrossSolution(ArrayList<String> acrossSolution) { this.acrossSolution = acrossSolution; }
    public void setDownSolution(ArrayList<String> downSolution) { this.downSolution = downSolution; }


    //printPuzzle method-- for TESTING
    public static void printPuzzle(char[][] puzzle){
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                System.out.print(puzzle[y][x]);
            }
            System.out.println();
        }
    }

    //fillGrid method() fills the crossword Grid-- FOR GUI
    public void fillGrid() throws IOException {
        //display in log that retrieval is initiated
        getLog().append( "\nRetrieve crossword...");
        //need array of colors that we got from the html from website
        int[] colorsArr;
        colorsArr = this.getColors();
        //need array of numbers that we got from the html from website
        int[] numbersArr;
        numbersArr = this.getNumbers();
        //lop in the arraylists and copy the puzzle int ours accordingly
        for( int i = 0; i < 25; i++)
        {
            if( colorsArr[i] == 1)
            {
                //if the array has value of 1 it means it is black so copy into ours as black
                getPanellist().get(i).setBackground(dark);
                getTextlist().get(i).setBackground( dark);
                getButtonlist().get(i).setBackground( dark);
            }
            if( numbersArr[i] != 0)
            {
                //..if value is 0, it means it is white and fillable so the text should be empty and the
                //number should appear
                getButtonlist().get(i).setText("" + numbersArr[i]);
            }
        }
        getLog().append( "\nCrossword retrieval complete!");

        //Tam burada snapshots listesini olustur. Sonra CheckWords'e ver.
        //What do I need?
        //clues, in my order
        //across order is the same. I just need to find out the down order.
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
        getLog().append( "\nNow, looking for answers online. Patience is key here.");

        getLog().append( "\nWe've got them all! Now watch as FaglAIno tries to solve the puzzle...");
        CheckWords checkWordsInstance = new CheckWords(this, clues, charCounts, solutions);
        Thread t = new Thread(checkWordsInstance);
        t.start();
    }

    public static ArrayList<ArrayList<String>> getWordsForClues (ArrayList<String> clues, int[] charCounts, CrosswordGUI cw) throws IOException{
        ArrayList<ArrayList<String>> wordListList = new ArrayList<ArrayList<String>>();
        for (int i=0; i<clues.size(); i++){
            ArrayList<String> wordList = new ArrayList<String>();
            cw.getLog().append( "\nChecking Google for clue #" + i + "...");
            try {
                wordList.addAll(getWordsForClue(clues.get(i), charCounts[i]));
            } catch (Exception e){
                System.out.println("GOOGLE FAILED!");
            }
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
            System.out.println("GOOGLE FAILED");
        }

        return wordList;
    }

    //fillHints method- copies the hints
    public void fillHints()
    {
        String across = "";
        String down = "";
        getLog().append( "\nRetrieve hints...");
        for( int i = 0; i < this.acrossList.size(); i++)
        {
            across = across + this.acrossList.get(i) + "\n";
        }
        getAcrossHints().setText( across); //across hints copied
        getLog().append( "\nGot Across Hints!");
        for( int i = 0; i < this.downList.size(); i++)
        {
            down = down + this.downList.get(i) + "\n";
        }
        getDownHints().setText( down); //down hints copied
        getLog().append( "\nGot Down Hints!");
        getLog().append( "\nHints retrieval complete!");
    }



    class CheckWords implements Runnable
    {
        public ArrayList<CharGrid> snapshots;
        public CrosswordGUI cw;
        public ArrayList<String> clues;
        int[] charCounts;
        char[][] solution;

        CheckWords(CrosswordGUI cw, ArrayList<String> clues, int[] charCounts, char[][] solution){
            this.cw = cw;
            this.clues = clues;
            this.charCounts = charCounts;
            this.solution = solution;
        }
        public void run()
        {
            try {
                check();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void check() throws IOException {

            ArrayList<ArrayList<String>> wordListList = getWordsForClues(clues, charCounts, cw);
            this.snapshots = solve(solution, wordListList);

            getLog().append( "\nBuraya tum interneti bitirmeden gelmemesi lazim. Gelirse bu programi idam edin.");

            for (int k=0; k<this.snapshots.size(); k++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Showing this snapshot:");
                char[][] charGrid = this.snapshots.get(k).p;
                printPuzzle(charGrid);
                for (int i=0; i<5; i++){
                    for(int j=0; j<5; j++){
                        if (charGrid[j][i] == '@'){
                            //Do nothing
                        } else if (charGrid[j][i] == '.'){
                            textlist.get(i*5+j).setText(" ");
                        } else {

                            textlist.get(i*5+j).setText(Character.toString(Character.toUpperCase(charGrid[j][i])));
                        }
                    }
                }
            }

        }
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

    //Run this main method!
    public static void main(String[] args) throws IOException
    {
        SolvePuzzle solve = new SolvePuzzle();
        Crossword g = solve.getCrossword();
        CrosswordGUI crossword = new CrosswordGUI();
        //Pop up frame asking which crossword to solve
        JFrame frame0 = new JFrame("Which Crossword?");
        String option = (String) JOptionPane.showInputDialog(frame0,
                "Which crossword would you like to solve?",
                "Crossword to Solve",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]  );
        if( option == "Today") { System.out.println("Retrieving today's puzzle...Please wait"); g.readGridFromUrl();}
        else if( option == "Oct 24, 2017"){ System.out.println("Retrieving saved puzzle...Please wait");
        g.readGridFromFile("crosswords/October 24, 2017.html");}
        else if( option == "Nov 8, 2017"){ System.out.println("Retrieving saved puzzle...Please wait");
        g.readGridFromFile("crosswords/November 8, 2017.html");}
        else if( option == "Nov 14, 2017") { System.out.println("Retrieving saved puzzle...Please wait");
        g.readGridFromFile("crosswords/November 14, 2017.html");}
        else if( option == "Nov 15, 2017") { System.out.println("Retrieving saved puzzle...Please wait");
        g.readGridFromFile("crosswords/November 15, 2017.html");}
        else if( option == "Dec 12, 2017") { System.out.println("Retrieving saved puzzle...Please wait");
        g.readGridFromFile("crosswords/December 12, 2017.html");}
        else if( option == "Dec 13, 2017") { System.out.println("Retrieving saved puzzle...Please wait");
        g.readGridFromFile("crosswords/December 13, 2017.html");}
        else if( option == "Dec 20, 2017") { System.out.println("Retrieving saved puzzle...Please wait");
        g.readGridFromFile("crosswords/December 20, 2017.html");}
        else { crossword.getLog().append( "Cannot display puzzle"); }

        JFrame frame;
        frame = new JFrame("CS461 faglAIno Crossword");
        crossword.setColors(g.getColors());
        crossword.setNumbers( g.getNumbers());
        crossword.setAcrossList( g.getAcrossHints());
        crossword.setDownList( g.getDownHints());
        crossword.setSolution (g.getSolutionArr());
        crossword.setAcrossSolution(g.getAcrossSolution());
        crossword.setDownSolution(g.getDownSolution());

        frame.setContentPane(crossword.CWPanel);
        frame.setLocation(400,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        JFrame solveframe;
        solveframe = new JFrame("Solve");
        solveframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solveframe.setContentPane( solve.solvePanel);
        solveframe.setLocation(20,150);
        solveframe.pack();
        solveframe.setVisible(true);

        SolutionGUI solution = new SolutionGUI( g.getColors(), g.getNumbers(), g.getSolutionArr());
        JFrame solutionframe;
        solutionframe = new JFrame("Solution");
        solutionframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solutionframe.setContentPane( solution.CWPanel);
        solutionframe.setLocation(1020,150);
        solutionframe.pack();
        solutionframe.setVisible(true);

    }
}
