/**
 * Created by Yehwan on 5/9/2017.
 */

import java.lang.String;
import java.io.*;

public class ReadFile implements ProjectInterface {
    Coordinates[] coordinates;
    public ReadFile(){
    }

    //Receive String fileName and reads text file for coordinates and returns an array of Coordinate instances
    public Coordinates[] getCoor(String fileName) throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str;
        String[] delimit;
        int count = 0;

        //get number of coordinates in file and create instance
        coordinates = new Coordinates[numOfCoordinates(fileName)];

        while ((str = in.readLine()) != null) {
            //delimit by ", "
            delimit = str.split(", ");
            coordinates[count] = new Coordinates();
            //parse x value and insert to Coordinate array
            coordinates[count].setX(Integer.parseInt(delimit[0]));
            //parse y value and insert to Coordinate array
            coordinates[count].setY(Integer.parseInt(delimit[1]));
            coordinates[count].setID(count+1);

            //System.out.println(coordinates[count].getX()+","+ coordinates[count].getY()); //print parsed coordinates
            count++;
        }
        in.close();

        return coordinates;
    }

    public int numOfCoordinates (String fileName) throws IOException{
        //function to count num of lines(coordinates)
        LineNumberReader lnr = new LineNumberReader(new FileReader(fileName));
        //skip to end of file line number
        lnr.skip(Long.MAX_VALUE);
        //allocate array of Coordinates to number of lines in file
        int numOfCoord = lnr.getLineNumber() + 1;
        lnr.close();
        return numOfCoord;
    }
}