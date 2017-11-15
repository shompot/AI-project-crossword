public class BlackNode extends GridNode{
    // variables
    private String color;

    // constructors
    BlackNode (){
        super ();
        color = "black";
    }

    BlackNode (int x, int y){
        super (x, y);
        color = "black";
    }
    BlackNode (String color){
        super ();
        this.color = color;
    }
    BlackNode (int x, int y, String color){
        super (x, y);
        this.color = color;
    }

    // setters
    public void setColor(String color) {
        this.color = color;
    }

    // getters
    public String getColor() {
        return color;
    }

    // print
    public String toString (){
        String s = "Black Node without number";

        return s;
    }
}
