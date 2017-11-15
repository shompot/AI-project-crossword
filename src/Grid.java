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
    public void readGrid (String fileName){
        String text = this.readTxtFile(fileName);

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

    public int [] getNumbers (String text) {
        int size = this.side*this.side;
        int [] numbers = new int[size];


        for (int i =0; i < size; i++){

            text = text.substring(text.indexOf("<g"));
            int indexEnd = text.indexOf("</g>");
            String str = text.substring(0, indexEnd);

            int index = str.indexOf("</text>") - 1;
            //System.out.println (index);
            if  (index >= 0 && index <str.length() && str.charAt(index)!= '>'){
                int t = str.charAt(index) - '0';
                numbers[i] = t;
                //System.out.print ("Char " + str.charAt(index) + ", saved as " + numbers[i] + "\n");
            }
            else {
                numbers[i] = 0;
                //System.out.print ("No char, saved as " + numbers[i] + "\n");
            }
            text = text.substring(indexEnd);

            //System.out.println (numbers[i] + ",");

        }
        return numbers;
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

    public String readTxtFile  (String fileName){
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                text += sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
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
    public static void main(String[] args){
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

        g.readGrid("crosswords/November 14, 2017.html");
        System.out.print(g.toString());
        int [] colors = g.getColors();

        int index = 0;
        for (int i = 0; i < 5; i ++){
            for (int j = 0; j < 5; j++)
                System.out.print(colors[index++] + " ");
            System.out.print("\n");
        }
    }

}
