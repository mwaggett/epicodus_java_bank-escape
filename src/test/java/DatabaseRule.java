import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/bank_escape_test", null, null);
    // Make sure to customize the url to have the actual test database name.
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteTableQuery = "DELETE FROM weapons *;";
      String deleteTableQuery = "DELETE FROM players *;";
      String deleteTableQuery = "DELETE FROM weapons_players *;"; 
      con.createQuery(deleteWeaponsQuery).executeUpdate();
      con.createQuery(deletePlayersQuery).executeUpdate();
      con.createQuery(deleteWeaponsPlayersQuery).executeUpdate();

    }
  }
}
