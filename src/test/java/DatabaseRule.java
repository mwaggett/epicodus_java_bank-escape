import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/bank_escape_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deletePeopleQuery = "DELETE FROM people *;";
      String deleteWeaponsQuery = "DELETE FROM weapons *;";
      con.createQuery(deletePeopleQuery).executeUpdate();
      con.createQuery(deleteWeaponsQuery).executeUpdate();
    }
  }
}
