// import org.sql2o.*;
// import java.util.List;
//
// public class Weapon {
//   //private List<Weapon> weapons;
//   private int id;
//   private int damage;
//   private String nameOfWeapon;
//   private int numberOfUses;
//   private boolean broken;
//
//   public Weapon (String nameOfWeapon, int damage) {
//     this.nameOfWeapon = nameOfWeapon;
//     this.damage = damage;
//     this.numberOfUses = 3;
//     //this.broken = false;
//   }
//
//   public int getId() {
//     return id;
//   }
//
//   public String getNameOfWeapon(){
//     return nameOfWeapon;
//   }
//
//  public int getDamage(){
//    return damage;
//  }
//
//  public int getNumberOfUses(){
//    return numberOfUses;
//  }
//
//   @Override
//   public boolean equals(Object otherWeapon) {
//     if (!(otherWeapon instanceof Weapon)) {
//       return false;
//     } else {
//       Class newWeapon = (Class) otherWeapon;
//       return    this.getId() == newWeapon.getId() &&
//                 this.getNameOfWeapon().equals(newWeapon.getNameOfWeapon()) &&
//                 this.getDamage() == newWeapon.getDamage() &&
//                 this.getNumberOfUses() == newWeapon.getNumberOfUses();
//     }
//   }
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO weapons (name_of_weapon) VALUES (:name_of_weapon);";
//       this.id = (int) con.createQuery(sql, true)
//           .addParameter("name_of_weapon", name_of_weapon)
//           .executeUpdate()
//           .getKey();
//     }
//   }
//
//   public void update(String nameOfWeapon) {
//     this.nameOfWeapon = nameOfWeapon;
//     try(Connection con = DB.sql2o.open()){
//       String sql = "UPDATE weapons SET name_of_weapon = :name_of_weapon WHERE id = :id";
//       con.createQuery(sql)
//       .addParameter("name_of_weapon", name_of_weapon)
//       .addParameter("id", id)
//       .executeUpdate();
//     }
//   }
//
//     public void delete() {
//     try(Connection con = DB.sql2o.open()){
//      String sql = "DELETE FROM weapons WHERE id = :id";
//      con.createQuery(sql)
//      .addParameter("id", id)
//      .executeUpdate();
//
//      String deletePersonWeapon = "DELETE FROM Person_weapons WHERE weapons_id = :id";
//       con.createQuery(deletePersonWeapon)
//       .addParameter("id", id)
//       .executeUpdate();
//      }
//     }
//
//   public static List<Weapon> all() {
//     String sql = "SELECT * FROM weapons";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(Weapon.class);
//     }
//   }
//
//   public static Weapon find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM weapons WHERE id=:id";
//       Weapon weapon = con.createQuery(sql)
//         .addParameter("id", id)
//         .executeAndFetchFirst(Weapon.class);
//       return weapon;
//     }
//   }
//
//   public void addPerson(Person person) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO weapons_person (weapon_id, Person_id) VALUES (:weapon_id, :Person_id)";
//       con.createQuery(sql)
//         .addParameter("weapon_id", id)
//         .addParameter("Person_id", Person.getId())
//         .executeUpdate();
//     }
//   }
//
//   public void removePerson(Person Person){
//     try(Connection con = DB.sql2o.open()){
//       String sql = "DELETE FROM weapons_person WHERE weapon_id = :weapon_id AND Person_id = :Person_id";
//       con.createQuery(sql)
//       .addParameter("weapon_id", id)
//       .addParameter("Person_id", Person.getId())
//       .executeUpdate();
//     }
//   }
//
//   public List<Person> getPerson() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT person.* FROM weapons JOIN weapons_person ON (weapons.id = weapons_person.weapon_id) JOIN person ON (weapons_person.Person_id = person.id) WHERE weapons.id = :id";
//       List<Person> person = con.createQuery(sql)
//         .addParameter("id", id)
//         .executeAndFetch(Person.class);
//       return person;
//     }
//   }
//
//   public static List<Weapon> search(String query) {
//     try(Connection con = DB.sql2o.open()) {
//       String searchQuery = "%"+query+"%";
//       String sql = "SELECT * FROM weapons WHERE name_of_weapon LIKE :searchQuery";
//       List<Weapon> weapons = con.createQuery(sql)
//         .addParameter("searchQuery", searchQuery)
//         .executeAndFetch(Weapon.class);
//       return weapons;
//     }
//   }
//
//   public boolean isBroken(){
//   return broken;
//   }
//
// }
