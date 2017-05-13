import java.lang.annotation.Target;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	private Vertex[] vertices;
	private Edge edges;
	private Vertex currentVertex;
	private List<Vertex> setVertices;
	private List<Vertex> unsetVertices;
	private LinkedList<Integer> path;
	
	public Graph(Edge edges, Vertex[] vertices){
		this.edges=edges;
		this.vertices=vertices;
		path = new LinkedList<Integer>();
		setVertices= new ArrayList<Vertex>();
		unsetVertices= new ArrayList<Vertex>();
		currentVertex= new Vertex();

		//add all vertices from vertices to List<Vertex> unsetVertices
		for(int i=0; i<vertices.length;i++)
			unsetVertices.add(vertices[i]);
	}

	public void startVertex(int a){ //ù��° vertex �ʱ�ȭ
		for(Vertex vertex: unsetVertices)
			if(vertex.getID()==a){
				vertex.setDistance(0);
				currentVertex=vertex;
			}
	}
	
	public List<Vertex> getNeighbors(Vertex vertex){
		List<Vertex> neighbors=new ArrayList<Vertex>();
		for(Vertex target:unsetVertices)
			if(edges.getFlag(vertex.getID(), target.getID())){
				neighbors.add(target);				
			}
		return neighbors;
	}
	
	public void setDistance(){
		List<Vertex> neighbors = getNeighbors(currentVertex);
		
		for(Vertex vertex:neighbors)		
			if(edges.getFlag(currentVertex.getID(), vertex.getID())){
				if(vertex.getDistance()>currentVertex.getDistance()+edges.getDistance(currentVertex.getID(), vertex.getID())){
					vertex.setDistance(currentVertex.getDistance()+edges.getDistance(currentVertex.getID(), vertex.getID()));
					vertex.setPath(currentVertex.getID());
					
					//System.out.println("Vertex: "+vertex.getDistance());//setDistance���� �Ϸ�
				}
			}
	}

	public void loop(){
		double min=Integer.MAX_VALUE;;
		int pos=-10;
		for(Vertex vertex:unsetVertices) //�ʱ�ȭ
			if(min>vertex.getDistance()){
				pos=vertex.getID();
				min=vertex.getDistance();
			}
		
		for(Iterator<Vertex> it=unsetVertices.iterator();it.hasNext();){
			Vertex value=it.next();
			if(pos==value.getID()){
				setVertices.add(value);
				it.remove();
				currentVertex=value;
			}
		}
	}

	public int findBefore(int a){
		int start;
		for(Vertex target:setVertices)
			if(target.getID()==a){
				start=target.getPath();
				return start;
			}
		return -1;
	}
	
	public void findPath(int a){
		int end = a;
		path.add(end);
		while(true){
			end=findBefore(end);
			if(end==-1) break;
			path.add(end);
			if(setVertices.get(0).getID()==end)break;
		}
		Collections.reverse(path);
	}
	
	public LinkedList<Integer> showPath(int a){
		findPath(a);
		for(int load: path){
			System.out.print(load);
			if(load == a)   
				break;
			System.out.print(" -> ");
		}	
		System.out.println();
		return path;
	}
	
	public void execute(int a){
		startVertex(a);
		while(unsetVertices.size()>1){
			this.loop();
			this.setDistance();
		}
		this.show();
	}

	public void show() {
		for (Vertex vertex : setVertices)
			System.out.println(vertex);
	}
}
