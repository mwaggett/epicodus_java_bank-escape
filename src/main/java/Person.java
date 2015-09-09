import org.sql2o.*;
import java.util.List;
import java.util.Random;

public class Person {

  private int id;
  private String name;
  private int x_coordinate;
  private int y_coordinate;
  private int health;

  private static Random randomGenerator = new Random();

  public Person (String name) {
    this.name = name;
    this.x_coordinate = randomGenerator.nextInt(401); // Randomize NPC location.
    this.y_coordinate = randomGenerator.nextInt(401);
    this.health = 100;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getXCoordinate() {
    return x_coordinate;
  }

  public int getYCoordinate() {
    return y_coordinate;
  }

  public int getHealth() {
    return health;
  }

  @Override
  public boolean equals(Object otherPersonInstance) {
    if (!(otherPersonInstance instanceof Person)) {
      return false;
    } else {
      Person newPersonInstance = (Person) otherPersonInstance;
      return this.getName().equals(newPersonInstance.getName()) &&
             // this.getXCoordinate() == newPersonInstance.getXCoordinate() &&
             // this.getYCoordinate() == newPersonInstance.getYCoordinate() &&
             // ^Since locations are randomly generated, these won't be equal.
             // Do we just want people with the same name to be equal? idk.
             // Or should we include these anyway and it's just hard to test?
             this.getHealth() == newPersonInstance.getHealth() &&
             this.getId() == newPersonInstance.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO people (name, x_coordinate, y_coordinate, health) VALUES (:name, :x_coordinate, :y_coordinate, :health);";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("name", name)
          .addParameter("x_coordinate", x_coordinate)
          .addParameter("y_coordinate", y_coordinate)
          .addParameter("health", health)
          .executeUpdate()
          .getKey();
    }
  }

  public void moveLeft() {
    this.x_coordinate -= 1;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET x_coordinate = :x WHERE id = :id";
      con.createQuery(sql)
        .addParameter("x", x_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void moveRight() {
    this.x_coordinate += 1;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET x_coordinate = :x WHERE id = :id";
      con.createQuery(sql)
        .addParameter("x", x_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void moveUp() {
    this.y_coordinate -= 1;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET y_coordinate = :y WHERE id = :id";
      con.createQuery(sql)
        .addParameter("y", y_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void moveDown() {
    this.y_coordinate += 1;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET y_coordinate = :y WHERE id = :id";
      con.createQuery(sql)
        .addParameter("y", y_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM people WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static List<Person> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM people";
      return con.createQuery(sql).executeAndFetch(Person.class);
    }
  }

  public static Person find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM people WHERE id = :id";
      return con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Person.class);
    }
  }

}
