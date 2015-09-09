import org.junit.*;
import static org.junit.Assert.*;

public class ClassTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Class.all().size());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Class instance = new Class();
    instance.save();
    assertEquals(Class.all().get(0).getId(), instance.getId());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Class firstInstance = new Class();
    Class secondInstance = new Class();
    assertEquals(true, firstInstance.equals(secondInstance));
  }

  @Test
  public void save_addsToDatabase() {
    Class instance = new Class();
    instance.save();
    assertEquals(Class.all().get(0), instance);
  }

}
