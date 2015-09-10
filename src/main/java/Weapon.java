import org.sql2o.*;
import java.util.List;

public class Weapon {

  private int id;
  private int damage;
  private String nameOfWeapon;
  private int person_id;
  private int x_coordinate;
  private int y_coordinate;
  // ^These are not in database (yet?). Not sure if they're necessary or not.


  public Weapon (String nameOfWeapon, int damage) {
    this.nameOfWeapon = nameOfWeapon;
    this.damage = damage;
    this.person_id = 0;
  }

  public int getId() {
    return id;
  }

  public String getNameOfWeapon(){
    return nameOfWeapon;
  }

 public int getDamage(){
   return damage;
 }

public int getPersonId(){
  return person_id;
}


public int getXCoordinate() {
  return x_coordinate;
}

public int getYCoordinate() {
  return y_coordinate;
}

  @Override
  public boolean equals(Object otherWeapon) {
    if (!(otherWeapon instanceof Weapon)) {
      return false;
    } else {
      Weapon newWeapon = (Weapon) otherWeapon;
      return    this.getId() == newWeapon.getId() &&
                this.getNameOfWeapon().equals(newWeapon.getNameOfWeapon()) &&
                this.getDamage() == newWeapon.getDamage();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO weapons (nameofweapon, damage, person_id) VALUES (:nameofweapon, :damage, :person_id);";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("nameofweapon", nameOfWeapon)
          .addParameter("damage", damage)
          .addParameter("person_id", person_id)
          .executeUpdate()
          .getKey();
    }
  }

  public void update(String nameOfWeapon) {
    this.nameOfWeapon = nameOfWeapon;
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE weapons SET nameofweapon = :nameofweapon WHERE id = :id";
      con.createQuery(sql)
      .addParameter("nameofweapon", nameOfWeapon)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

    public void delete() {
    try(Connection con = DB.sql2o.open()){
     String sql = "DELETE FROM weapons WHERE id = :id";
     con.createQuery(sql)
     .addParameter("id", id)
     .executeUpdate();

     String deletePersonWeapon = "DELETE FROM people_weapons WHERE weapons_id = :id";
      con.createQuery(deletePersonWeapon)
      .addParameter("id", id)
      .executeUpdate();
     }
    }

  public static List<Weapon> all() {
    String sql = "SELECT * FROM weapons";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Weapon.class);
    }
  }

  public static Weapon find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM weapons WHERE id=:id";
      Weapon weapon = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Weapon.class);
      return weapon;
    }
  }

  public void addPerson(Person person) {
    this.person_id = person.getId();
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE weapons SET person_id = :person_id WHERE id = :weapon_id";
      con.createQuery(sql)
        .addParameter("person_id", person.getId())
        .addParameter("weapon_id", id)
        .executeUpdate();
    }
  }

  public static List<Weapon> search(String query) {
    try(Connection con = DB.sql2o.open()) {
      String searchQuery = "%"+query+"%";
      String sql = "SELECT * FROM weapons WHERE nameofweapon LIKE :searchQuery";
      List<Weapon> weapons = con.createQuery(sql)
        .addParameter("searchQuery", searchQuery)
        .executeAndFetch(Weapon.class);
      return weapons;
    }
  }

}
