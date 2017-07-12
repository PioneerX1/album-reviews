import org.sql2o.*;
import java.util.*;

public class Artist {
  private String name;
  private int id;

  public Artist(String name) {
    this.name = name;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO artists(name) VALUES (:name);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Artist> all() {
    String sql = "SELECT id, name FROM artists";

    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Artist.class);
    }
  }

  public static Artist find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM artists where id=:id";
      Artist artist = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Artist.class);
    return artist;
    }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE artists SET name = :name WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM artists WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherArtist) {
    if(!(otherArtist instanceof Artist)) {
      return false;
    } else {
      Artist newArtist = (Artist) otherArtist;
      return this.getId() == newArtist.getId() &&
             this.getName().equals(newArtist.getName());
    }
  }
}
