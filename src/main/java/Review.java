import org.sql2o.*;
import java.util.*;

public class Review{
  private int id;
  private String name;
  private String comment;
  private int rating;
  private int albumId;

  public Review(String name, String comment, int rating, int albumId){
    this.name = name;
    this.comment = comment;
    this.rating = rating;
    this.albumId = albumId;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews(name, comment, rating, albumId) VALUES (:name, :comment, :rating, :albumId);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("comment", this.comment)
      .addParameter("rating", this.rating)
      .addParameter("albumId", this.albumId)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Review> all(){
    // String sql = "SELECT id, name, comment, rating, albumId FROM reviews";
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM reviews;";
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  @Override
  public boolean equals(Object otherReview){
    if(!(otherReview instanceof Review)){
      return false;
    } else {
      Review newReview = (Review) otherReview;
      return this.getId() == newReview.getId() &&
      this.getName().equals(newReview.getName()) &&
      this.getComment().equals(newReview.getComment()) &&
      this.getRating() == newReview.getRating() &&
      this.getAlbumId() == newReview.getAlbumId();
    }
  }

  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE id=:id;";
      Review review = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
        return review;
    }
  }

  public static List<Review> findAlbumId(int albumId){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM reviews WHERE albumId=:albumId;";
      List selectedReviews = con.createQuery(sql)
        .addParameter("albumId", albumId)
        .executeAndFetch(Review.class);
      return selectedReviews;

    }
  }

  public void update(String name, String comment, int rating) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE reviews SET name = :name, comment = :comment, rating = :rating WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("comment", comment)
        .addParameter("rating", rating)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM reviews WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }



  public String getName() {
    return name;
  }

  public String getComment() {
    return comment;
  }

  public int getRating() {
    return rating;
  }

  public int getId() {
    return id;
  }

  public int getAlbumId() {
    return albumId;
  }

}
