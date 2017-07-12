
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {
  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/album_reviews_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteAlbumsQuery = "DELETE FROM albums *;";
      String deleteArtistsQuery = "DELETE FROM artists *;";
      con.createQuery(deleteAlbumsQuery).executeUpdate();
      con.createQuery(deleteArtistsQuery).executeUpdate();
    }
  }

}
