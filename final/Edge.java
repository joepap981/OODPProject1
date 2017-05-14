/**
 * Created by Yehwan on 5/10/2017.
 */

public class Edge implements Comparable<Edge>{
    //distance between two coordinates
    private double distance;
    private Vertex u, v;

    public Edge() {}
    public Edge(Vertex u, Vertex v, double distance) {
        this.u = u;
        this.v = v;
        this.distance = distance;
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


    public String toString () {
        return u.toString() + "->" + v.toString() + " distance: " + distance;
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

    //overwrite equals to compare edges for equality
    public boolean equals(Edge cmpEdge) {
        if (distance == cmpEdge.getDistance() && u == cmpEdge.getV() && v == cmpEdge.getU())
            return true;
        return false;
    }
}
