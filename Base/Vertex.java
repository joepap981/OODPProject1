import java.util.ArrayList;

public class Vertex {
    //Coordinates x and y
    private int x, y;
    private int id;
    private int hash;
    
    //Digikstra instances
	private double distance;
	private int path;
	private boolean visited;

    public Vertex() {}
    
    public Vertex(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
        //Digikstra
        distance=Integer.MAX_VALUE;
        visited=false;
    }
    
    //common method
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
        return "(Vertex: "+id+") Distance: "+ getDistance();
    }
    
    //Digikstra method
	public double getDistance(){
		return distance;
	}

	public void setDistance(double d){
		this.distance=d;
	}
	
	public boolean isVisited(){
		return visited;
	}
	
	public void setVisited(boolean visited){
		this.visited=visited;
	}
	
	public void setPath(int path){
		this.path=path;
	}
	
	public int getPath(){
		return path;
	}
}
