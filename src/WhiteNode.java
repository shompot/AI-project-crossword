public class WhiteNode extends GridNode{
    // variables
    private int number;
    private char letter;

    // constructors
    WhiteNode (){
        super();
        this.number = -1;
        this.letter = ' ';
    }
    WhiteNode (int x, int y){
        super(x,y);
        this.number = -1;
        this.letter = ' ';
    }
    WhiteNode (int x, int y, int number){
        super(x,y);
        this.number = number;
        this.letter = ' ';
    }
    WhiteNode (int number){
        super();
        this.number = number;
        this.letter = ' ';
    }
    WhiteNode (char letter){
        super();
        this.number = -1;
        this.letter = letter;
    }
    WhiteNode (int number, char letter){
        super();
        this.number =number;
        this.letter = letter;
    }

    // setters
    public void setLetter(char letter) { this.letter = letter; }
    public void setNumber(int number) { this.number = number; }

    // getters
    public char getLetter() { return letter; }
    public int getNumber() { return number; }

    // print
    public String toString (){
        String s = "White Node";
        if (number == 0)
            s+= " without number";
        else
            s +=" with number " + number;

        return s;
    }
}
