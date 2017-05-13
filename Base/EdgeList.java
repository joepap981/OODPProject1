public class EdgeList {
	private double[][] distance;
	private boolean[][] flag;
	private int id;
	
	public EdgeList(int a){
		id =a;
		distance=new double[id][id]; // vertex ������ŭ �� ����
		flag =new boolean[id][id]; //���� �ִ��� üũ
		
		for(int i = 0; i< id; i++){ //�ʱⰪ
			for(int j = 0; j< id; j++){
				distance[i][j]=Integer.MAX_VALUE; // �ʱⰪ
				flag[i][j]=false;
			}
		}
	}
    
	public void addEdge(int aID, int bID, double d){ //�游���
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