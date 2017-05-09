/**
 * Created by Yehwan on 5/10/2017.
 */

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Edge implements Comparable<Edge>{
    //distance between two coordinates
    private double weight;
    private Coordinates u, v;
    //the IDs of the connected coordinates for easier identification
    private int[] edgeID = new int[2];
    //hashcode for comparison
    private int hash;

    public Edge() {}
    public Edge(Coordinates u, Coordinates v) {
        this.u = u;
        this.v = v;
        edgeID[0] = u.getID();
        edgeID[1] = v.getID();
        weight = euclidean();
        hash = hashCode();
    }

    //calculate the euclidean distance between two points
    public double euclidean () {
        return Math.sqrt(Math.pow(u.getX() - v.getX(), 2) + Math.pow(u.getY() - v.getY(), 2));
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public int[] getEdgeID () {
        return edgeID;
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

    //overwrite compareTo to compare weight of Edge for sort
    public int compareTo(Edge edge) {
        if (this.weight > edge.getWeight())
            return 1;
        else if(this.weight == edge.getWeight())
            return 0;
        else
            return -1;
    }
}
