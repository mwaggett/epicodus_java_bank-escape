import org.junit.*;
import static org.junit.Assert.*;

public class WeaponTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Weapon.all().size());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Weapon newWeapon = new Weapon();
    newWeapon.save();
    assertEquals(Weapon.all().get(0).getId(), newWeapon.getId());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Weapon firstWeapon = new Weapon();
    Weapon secondWeapon = new Weapon();
    assertEquals(true, firstWeapon.equals(secondWeapon));
  }

  @Test
  public void save_addsToDatabase() {
    Weapon newWeapon = new Weapon();
    newWeapon.save();
    assertEquals(Weapon.all().get(0), newWeapon);
  }

  


}
