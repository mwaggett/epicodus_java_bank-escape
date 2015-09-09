import org.junit.*;
import static org.junit.Assert.*;

public class RoomTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Room.all().size());
  }


}
