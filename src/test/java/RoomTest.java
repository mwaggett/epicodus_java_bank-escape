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


}
