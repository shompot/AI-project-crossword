import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.sqrt;


public class Crossword {

    // VARIABLES
    private int side = 5;
    private GridNode[] grid;
    private int[] colors;
    private int[] numbers;
    private String solutionAddress;
    private char[] solutionArr;

    private ArrayList<String> acrossHints;
    private ArrayList<String> downHints;
    private ArrayList<String> acrossHintsOnly;
    private ArrayList<String> downHintsOnly;
    private ArrayList<Integer> acrossHintsNums;
    private ArrayList<Integer> downHintsNums;
    private ArrayList<String> acrossSolution;
    private ArrayList<String> downSolution;

    // CONSTRUCTORS
    Crossword(){
        this.side = 5;
        this.grid = new GridNode[side*side];
    }

    Crossword(int side){
        this.side = side;
        this.grid = new GridNode[side*side];
    }
    Crossword(GridNode[] grid){
        this.side = (int)sqrt((double)(grid.length));
        this.grid = grid;
    }


    // SETTERS
    //public void setGrid(GridNode[] grid) { this.grid = grid; }
    public void setSide(int side) { this.side = side; }
   // public void setColors(int[] colors) { this.colors = colors; }
    //public void setNumbers(int[] numbers) { this.numbers = numbers; }

    // GETTERS
    public GridNode[] getGrid() { return grid; }
    public int getSide() { return side; }
    public int[] getColors() { return colors; }
    public int[] getNumbers() { return numbers; }
    public char[] getSolutionArr() { return solutionArr; }

    public ArrayList<String> getAcrossHints() { return acrossHints; }
    public ArrayList<String> getDownHints() { return downHints; }

    public ArrayList<String> getAcrossHintsOnly() { return acrossHintsOnly; }
    public ArrayList<String> getDownHintsOnly() { return downHintsOnly; }

    public ArrayList<Integer> getAcrossHintsNums() { return acrossHintsNums; }
    public ArrayList<Integer> getDownHintsNums() { return downHintsNums; }
    public ArrayList<String> getAcrossSolution() { return acrossSolution; }
    public ArrayList<String> getDownSolution() { return downSolution; }

    // METHODS
    // Read file methods
    public void readGridFromUrl  () throws IOException{
        String url = "https://www.nytimes.com/crosswords/game/mini";
        solutionAddress = "crosswords/Solution December 20, 2017.html";
        Document documents = Jsoup.connect(url).get();
        String html  = documents.html();
        readCrossword(html);
    }

    public void readGridFromFile (String fileName) throws IOException{
        String html = "";
        solutionAddress = fileName.substring(0,11) + "Solution " + fileName.substring(11);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                html += sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        readCrossword(html);
    }


    public void readCrossword (String html){

        readGrid(html);
        readHints (html);
        readSolution ();
    }

    // read grid methods
    public void readGrid (String html){
        String text = html;

        text = this.extractGridPart(text);
        this.colors = this.getColors(text);
        this.numbers = this.getNumbers(text);

        for (int i = 0; i < this.side*this.side; i++){
            // if colors is black
            if (colors[i] == 1){
                this.grid[i] = new BlackNode(i/this.side, i%this.side);
            }
            // if colors is white
            else if (colors[i] == 0) {
                this.grid[i] = new WhiteNode(i / this.side, i % this.side, numbers[i]);
            }
        }

    }


    public String extractGridPart (String text){
        String sStart = "<g data-group=\"cells\"";
        String sEnd = "<g data-group=\"grid\"";

        //System.out.println(text);

        int indexStart = text.indexOf(sStart) + sStart.length();
        int indexEnd = text.indexOf(sEnd) - 1;

        //System.out.println("Start at " + indexStart + "\nEnd at " + indexEnd);

        // extract the part of that has data on the grid
        text = text.substring(indexStart, indexEnd);

        return text;
    }


    public int [] getColors (String text) {
        // 0 represents white, 1 represents black
        int size = this.side*this.side;
        int[] colors = new int[size];

        for (int i =0; i < size; i++){

            int indexStart = text.indexOf("fill") + 6;
            int indexEnd = indexStart;

            while (text.charAt(indexEnd) != '\"'){
                indexEnd++;
            }
            String color = text.substring(indexStart, indexEnd);


            if (color.equals("none"))
                colors[i] = 0;
            else
                colors[i] = 1;

            //System.out.println (color + ",");

            text = text.substring(indexEnd);
        }
        return  colors;
    }

    public int [] getNumbers (String html) {
        int size = this.side*this.side;
        int [] numbers = new int[size];

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("g");

        for (int i =0; i < elements.size() && i <size ; i++){

            String text = elements.get(i).text();
            int l = text.length()-1;
            while ( !text.isEmpty() && (Character.isLetter(text.charAt(l)))){
                text = text.substring(0, l);
                l--;
            }
            //System.out.println ("Text is " + text );
            if (!text.isEmpty())
                numbers[i] = Integer.parseInt(text);
            else
                numbers[i] = 0;


        }
        return numbers;
    }

    // read hints methods
    public void readHints (String html){

        String acrossHTML;
        String downHTML;

        //System.out.println ("Reading hints");

        html = this.extractHintsHTML(html);
        //System.out.println ("Hints HTML is:\n" + html);

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("ClueList-wrapper--3m-kd");

        acrossHTML = elements.get(0).toString();
        //System.out.println ("\nAcross\n" + elements.toString() + "\n");

        downHTML = elements.get(1).toString();
        //System.out.println ("\nDown\n" + elements.toString() + "\n");

        acrossHints = extractHints(acrossHTML);
        acrossHintsNums = extractHintsNums(acrossHTML);
        acrossHintsOnly = extractOnlyHints(acrossHTML);

        downHints = extractHints(downHTML);
        downHintsNums = extractHintsNums(downHTML);
        downHintsOnly = extractOnlyHints(downHTML);
    }
    public String extractHintsHTML (String html){

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("Layout-clueLists--10_Xl");

        String text = elements.toString();
        return text;
    }
    public ArrayList <String> extractHints(String html){

        ArrayList <String> hints = new ArrayList<String>();
        Document document = Jsoup.parse(html);

        Elements numsElements = document.getElementsByClass("Clue-label--2IdMY");
        Elements hintsElements = document.getElementsByClass("Clue-text--3lZl7");

        for (int i= 0; i< numsElements.size() && i < hintsElements.size(); i++) {
            hints.add( numsElements.get(i).text() + ". " + hintsElements.get(i).text());
            //System.out.println(hints.get(hints.size()-1));
        }

        return hints;
    }

    public ArrayList <String> extractOnlyHints(String html){

        ArrayList <String> hints = new ArrayList<String>();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("Clue-text--3lZl7");
        for (int i= 0; i< elements.size(); i++) {
            hints.add(elements.get(i).text());
            //System.out.println(hints.get(hints.size()-1));
        }

        return hints;
    }

    public ArrayList <Integer> extractHintsNums(String html){

        ArrayList <Integer> hintsNums = new ArrayList<Integer>();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("Clue-label--2IdMY");
        for (int i= 0; i< elements.size(); i++) {
            hintsNums.add(Integer.parseInt(elements.get(i).text()));
            //System.out.println(hintsNums.get(hintsNums.size()-1));
        }

        return hintsNums;
    }

    // READ SOLUTION
    public void readSolution (){
        readSolutionArray();
        readAcrossSolution();
        readDownSolution();
    }

    public void readSolutionArray (){
        String html = "";
        try (BufferedReader br = new BufferedReader(new FileReader(solutionAddress))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                html += sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // extract grid part
        html = this.extractGridPart(html);

        int size = this.side*this.side;
        solutionArr = new char[size];

        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByTag("g");

        for (int i =0; i < elements.size() && i <size ; i++){
            char c = ' ';
            String text = elements.get(i).text();
            int l = text.length()-1;
            if ( !text.isEmpty() && (Character.isLetter(text.charAt(l)))){
                if (Character.isDigit(text.charAt(0)))
                    c = text.charAt(l);
                else
                    c = text.charAt(0);
                l--;
            }
            //System.out.println ("Text is " + text );
            if (!text.isEmpty())
                solutionArr[i] = c;
            else
                solutionArr[i] = ' ';
        }
    }

    public void readAcrossSolution(){
        //System.out.println("Across:");
        acrossSolution = new ArrayList<String>();
        String word;
        for (int i = 0; i < side; i ++){
            word = "";
            int j = 0;
            while (colors[i*5+j]==1 && j < side) j++;
            while (j < side) {
                word += solutionArr[i*5+j];
                j++;
            }
            acrossSolution.add(word);
            //System.out.println(word);
        }
    }
    public void readDownSolution(){
        //System.out.println("Down:");
        downSolution = new ArrayList<String>();
        String word;
        for (int j = 0; j < side; j ++){
            word = "";
            int i = 0;
            while (colors[i*5+j]==1 && i < side) i++;
            while (i < side) {
                word += solutionArr[i*5+j];
                i++;
            }
            downSolution.add(word);
            //System.out.println(word);
        }
    }
    // PRINT
    public String toString (){
        String s = "";

        for ( int i = 0; i < side*side; i++){
            if ( i%5 == 0)
                s += "\n";
            s += this.grid[i].toString()+", ";
        }
        return s;
    }


    // MAIN
    public static void main(String[] args) throws IOException{
        Crossword g = new Crossword();

        g.readGridFromFile("crosswords/December 12, 2017.html");
        //g.readGridFromUrl();
        //System.out.println(g.toString());

        int[] colors = g.getColors();
        System.out.println ("Colors");
        for (int i=0; i < g.getSide(); i ++){
            for (int j=0; j < g.getSide(); j++)
                System.out.print( colors [i*5 + j] + " ");
            System.out.println ();
        }

        int[] numbers = g.getNumbers();
        System.out.println ("Numbers");
        for (int i=0; i < g.getSide(); i ++){
            for (int j=0; j < g.getSide(); j++)
                System.out.print( numbers [i*5 + j] + " ");
            System.out.println ();
        }

        char[] solution = g.getSolutionArr();
        System.out.println ("Solution Arr");
        for (int i=0; i < g.getSide(); i ++){
            for (int j=0; j < g.getSide(); j++)
                System.out.print( solution [i*5 + j] + " ");
            System.out.println ();
        }
        System.out.println("Across:");
        for (int i = 0; i < g.getAcrossHints().size(); i++){
            System.out.println(  g.getAcrossHints().get(i));
        }
        System.out.println("Down:");
        for (int i = 0; i < g.getDownHints().size(); i++){
            System.out.println(  g.getDownHints().get(i));
        }
    }

}
