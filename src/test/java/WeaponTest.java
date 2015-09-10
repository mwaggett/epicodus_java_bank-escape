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
  public void getXCoordinate_isInt() {
    Weapon newWeapon = new Weapon("Knife", 20);
    assertTrue(newWeapon.getXCoordinate() < 401);
  }

  @Test
  public void getYCoordinate_isInt() {
    Weapon newWeapon = new Weapon("Knife", 20);
    assertTrue(newWeapon.getYCoordinate() < 401);
  }

  @Test
  public void getDamage_returnsDamage() {
    Weapon newWeapon = new Weapon("Knife", 20);
    assertEquals(20, newWeapon.getDamage());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Weapon newWeapon = new Weapon("Knife", 20);
    newWeapon.save();
    assertEquals(Weapon.all().get(0).getId(), newWeapon.getId());
  }

  @Test
  public void equals_returnsFalseSinceLocationIsRandom() {
    Weapon firstWeapon = new Weapon("Knife", 20);
    Weapon secondWeapon = new Weapon("Knife", 20);
    assertEquals(false, firstWeapon.equals(secondWeapon));
  }

  @Test
  public void equals_returnsFalseWhenParamsDiffer() {
    Weapon firstWeapon = new Weapon("Knife", 20);
    Weapon secondWeapon = new Weapon("Gun", 50);
    assertEquals(false, firstWeapon.equals(secondWeapon));
  }

  @Test
  public void save_addsToDatabase() {
    Weapon newWeapon = new Weapon("Knife", 20);
    newWeapon.save();
    assertEquals(Weapon.all().get(0).getNameOfWeapon(), newWeapon.getNameOfWeapon());
  }

  @Test
  public void instantiatesWithRandomCoords() {
    Weapon newWeapon = new Weapon("Knife", 20);

    assertTrue(newWeapon.getXCoordinate() != 500);
  }

  @Test
  public void coordsMatchWhenCalledFromDatabase() {
    Weapon newWeapon = new Weapon("Knife", 20);
    newWeapon.save();
    Weapon testWeapon = Weapon.all().get(0);
    assertEquals(newWeapon.getXCoordinate(), testWeapon.getXCoordinate());
  }

  @Test
  public void coordsMatchWhenUsingFind() {
    Weapon newWeapon = new Weapon("Knife", 20);
    newWeapon.save();
    int id = newWeapon.getId();
    Weapon testWeapon = Weapon.find(id);
    assertEquals(newWeapon.getXCoordinate(), testWeapon.getXCoordinate());
  }


}
