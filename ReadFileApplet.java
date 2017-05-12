package Dijikstra;
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.io.*;
import java.net.*;
import java.util.LinkedList;

import javax.swing.*;

public class ReadFileApplet extends Applet {
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

    public void init() {
        readFile();
        width = getSize().width;
        height = getSize().height;
        setBackground(Color.black);

    }

    public void paint (Graphics g){
    	drawLine(g); // draw line
    	drawPoint(g); //draw Coordinates Point

    }

    public void readFile(){
        String line;
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

        } catch(IOException err) {
            System.out.println("Error");
            //txtArea.append("Error");
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
            g.fillOval(x*5,y*5,5,5);
        }
    }
   	
   public void drawLine(Graphics g){
	   Vertex v = null;
	   Vertex w = null;
	   int x,y;
	   int a,b;
	   g.setColor(Color.GRAY);
	   
	   for(int i=0; i<50; i++){
		   v=(Vertex)vertices[i].getFirst();
		   for(int j=1; j<49; j++){
			   w=(Vertex)vertices[j].getFirst();
			   x=v.getX();
			   y=v.getY();
			   a=w.getX();
			   b=w.getY();
			   
			   if(Math.sqrt((x-a)*(x-a)+(y-b)*(y-b))<50)
			   g.drawLine(x*5,y*5,a*5,b*5);  
		   }   
	   }
   }
    
    
}