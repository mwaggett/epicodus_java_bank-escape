import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/database_name_test", null, null);
    // Make sure to customize the url to have the actual test database name.
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteTableQuery = "DELETE FROM table_name *;"; // Change to actual table name.
      // v^Add same things for any other tables in the database.^v
      con.createQuery(deleteTableQuery).executeUpdate();
    }
  }
}
