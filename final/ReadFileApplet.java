import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

public class ReadFileApplet extends Applet {
	int spread = 9;
    String fileToRead = "test.txt";
    int width, height;
    //String fileToRead;
    Graphics g;

    //----------------
    Button b1;
    TextField t1;
    Label lid;

    //----------------
    LinkedList[] vertices = new LinkedList[50];
    VerticesList vList;
    EdgeList edgeList =new EdgeList(51);
    Graph graph;

    //LinkedList<Integer> path=null;
	LinkedList<Edge> path = null;
    
    public void init() {
        readFile();
        new Thread().start();
        width = getSize().width;
        height = getSize().height;
        setBackground(Color.black);

		Kruskal k = new Kruskal(vertices, edgeList);
		path = k.getPath();
    }

    public void paint (Graphics g){
    	drawLine(g); // draw line
    	drawPoint(g); //draw Coordinates Point
    	drawNewPoint(g); //draw Start Point

		Iterator<Edge> itr = path.listIterator();
		while(itr.hasNext()) {
			Edge plotEdge = itr.next();
			drawNewLine(g, plotEdge.getU().getID()-1, plotEdge.getV().getID()-1);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			drawNewPoint(g);
		}


		//plotting dijistrak
		/*
    	for(int i=0;i<50;i++){
    		findLine(i);
    		int prevVertex=0;

    		for(int load: path){
    			drawNewLine(g,prevVertex,load);
    			
    			try {
    				Thread.sleep(20);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    
    			prevVertex=load;
    			
    			if(load == i)   
    				break;	
    		
    		}
    		
    		drawNewPoint(g);    		
    		path=null;
    	}
    	*/
    
    }

    public void readFile(){
        String line;
        int edgeNumber;

        //array of Linked List that stores vertices and the edges connected to it
        for(int i=0; i< 50;i++)
            vertices[i] = new LinkedList();

        //hold parsed string delimited by ", "
        String[] delimit;
        //count iterator for saving vertices
        int count =0;
        //read String
        String str;

        try {
            URL url = new URL(getCodeBase(), fileToRead);
            InputStream in = url.openStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));

            while((str = bf.readLine()) != null) {
                //delimit by ", "
                delimit = str.split(", ");

                //initialize new Vertex
                Vertex newV = new Vertex();

                //parse x value and insert to Coordinate array
                newV.setX(Integer.parseInt(delimit[0]));

                //parse y value and insert to Coordinate array
                newV.setY(Integer.parseInt(delimit[1]));

                //set the numerical id of vertex
                newV.setID(count+1);

                //add vertex to the linked list
                vertices[count].add(newV);
                count++;
            }
           
            setEdge();
            setData(this.vertices);
            
            
        } catch(IOException err) {
            System.out.println("Error");
        }
    }

   public void drawPoint(Graphics g){
    	Vertex v = null;
        int x,y;
        g.setColor(Color.white);
        for (int i=0; i < 50; i++) {
            v= (Vertex)vertices[i].getFirst();
            x = v.getX();
            y = v.getY();
            g.fillOval(x*spread,y*spread,4,4);
        }
    }
   	
   public void drawLine(Graphics g){
	   Vertex v = null;
	   Vertex w = null;
	   int x,y;
	   int a,b;
	   g.setColor(Color.BLACK);
	   
	   for(int i=0; i<50; i++){
		   v=(Vertex)vertices[i].getFirst();
		   for(int j=1; j<49; j++){
			   w=(Vertex)vertices[j].getFirst();
			   x=v.getX();
			   y=v.getY();
			   a=w.getX();
			   b=w.getY();
			   
			   if(Math.sqrt((x-a)*(x-a)+(y-b)*(y-b))<50)
			   g.drawLine(x*spread,y*spread,a*spread,b*spread);
		   }   
	   }
   }
    
   public int calculateNoEdge(){
	   Vertex v = null;
	   Vertex w = null;
	   int x,y;
	   int a,b;
	   int edgeCount=0;
	   
	   for(int i=0; i<50; i++){
		   v=(Vertex)vertices[i].getFirst();
		   for(int j=1; j<49; j++){
			   w=(Vertex)vertices[j].getFirst();
			   x=v.getX();
			   y=v.getY();
			   a=w.getX();
			   b=w.getY();
			   
			   if(Math.sqrt((x-a)*(x-a)+(y-b)*(y-b))<50)
				   edgeCount++;
		   }   
	   }
	   
	   return edgeCount;
   }

   public void setEdge(){
       Vertex a1=null;
       Vertex a2=null;
       int xa1,ya1,xa2,ya2;
       
       for(int i=0; i<50; i++){
       	a1=(Vertex)vertices[i].getFirst();
       	for(int j=1; j<49; j++){
       		a2=(Vertex)vertices[j].getFirst();
       		xa1=a1.getX();
			   	ya1=a1.getY();
			   	xa2=a2.getX();
			   	ya2=a2.getY();
			   if(Math.sqrt((xa1-xa2)*(xa1-xa2)+(ya1-ya2)*(ya1-ya2))<50){
				   edgeList.addEdge(i, j, Math.sqrt((xa1-xa2)*(xa1-xa2)+(ya1-ya2)*(ya1-ya2)));
			   }
       	}
       }

   }
   
   public void setData(LinkedList[] vertices){
	   LinkedList[] copy=vertices;
	   vList=new VerticesList(vertices);
	   graph=new Graph(this.edgeList, vList);
	   graph.execute(0);
   }


   /*
   public void findLine(int end){
	  this.path=graph.showPath(end);
   }
   */
	
   public void drawNewPoint(Graphics g){
	   Vertex v = null;
       int x,y;
       g.setColor(Color.RED);
       
       v= (Vertex)vertices[0].getFirst();
       x = v.getX();
       y = v.getY();
       g.fillOval(x*spread,y*spread,4,4); }
	

   
	public void drawNewLine(Graphics g,int a1, int a2){
		Vertex v = null;
		Vertex w = null;
		int x,y;
		int a,b;
		g.setColor(Color.GREEN);

		v=(Vertex)vertices[a1].getFirst();
		w=(Vertex)vertices[a2].getFirst();
			   
		x=v.getX();
		y=v.getY();
		a=w.getX();
		b=w.getY();
		g.drawLine(x*spread,y*spread,a*spread,b*spread);
	}
}