import java.io.IOException;

/**
 * Created by Yehwan on 5/9/2017.
 */
public class OODPProject1 {
    public static void main (String[] args) throws IOException{
        ReadFile input = new ReadFile();
        Coordinates[] coordinates = null;
        int count =0, x,y, numOfCoor=0;
        String fileName = "test.txt";
        try {
            coordinates = input.getCoor(fileName);
            numOfCoor = input.numOfCoordinates(fileName);
        }
        catch (IOException e) {
            System.out.println("IOException Error");
        }

        while(coordinates[count] != null){
            x = coordinates[count].getX();
            y = coordinates[count].getY();
            System.out.println(x + "," + y +","+coordinates[count].getID());
            count++;
        }

        Graph g = new Graph(coordinates);
        g.printEdges();
        g.sortList();
        g.printEdges();
    }
}
