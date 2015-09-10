import org.sql2o.*;
import java.util.List;
import java.util.Random;

public class Person {

  protected int id;
  protected String name;
  protected int x_coordinate;
  protected int y_coordinate;
  protected int health;

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
    this.x_coordinate -= 10;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET x_coordinate = :x WHERE id = :id";
      con.createQuery(sql)
        .addParameter("x", x_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void moveRight() {
    this.x_coordinate += 10;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET x_coordinate = :x WHERE id = :id";
      con.createQuery(sql)
        .addParameter("x", x_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void moveUp() {
    this.y_coordinate -= 10;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET y_coordinate = :y WHERE id = :id";
      con.createQuery(sql)
        .addParameter("y", y_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void moveDown() {
    this.y_coordinate += 10;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET y_coordinate = :y WHERE id = :id";
      con.createQuery(sql)
        .addParameter("y", y_coordinate)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void melee(Person target) {
    this.health -= randomGenerator.nextInt(6);
    try(Connection con = DB.sql2o.open()) {
      String attackerQuery = "UPDATE people SET health = :health WHERE id = :id";
      con.createQuery(attackerQuery)
        .addParameter("health", health)
        .addParameter("id", id)
        .executeUpdate();

      String targetQuery = "UPDATE people SET health = :health WHERE id = :id";
      con.createQuery(targetQuery)
        .addParameter("health", target.getHealth() - randomGenerator.nextInt(11))
        .addParameter("id", target.getId())
        .executeUpdate();
      // I'm worried about the fact that we're not updating the target instance
      // and only the entry in the database. But maybe that doesn't matter.
    }
  }

  public void pickUp(Weapon weapon) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE weapons SET person_id = :id WHERE id = :weapon_id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("weapon_id", weapon.getId())
        .executeUpdate();
    }
  }

  public List<Weapon> getWeapons() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM weapons WHERE person_id = :id";
      return con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Weapon.class);
    }
  }

  public void use(Weapon weapon, Person target) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE people SET health = :decreased_health WHERE id = :target_id";
      con.createQuery(sql)
        .addParameter("decreased_health", target.getHealth() - weapon.getDamage())
        .addParameter("target_id", target.getId())
        .executeUpdate();
    }
    // Not yet checking whether person even has the weapon.
    // Maybe eventually will take into account how close attacker and target are.
    // Also same worry as in melee method.
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
