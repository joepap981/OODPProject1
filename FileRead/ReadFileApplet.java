package Dijikstra;

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class ReadFileApplet extends Applet {
	//String fileToRead = "test1.txt";
	String fileToRead;
	StringBuffer strBuff;
	TextArea txtArea;
	Graphics g;
	
	//----------------
	Button b1;
	TextField t1;
	Label lid;
	
	public void init() {
		inputFile();
		txtArea = new TextArea(10, 100);
	    txtArea.setEditable(false);
	    add(txtArea, "left");
	 }
	
	 public void readFile(){
		 String line;
	     try {
	    	 URL url = new URL(getCodeBase(), fileToRead);
	         InputStream in = url.openStream();
	         BufferedReader bf = new BufferedReader(new InputStreamReader(in));
	         strBuff = new StringBuffer();
	         while((line = bf.readLine()) != null) {
	            strBuff.append(line + "\n");
	         }
	         txtArea.append("File Name : " + fileToRead + "\n");
	         txtArea.append(strBuff.toString());
	         //bf.close();
	      } catch(IOException err) {
	         txtArea.append("Error");
	      }
	   }	
	 
	 public void inputFile(){
		 lid=new Label("File Name: ", Label.CENTER);
		 t1=new TextField(100);
		 b1=new Button("OK");
		 
		 t1.addActionListener(new EventHandler());
		 b1.addActionListener(new EventHandler());
		 
		 setLayout(new FlowLayout());
		 add(lid);
		 add(t1);
		 add(b1);
		 setSize(50,100);
		 setVisible(true);
	 }
	 class EventHandler implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 fileToRead=t1.getText();
			 if(fileToRead!=null){
				    readFile();
			 }
		 }
	 }
}
