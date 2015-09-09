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
  public void moveLeft_updatesXCoordinateMinus1() {
    Person person = new Person("George");
    person.save();
    int originalX = person.getXCoordinate();
    person.moveLeft();
    assertEquals(1, originalX - person.getXCoordinate());
    assertEquals(1, originalX - Person.all().get(0).getXCoordinate());
  }

  @Test
  public void moveRight_updatesXCoordinatePlus1() {
    Person person = new Person("George");
    person.save();
    int originalX = person.getXCoordinate();
    person.moveRight();
    assertEquals(1, person.getXCoordinate() - originalX);
    assertEquals(1, Person.all().get(0).getXCoordinate() - originalX);
  }

  @Test
  public void moveUp_updatesYCoordinateMinus1() {
    Person person = new Person("George");
    person.save();
    int originalY = person.getYCoordinate();
    person.moveUp();
    assertEquals(1, originalY - person.getYCoordinate());
    assertEquals(1, originalY - Person.all().get(0).getYCoordinate());
  }

  @Test
  public void moveDown_updatesYCoordinatePlus1() {
    Person person = new Person("George");
    person.save();
    int originalY = person.getYCoordinate();
    person.moveDown();
    assertEquals(1, person.getYCoordinate() - originalY);
    assertEquals(1, Person.all().get(0).getYCoordinate() - originalY);
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
