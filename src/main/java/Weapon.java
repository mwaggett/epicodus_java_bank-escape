import org.sql2o.*;
import java.util.List;

public class Weapon {
  //private List<Weapon> weapons;
  private int id;
  private int damage;
  private String nameOfWeapon;
  private int numberOfUses;
  private boolean broken;
  private String personId;

  public Weapon (String nameOfWeapon, int damage) {
    this.nameOfWeapon = nameOfWeapon;
    this.damage = damage;
    this.numberOfUses = 3;
    this.broken = false;
    this.personId = personId;
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

 public int getNumberOfUses(){
   return numberOfUses;
 }

public String getPersonId(){
  return personId;
}

  // @Override
  // public boolean equals(Object otherWeapon) {
  //   if (!(otherWeapon instanceof Weapon)) {
  //     return false;
  //   } else {
  //     Class newWeapon = (Class) otherWeapon;
  //     return    this.getId() == newWeapon.getId() &&
  //               this.getNameOfWeapon().equals(newWeapon.getNameOfWeapon()) &&
  //               this.getDamage() == newWeapon.getDamage() &&
  //               this.getNumberOfUses() == newWeapon.getNumberOfUses();
  //   }
  // }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO weapons (nameofweapon) VALUES (:nameofweapon);";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("nameofweapon", this.getNameOfWeapon())
          .executeUpdate()
          .getKey();
    }
  }

  public void update(String nameofweapon) {
    this.nameOfWeapon = nameOfWeapon;
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE weapons SET nameofweapon = :nameofweapon WHERE id = :id";
      con.createQuery(sql)
      .addParameter("nameofweapon", nameofweapon)
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
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO weapons_person (weapon_id, person_id) VALUES (:weapon_id, :person_id)";
      con.createQuery(sql)
        .addParameter("weapon_id", id)
        .addParameter("person_id", person.getId())
        .executeUpdate();
    }
  }

  public void removePerson(Person person){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM weapons_person WHERE weapon_id = :weapon_id AND person_id = :person_id";
      con.createQuery(sql)
      .addParameter("weapon_id", id)
      .addParameter("person_id", person.getId())
      .executeUpdate();
    }
  }

  public List<Person> getPerson() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT person.* FROM weapons JOIN weapons_person ON (weapon.id = weapons_person.weapon_id) JOIN person ON (weapons_person.Person_id = person.id) WHERE weapons.id = :id";
      List<Person> person = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Person.class);
      return person;
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

  public boolean isBroken(){
  return broken;
  }

}
