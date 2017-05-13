package Dijikstra;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Edge {
	private double[][] distance;
	private boolean[][] flag;
	private int no;
	
	public Edge(int a){
		no=a;
		distance=new double[no][no]; // vertex 개수만큼 길 생성
		flag =new boolean[no][no]; //길이 있는지 체크
		
		for(int i=0; i<no; i++){ //초기값
			for(int j=0; j<no; j++){
				distance[i][j]=Integer.MAX_VALUE; // 초기값
				flag[i][j]=false;
			}
		}
	}
    
	public void addEdge(int aID, int bID, double d){ //길만들기
		distance[aID][bID]=d;
		distance[bID][aID]=d;
		flag[aID][bID]=true;
		flag[bID][aID]=true;
	}
	
	public double getDistance(int a, int b){
		return distance[a][b];
	}
	
	public boolean getFlag(int a, int b){
		return flag[a][b];
	}
	
}