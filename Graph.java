import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yehwan on 5/10/2017.
 */
public class Graph {
    Coordinates[] coordinates;
    List edges = new LinkedList<Edge>();
    public Graph (){};
    public Graph (Coordinates[] coordinates) {
        this.coordinates = coordinates;
        makeGraph();
    }

    //add all possible edges
    public void makeGraph () {
        for(int i=0; i < coordinates.length-1; i++) {
            for(int j=0; j < coordinates.length-1;j++){
                if(coordinates[i].getID() == coordinates[j].getID())
                    continue;
                //create new Edge
                Edge newEdge = new Edge(coordinates[i], coordinates[j]);
                //add edge to List only if the distance is less than 50
                if(newEdge.getWeight() < 50)
                    edges.add(newEdge);
            }
        }
    }

    public void sortList () {
        Collections.sort(edges);
    }

    public void printEdges() {
        for(int i=0; i < edges.size(); i++){
            Edge thisEdge = (Edge)edges.get(i);
            int[] ID = thisEdge.getEdgeID();
            System.out.println(ID[0] + "," +ID[1] +"length: " + thisEdge.getWeight());
        }
    }
}
