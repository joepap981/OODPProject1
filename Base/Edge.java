/**
 * Created by Yehwan on 5/10/2017.
 */

public class Edge implements Comparable<Edge>{
    //distance between two coordinates
    private double distance;
    private Vertex u, v;
    //the IDs of the connected coordinates for easier identification
    private int[] edgeID = new int[2];
    //hashcode for comparison
    private int hash;

    public Edge() {}
    public Edge(Vertex u, Vertex v, double distance) {
        this.u = u;
        this.v = v;
        edgeID[0] = u.getID();
        edgeID[1] = v.getID();
        this.distance = distance;
        hash = hashCode();
    }


    public Vertex getU () {return this.u;}
    public Vertex getV () {return this.v;}
    public void setUV(Vertex u, Vertex v) {this.u = u;this.v = v;}

    public double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setEdgeID (int[] edgeID){ this.edgeID= edgeID;}
    public int[] getEdgeID () {
        return edgeID;
    }

    public String toString () {
        return u.toString() + "->" + v.toString() + " distance: " + distance;
    }

    //create hashcode ID for comparison
    //design to make sure that u(x,y) v(y,x) are recognized as same edge
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 17 + edgeID[0];
        hashCode = hashCode * 31 + edgeID[1];
        return hashCode;
    }

    public int getHash() {
        return hash;
    }

    //overwrite compareTo to compare distance of Edge for sort
    public int compareTo(Edge edge) {
        if (this.distance > edge.getDistance())
            return 1;
        else if(this.distance == edge.getDistance() && this.u == edge.getV() && this.v == edge.getU())
            return 0;
        else
            return -1;
    }

    public boolean equals(Edge cmpEdge) {
        if(distance == cmpEdge.getDistance() && u == cmpEdge.getU() && v == cmpEdge.getV())
            return true;
        return false;
    }
}
