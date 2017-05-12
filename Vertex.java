/**
 * Created by Yehwan on 5/9/2017.
 */
public class Vertex {
    //Coordinates x and y
    private int x, y;
    private int id;
    private int hash;

    public Vertex() {
    }
    public Vertex(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.hash = hashCode();
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    public int hashCode() {
        int hash = 1;
        hash = hash* 17 + x;
        hash = hash* 31 + y;
        return hash;
    }
    public String toString(){
        return "("+Integer.toString(x) + "," + Integer.toString(y)+")";
    }
}
