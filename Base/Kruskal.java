import java.util.LinkedList;

/**
 * Created by Yehwan on 5/14/2017.
 */
public class Kruskal {
    //adjacency list for graph (used for DFS
    LinkedList<Vertex>[] adjList;
    LinkedList<Edge> edges;

    public Kruskal (Vertex[] vertices, EdgeList edgeList){
        adjList = new LinkedList[vertices.length];
        edges = new LinkedList<>();

    }

    public void addVertices (Vertex[] vertices) {
        for(int i=0; i < vertices.length;i++){

        }
    }
}
