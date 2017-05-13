package Dijikstra;

import java.util.LinkedList;

public class VerticesList {
	private Vertex[] vertex;
	private int no;
	
	public VerticesList(LinkedList[] a){
		no=a.length;
		vertex=new Vertex[no];
		for(int k=0;k<no;k++){
			vertex[k]=new Vertex(((Vertex)(a[k].getFirst())).getX(), ((Vertex)(a[k].getFirst())).getY(), ((Vertex)(a[k].getFirst())).getID());
		}
	}
	
	public Vertex getVertex(int a){
		return vertex[a];		
	}
	
	public int getNo(){
		return no;
	}
	
}
