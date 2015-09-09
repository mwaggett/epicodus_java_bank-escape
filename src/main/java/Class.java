import org.sql2o.*;
import java.util.List;

public class Class {

  private int id;

  public Class () {
    // pass ^arguments as necessary.
    // instantiate - assign values to variables, etc.
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherClassInstance) {
    if (!(otherClassInstance instanceof Class)) {
      return false;
    } else {
      Class newClassInstance = (Class) otherClassInstance;
      return // this.anyOtherAttributes().equals(newClassInstance.anyOtherAttributes()) &&
                this.getId() == newClassInstance.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO table_name (attribute) VALUES (:attribute);";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("attribute", attribute)
          .executeUpdate()
          .getKey();
    }
  }

  // public void update() {}

  // public void delete() {}

  public static List<Class> all() {
    String sql = "SELECT * FROM table_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Class.class);
    }
  }

  //public static Class find(int id) {}

}
