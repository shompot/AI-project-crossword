public class GridNode {
    // variables
    private int x; // horizontal distance from left-most side, starts from 0
    private int y; // vertical distance from top, starts from 0

    // constructors
    GridNode (){
        this.x = 0;
        this.y = 0;
    }
    GridNode (int x, int y){
        this.x = x;
        this.y = y;
    }

    // setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    // gettters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}
