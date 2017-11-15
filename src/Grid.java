import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

import static java.lang.Math.sqrt;
import static javax.script.ScriptEngine.FILENAME;


public class Grid {
    // variables
    private int side = 5;
    private GridNode[] grid;
    private int[] colors;
    private int[] numbers;

    // constructors
    Grid (){
        this.side = 5;
        this.grid = new GridNode[side*side];
    }

    Grid (int side){
        this.side = side;
        this.grid = new GridNode[side*side];
    }
    Grid (GridNode[] grid){
        this.side = (int)sqrt((double)(grid.length));
        this.grid = grid;
    }

    // setters
    public void setGrid(GridNode[] grid) { this.grid = grid; }
    public void setSide(int side) { this.side = side; }
    public void setColors(int[] colors) { this.colors = colors; }
    public void setNumbers(int[] numbers) { this.numbers = numbers; }

    // getters
    public GridNode[] getGrid() { return grid; }
    public int getSide() { return side; }
    public int[] getColors() { return colors; }
    public int[] getNumbers() { return numbers; }

    // computing methods
    public void readGridFromUrl  () throws IOException{
        String url = "https://www.nytimes.com/crosswords/game/mini";
        Document documents = Jsoup.connect(url).get();
        String html  = documents.html();
        readGrid(html);
    }

    public void readGridFromFile (String fileName) throws IOException{
        String html = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                html += sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        readGrid(html);
    }

    public void readGrid (String text){

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
    

    // print
    public String toString (){
        String s = "";

        for ( int i = 0; i < side*side; i++){
            if ( i%5 == 0)
                s += "\n";
            s += this.grid[i].toString()+", ";
        }
        return s;
    }

    // main method
    public static void main(String[] args) throws IOException{
        Grid g = new Grid();

        /*String text = g.readTxtFile("txt/crossword1.txt");
        text = g.extractGridPart(text);
        //System.out.println(text);

        System.out.print("\nPrint Colors:\n");

        int [] colors = g.getColors(text);

        int index = 0;
        for (int i = 0; i < 5; i ++){
            for (int j = 0; j < 5; j++)
                System.out.print(colors[index++] + " ");
            System.out.print("\n");
        }

        System.out.print("\n\nPrint Numbers:\n");

        int [] numbers = g.getNumbers(text);

        index = 0;
        for (int i = 0; i < 5; i ++){
            for (int j = 0; j < 5; j++)
                System.out.print(numbers[index++] + " ");
            System.out.print("\n");
        }
        */

        g.readGridFromFile("crosswords/November 14, 2017.html");
        System.out.println(g.toString());
        g.readGridFromUrl();
        System.out.println(g.toString());
        g.readGridFromFile("crosswords/November 8, 2017.html");
        System.out.println(g.toString());
    }

}
