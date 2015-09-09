import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class RoomTest {

  // @Rule
  // public DatabaseRule database = new DatabaseRule();

  @Test
  public void constructor_SpawnsNumberOfItems() {

    Room test = new Room();
    ArrayList<int[]> testArray = test.getItemLocations();
    assertEquals(40, testArray.size());
  }

  @Test
  public void constructor_SpawnsNumberOfNpcs() {

    Room test = new Room();
    ArrayList<int[]> testArray = test.getNpcLocations();
    assertEquals(40, testArray.size());
  }

  @Test
  public void checkResults_DoesntLetYouGoThruWall(){
    Room test = new Room();
    int[] illegalCoords = new int[2];
    illegalCoords[0] = -1;
    illegalCoords[1] = 0;
    assertEquals("Don't go there!", test.checkCoords(illegalCoords));
  }

  @Test
  public void checkResults_SeesAnItem(){
    Room test = new Room();

    int[] testItem = {0,0};
    int[] testCoords = {0,0};

    test.placeItem(testItem);

    assertEquals("There is an item here!  ", test.checkCoords(testCoords));
  }


}
