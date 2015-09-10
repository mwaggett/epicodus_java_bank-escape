import org.junit.*;
import static org.junit.Assert.*;

public class PersonTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(0, Person.all().size());
  }

  @Test
  public void getName_returnsName() {
    Person person = new Person("George");
    assertEquals("George", person.getName());
  }

  @Test
  public void getXCoordinate_isInt() {
    Person person = new Person("George");
    assertTrue(person.getXCoordinate() < 401);
  }

  @Test
  public void getYCoordinate_isInt() {
    Person person = new Person("George");
    assertTrue(person.getYCoordinate() < 401);
  }

  @Test
  public void getHealth_returnsHealth() {
    Person person = new Person("George");
    assertEquals(100, person.getHealth());
  }

  @Test
  public void setHealth_changesHealth() {
    Person person = new Person("George");
    person.save();
    person.setHealth(person.getHealth() - 20);
    assertEquals(80, person.getHealth());
    assertEquals(80, Person.all().get(0).getHealth());
  }

  @Test
  public void getId_returnsIdAfterSave() {
    Person person = new Person("George");
    person.save();
    assertEquals(Person.all().get(0).getId(), person.getId());
  }

  @Test
  public void equals_returnsTrueWhenParamsMatch() {
    Person firstPerson = new Person("George");
    Person secondPerson = new Person("George");
    assertEquals(true, firstPerson.equals(secondPerson));
  }

  @Test
  public void equals_returnsFalseWhenParamsDiffer() {
    Person firstPerson = new Person("George");
    Person secondPerson = new Person("Frank");
    assertEquals(false, firstPerson.equals(secondPerson));
  }

  @Test
  public void save_addsToDatabase() {
    Person person = new Person("George");
    person.save();
    assertEquals(Person.all().get(0), person);
  }

  @Test
  public void moveLeft_updatesXCoordinateMinus10() {
    Person person = new Person("George");
    person.save();
    int originalX = person.getXCoordinate();
    person.moveLeft();
    assertEquals(10, originalX - person.getXCoordinate());
    assertEquals(10, originalX - Person.all().get(0).getXCoordinate());
  }

  @Test
  public void moveRight_updatesXCoordinatePlus10() {
    Person person = new Person("George");
    person.save();
    int originalX = person.getXCoordinate();
    person.moveRight();
    assertEquals(10, person.getXCoordinate() - originalX);
    assertEquals(10, Person.all().get(0).getXCoordinate() - originalX);
  }

  @Test
  public void moveUp_updatesYCoordinateMinus10() {
    Person person = new Person("George");
    person.save();
    int originalY = person.getYCoordinate();
    person.moveUp();
    assertEquals(10, originalY - person.getYCoordinate());
    assertEquals(10, originalY - Person.all().get(0).getYCoordinate());
  }

  @Test
  public void moveDown_updatesYCoordinatePlus10() {
    Person person = new Person("George");
    person.save();
    int originalY = person.getYCoordinate();
    person.moveDown();
    assertEquals(10, person.getYCoordinate() - originalY);
    assertEquals(10, Person.all().get(0).getYCoordinate() - originalY);
  }

  // Movement tests will fail when person spawns too close to a wall.

  @Test
  public void melee_affectsHealth(){
    Person attacker = new Person("George");
    attacker.save();
    Person target = new Person("Frank");
    target.save();
    attacker.melee(target);
    assertTrue(Person.find(attacker.getId()).getHealth() < 100);
    assertTrue(Person.find(target.getId()).getHealth() < 100);
    // Will fail if randomGenerator chooses 0.
  }

  @Test
  public void pickUp_getWeapons_personAcquiresWeapon() {
    Person person = new Person("George");
    person.save();
    Weapon weapon = new Weapon("Knife", 20);
    weapon.save();
    person.pickUp(weapon);
    assertTrue(person.getWeapons().contains(weapon));
  }

  @Test
  public void use_affectsHealth(){
    Person attacker = new Person("George");
    attacker.save();
    Weapon weapon = new Weapon("Knife", 20);
    weapon.save();
    Person target = new Person("Frank");
    target.save();
    attacker.pickUp(weapon);
    attacker.use(weapon, target);
    assertEquals(80, Person.find(target.getId()).getHealth());
  }

  @Test
  public void delete_deletesFromDatabase() {
    Person person = new Person("George");
    person.save();
    person.delete();
    assertEquals(0, Person.all().size());
  }

  @Test
  public void all_containsSavedPerson() {
    Person person = new Person("George");
    person.save();
    assertEquals(true, Person.all().contains(person));
  }

  @Test
  public void find_findsPersonInDatabase() {
    Person person = new Person("George");
    person.save();
    assertEquals(person, Person.find(person.getId()));
  }

}
