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

}
