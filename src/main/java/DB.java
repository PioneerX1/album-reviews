import org.sql2o.*;

//Add project database name after localhost:5432/
//Example: localhost:5432:to_do
public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/album_reviews", null, null);
}
