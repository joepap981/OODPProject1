import com.sun.org.apache.bcel.internal.generic.LNEG;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Yehwan on 5/14/2017.
 */
public class Kruskal {
    //adjacency list for graph (used for DFS
    private LinkedList<Vertex>[] adjList;
    //linked list holding all edges sorted by increasing distance
    private LinkedList<Edge> edges;
    //the path of minimal spanning tree
    private LinkedList<Edge> path;

    //constructor
    public Kruskal (LinkedList<Vertex>[] vertices, EdgeList edgeList){
        //initialize LinkedLists
        adjList = vertices;
        edges = new LinkedList<>();
        path = new LinkedList<>();

        //add edges to LinkedList<Edge> edges
        addEdges(edgeList);

        //make Kruskal minimal spanning tree
        makePath();

        //printAdjList();
    }

    //calculates the path of minimal spanning tree using Kruskal
    public void makePath () {
        Edge newEdge;
        //x, vertex the edge spans from
        //y, vertex the edge connects to
        Vertex x,y;
        Iterator<Edge> itr = edges.listIterator();

        while(itr.hasNext()){
            newEdge = itr.next();
            x= newEdge.getU();
            y = newEdge.getV();

            //add vertex y to vertex x in adjacency list
            //remember that IDs are index +1
            adjList[x.getID()-1].add(y);
            path.add(newEdge);
            //if adding the edge makes the graph cyclic, remove the vertex from adjList and remove the edge from path
            if(pathContains(newEdge) || isCyclic()) {
                adjList[x.getID()-1].removeLast();
                path.removeLast();
            }
        }
    }

    //add edges to LinkedList<Edge> edges and sorts them in increasing order
    public void addEdges (EdgeList edgeList) {
        Iterator itr;
        Vertex vertex1, vertex2;
        for(int i=0; i < 50; i++){
            for(int j=0; j < 50; j++){
                //find the Vertex instance by their ID
                vertex1 = findVertByID(i);
                vertex2 = findVertByID(j);

                //create a new Edge with the IDs of the two vertices and the distance between them
                Edge newEdge = new Edge(vertex1,vertex2, edgeList.getDistance(i, j));

                //add edge to LinkedList<Edge> edges if it is greater than 0 and less than 50
                if(newEdge.getDistance() >0 && newEdge.getDistance() < 50)
                    edges.add(newEdge);
            }
        }
        Collections.sort(edges);
    }

    //find vertex by id
    public Vertex findVertByID (int id) {
        return adjList[id].getFirst();
    }

    public boolean pathContains(Edge newEdge) {
        Iterator<Edge> itr = path.listIterator();
        while(itr.hasNext()){
            Edge cmpEdge = itr.next();
            if(cmpEdge.getU() == newEdge.getV() && cmpEdge.getV() == newEdge.getU())
                return true;
        }
        return false;
    }

    //----------------------------------------------
    //check for cycles in adjacency list

    //the recursive method to find cycles
    boolean isCyclicRecursive(int vertexNo, int parentVertexNo) {
        //mark the current vertex as visited
        adjList[vertexNo].getFirst().setVisited(true);

        //iterator for LinkedList representing all vertices connected to vertexNo
        Iterator<Vertex> itr = adjList[vertexNo].listIterator();

        //primer to get rid of vertex itself
        Vertex checkVertex = itr.next();

        while(itr.hasNext()){
            checkVertex = itr.next();
            if (!adjList[checkVertex.getID()-1].getFirst().isVisited()){
                if(isCyclicRecursive(checkVertex.getID()-1, vertexNo))
                    return true;
            }
            else if(checkVertex.getID()-1 != parentVertexNo)
                return true;
        }
        return false;
    }

    //initiation method for finding cycles
    boolean isCyclic() {
        for (int i = 0; i < adjList.length; i++) {
            if (!adjList[i].getFirst().isVisited())
                if (isCyclicRecursive(i, -1)) {
                    //reset all vertexes in adjacency list as unvisited
                    clearVisit();
                    return true;
                }
        }
        //reset all vertexes in adjacency list as unvisited
        clearVisit();
        return false;
    }

    //reset all vertexes in adjacency list as unvisited
    public void clearVisit () {
        for(int itr=0; itr< adjList.length; itr++)
            adjList[itr].getFirst().setVisited(false);
    }


    //---------------------------------------
    public LinkedList<Edge> getPath () {
        return path;
    }

    public void printAdjList () {
        for(int i=0; i < adjList.length; i++){
            System.out.print(adjList[i].getFirst().toString() + "-> ");
            Iterator<Vertex> itr = adjList[i].listIterator();
            Vertex v = itr.next();
            while(itr.hasNext()) {
                v = itr.next();
                System.out.print(v.toString() +" ");
            }
            System.out.println();
        }
    }
}