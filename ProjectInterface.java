import java.io.IOException;

/**
 * Created by Yehwan on 5/9/2017.
 */
public interface ProjectInterface{
    public Coordinates[] getCoor(String fileName) throws IOException;
    public int numOfCoordinates (String fileName) throws IOException;
}
